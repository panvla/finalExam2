package com.vladimirpandurov.finalExam2B.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "state", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    public void deleteTask(Task task){
        this.tasks.remove(task);
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

}
