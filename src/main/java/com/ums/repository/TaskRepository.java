package com.ums.repository;

import com.ums.entity.Task;
import com.ums.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByOwnerOrderByDateDesc(User user);
}
