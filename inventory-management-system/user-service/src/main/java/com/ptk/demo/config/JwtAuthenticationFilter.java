
package com.ptk.demo.config;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ptk.demo.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtAuthenticationFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String requestUri = request.getRequestURI();

        System.out.println(
                "JWT FILTER: "
                        + request.getMethod()
                        + " "
                        + requestUri
        );

        // 1. Read token from Authorization header
        String token = null;
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null
                && authHeader.startsWith("Bearer ")) {

            token = authHeader.substring(7).trim();
        }

        // 2. If no Bearer token, read the HttpOnly cookie
        if (token == null || token.isBlank()) {

            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {

                    if ("access_token".equals(cookie.getName())) {
                        token = cookie.getValue();
                        break;
                    }
                }
            }
        }

        // 3. No token: let Spring Security decide access
        if (token == null || token.isBlank()) {

            System.out.println("JWT FILTER: Token missing");

            filterChain.doFilter(request, response);
            return;
        }

        try {
            // 4. Extract JWT claims
            String username = jwtService.extractUsername(token);
            String role = jwtService.extractRole(token);

            // 5. Validate token
            boolean validToken =
                    username != null
                    && !username.isBlank()
                    && role != null
                    && !role.isBlank()
                    && jwtService.isTokenValid(token, username);

            if (!validToken) {
                System.out.println("JWT FILTER: Invalid token");

                filterChain.doFilter(request, response);
                return;
            }

            // 6. Set authentication only if not already authenticated
            if (SecurityContextHolder.getContext()
                    .getAuthentication() == null) {

                role = role.trim();

                String authority = role.startsWith("ROLE_")
                        ? role
                        : "ROLE_" + role;

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                username,
                                null,
                                List.of(
                                        new SimpleGrantedAuthority(authority)
                                )
                        );

                authentication.setDetails(
                        new WebAuthenticationDetailsSource()
                                .buildDetails(request)
                );

                SecurityContextHolder.getContext()
                        .setAuthentication(authentication);

                System.out.println(
                        "JWT AUTHENTICATION SUCCESS: " + username
                );
                System.out.println("AUTHORITIES: " + authority);
            }

        } catch (Exception e) {

            System.out.println("JWT ERROR: " + e.getMessage());
            SecurityContextHolder.clearContext();
        }

        // 7. Continue the filter chain
        filterChain.doFilter(request, response);
    }
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return "OPTIONS".equalsIgnoreCase(request.getMethod());
    }
}
