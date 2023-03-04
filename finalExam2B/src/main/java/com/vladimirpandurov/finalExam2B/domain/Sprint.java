package com.vladimirpandurov.finalExam2B.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer allPoints;
    @OneToMany(mappedBy = "sprint",fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();

    public void deleteTask(Task task){
        this.tasks.remove(task);
        substratePoints(task.getPoints());
    }

    public void addTask(Task task){
        this.tasks.add(task);
        addPoints(task.getPoints());
    }

    public void addPoints(Integer points){
        this.allPoints += points;
    }

    public void substratePoints(Integer points){
        this.allPoints -= points;
    }

}
