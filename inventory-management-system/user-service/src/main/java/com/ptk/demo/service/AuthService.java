package com.ptk.demo.service;

import com.ptk.demo.dto.request.ChangePasswordRequest;
import com.ptk.demo.dto.request.LoginRequest;
import com.ptk.demo.dto.request.RegisterRequest;
import com.ptk.demo.dto.response.LoginResponse;
import com.ptk.demo.dto.response.UserResponse;

import jakarta.validation.Valid;

public interface AuthService {
	
	LoginResponse login(LoginRequest request);
	
	void changePassword(ChangePasswordRequest request);
	
	UserResponse setupAdmin(@Valid RegisterRequest request);
	
	void logout();
}
