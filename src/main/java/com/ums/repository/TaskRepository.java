package com.ums.repository;

import com.ums.entity.Task;
import com.ums.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @EntityGraph(attributePaths = "owner")
    List<Task> findByOwnerOrderByDateDesc(User user);
}
