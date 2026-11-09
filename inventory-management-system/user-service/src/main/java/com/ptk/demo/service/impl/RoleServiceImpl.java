
package com.ptk.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ptk.demo.dto.response.RoleResponse;
import com.ptk.demo.exception.RoleNotFoundException;
import com.ptk.demo.mapper.RoleMapper;
import com.ptk.demo.model.Role;
import com.ptk.demo.repository.RoleRepository;
import com.ptk.demo.service.RoleService;

@Service
@Transactional(readOnly = true)
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // GET ROLE BY ID
    @Override
    public RoleResponse getRoleById(Long id) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() ->
                        new RoleNotFoundException(
                                "Role not found with id: " + id
                        )
                );

        return RoleMapper.toResponse(role);
    }

    // GET ALL ROLES
    @Override
    public List<RoleResponse> getAllRoles() {

        return roleRepository.findAll()
                .stream()
                .map(RoleMapper::toResponse)
                .toList();
    }
}
