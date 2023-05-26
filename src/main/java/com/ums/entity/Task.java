package com.ums.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    private String name;

    private String description;
    private LocalDate date;
    private boolean isCompleted;
    private String creatorName;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public long daysLeftUntilDeadline(LocalDate date){
        return ChronoUnit.DAYS.between(LocalDate.now(),date);
    }

    public Task() {
    }

    public Task(Long id, String name, String description, LocalDate date, boolean isCompleted, String creatorName, User owner) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.isCompleted = isCompleted;
        this.creatorName = creatorName;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj==null || getClass() != obj.getClass()) return false;
        Task task = (Task) obj;
        return isCompleted == task.isCompleted &&
                Objects.equals(id,task.id) &&
                name.equals(task.name) &&
                description.equals(task.description) &&
                date.equals(task.date) &&
                Objects.equals(creatorName,task.creatorName) &&
                Objects.equals(owner,task.owner);
    }

    @Override
    public int hashCode() {
return Objects.hash(id,name,description,date,isCompleted,creatorName,owner);    }
}
