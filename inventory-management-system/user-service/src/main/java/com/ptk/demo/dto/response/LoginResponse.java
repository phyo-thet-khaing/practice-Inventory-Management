package com.ptk.demo.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	
	private String token;
	
	private String username;
	
	 private Boolean mustChangePassword;
	
	private List<String> roles;
}
