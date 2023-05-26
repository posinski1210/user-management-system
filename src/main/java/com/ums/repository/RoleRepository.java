package com.ums.repository;

import com.ums.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;



public interface RoleRepository extends JpaRepository<Role,Long> {
   Role findByRole(String user);

}
