package com.ptk.demo.service;

import java.util.List;

import com.ptk.demo.dto.response.RoleResponse;

public interface RoleService  {

	RoleResponse getRoleById(Long id);
	
	List<RoleResponse> getAllRoles();
}
