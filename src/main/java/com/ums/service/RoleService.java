package com.ums.service;

import com.ums.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);

    List<Role> findAll();

}
