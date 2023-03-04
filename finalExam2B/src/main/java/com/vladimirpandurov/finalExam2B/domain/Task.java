package com.vladimirpandurov.finalExam2B.domain;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String subscribers;
    private Integer points;
    @ManyToOne
    @JoinColumn(name="sprint_id")
    private Sprint sprint;
    @ManyToOne
    @JoinColumn(name="state_id")
    private State state;


    public void addSprint(Sprint sprint){
        this.sprint = sprint;
        if(!sprint.getTasks().contains(this)){
            sprint.addTask(this);
        }
    }

    public void deleteSprint(Sprint sprint){
        this.sprint = null;
        sprint.deleteTask(this);
    }

    public void addState(State state){
        this.state = state;
        state.addTask(this);
    }

    public void deleteState(State state){
        this.state = null;
        state.deleteTask(this);
    }

}
