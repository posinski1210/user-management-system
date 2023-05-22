package com.ums.repository;

import com.ums.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findByName(String name);

}
