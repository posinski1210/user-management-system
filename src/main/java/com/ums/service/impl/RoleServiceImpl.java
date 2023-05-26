package com.ums.service.impl;

import com.ums.entity.Role;
import com.ums.repository.RoleRepository;
import com.ums.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @Override
    public Role createRole(Role role){
        return roleRepository.save(role);
    }

    @Override
    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
