package com.ums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ums.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);

}
