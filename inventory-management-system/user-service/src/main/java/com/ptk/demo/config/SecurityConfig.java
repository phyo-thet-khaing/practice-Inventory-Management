
package com.ptk.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    // JWT filter ကို inject လုပ်ထားတယ်
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Password ကို plain text မသိမ်းဘဲ hash လုပ်ဖို့
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                // CSRF ကို ယာယီပိတ်ထားခြင်း
                // Cookie authentication အတွက် production မှာ CSRF protection ထည့်ရန်
        			.csrf(csrf -> csrf.disable())	

                // Server-side session မသုံးဘဲ JWT ကို အသုံးပြုမယ်
        			.sessionManagement(session -> session
        			        .sessionCreationPolicy(
        			            SessionCreationPolicy.STATELESS
        			        )
        			    )

                .authorizeHttpRequests(auth -> auth

                        // Preflight requests (CORS)
                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                        .permitAll()

                        // Login နဲ့ ပထမဆုံး Admin setup ကို ခွင့်ပြု
                        // IMPORTANT: setup-admin ကို setup ပြီးရင် ပိတ်ရန်
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/auth/login",
                                "/api/auth/setup-admin"
                        ).permitAll()

                        // Swagger documentation
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Health check ကိုသာ public ထား
                        .requestMatchers("/actuator/health")
                        .permitAll()

                        // User နဲ့ Role management ကို ADMIN သာ အသုံးပြုနိုင်
                        .requestMatchers(
                                "/api/users/**",
                                "/api/roles/**"
                        ).hasRole("ADMIN")

                        // ကျန်တဲ့ endpoint တွေမှာ Login ဝင်ထားရမယ်
                        .anyRequest().authenticated()
                )

                // JWT filter ကို UsernamePasswordAuthenticationFilter မတိုင်ခင်ထည့်
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}
