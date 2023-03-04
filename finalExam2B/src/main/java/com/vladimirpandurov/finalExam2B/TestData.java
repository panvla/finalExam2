package com.vladimirpandurov.finalExam2B;

import com.vladimirpandurov.finalExam2B.domain.Sprint;
import com.vladimirpandurov.finalExam2B.domain.State;
import com.vladimirpandurov.finalExam2B.domain.Task;
import com.vladimirpandurov.finalExam2B.service.SprintService;
import com.vladimirpandurov.finalExam2B.service.StateService;
import com.vladimirpandurov.finalExam2B.service.TaskService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestData {

    private final SprintService sprintService;
    private final StateService stateService;
    private final TaskService taskService;

    @PostConstruct
    @Transactional
    public void init(){

        Sprint sprint1 = new Sprint();
        sprint1.setName("Sprint1");
        sprint1.setAllPoints(0);
        this.sprintService.save(sprint1);

        Sprint sprint2 = new Sprint();
        sprint2.setName("Sprint2");
        sprint2.setAllPoints(0);
        this.sprintService.save(sprint2);

        State state1 = new State();
        state1.setName("new");
        this.stateService.save(state1);

        State state2 = new State();
        state2.setName("in progress");
        this.stateService.save(state2);

        State state3 = new State();
        state3.setName("finished");
        this.stateService.save(state3);

        Task task = new Task();
        task.setName("Add Entities");
        task.setPoints(5);
        task.setSubscribers("Jackson Hinkle");
        task.addState(state1);
        task.addSprint(sprint1);
        this.taskService.save(task);

        Task task1 = new Task();
        task1.setName("Add Repository");
        task1.setPoints(10);
        task1.setSubscribers("Duglas Schmith");
        task1.addState(state1);
        task1.addSprint(sprint1);
        //this.sprintService.save(sprint1);
        this.taskService.save(task1);

        Task task2 = new Task();
        task2.setName("Add Service");
        task2.setPoints(15);
        task2.setSubscribers("Joe Biden");
        task2.addState(state1);
        task2.addSprint(sprint1);
        this.taskService.save(task2);

        //this.sprintService.save(sprint1);



        log.info(sprint1.getAllPoints().toString());

    }

}
