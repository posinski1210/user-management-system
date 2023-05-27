package com.ums.entity;

import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name="email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name="password")
    private String password;
    @OneToMany(fetch =FetchType.EAGER,mappedBy = "owner")
    private Set<Task> tasksOwned;

    private String username;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    public List<Task> getTasksCompleted() {
        return tasksOwned.stream()
                .filter(Task::isCompleted)
                .collect(Collectors.toList());
    }

    public List<Task> getTasksInProgress() {
        return tasksOwned.stream()
                .filter(task -> !task.isCompleted())
                .collect(Collectors.toList());
    }

    public boolean isAdmin() {
        Hibernate.initialize(roles);
        String roleName = "ROLE_ADMIN";
        return roles.stream().map(Role::getRole).anyMatch(roleName::equals);
    }

    public User() {
    }

    public User(String email,
                String name,
               String password
                ) {
        this.email = email;
        this.name = name;
        this.password = password;

    }

    public User(String email,
                 String name,
               String password,
                Set<Task> tasksOwned,
                List<Role> roles) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.tasksOwned = tasksOwned;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<Task> getTasksOwned() {
        return tasksOwned;
    }

    public void setTasksOwned(Set<Task> tasksOwned) {
        this.tasksOwned = tasksOwned;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }




    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
