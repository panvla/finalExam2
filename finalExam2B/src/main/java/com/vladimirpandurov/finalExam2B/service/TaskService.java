package com.vladimirpandurov.finalExam2B.service;

import com.vladimirpandurov.finalExam2B.domain.Sprint;
import com.vladimirpandurov.finalExam2B.domain.State;
import com.vladimirpandurov.finalExam2B.domain.Task;
import com.vladimirpandurov.finalExam2B.repository.SprintRepository;
import com.vladimirpandurov.finalExam2B.repository.StateRepository;
import com.vladimirpandurov.finalExam2B.repository.TaskRepository;
import com.vladimirpandurov.finalExam2B.web.dto.TaskDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final SprintRepository sprintRepository;
    private final StateRepository stateRepository;

    public Task findOne(Long id){
        return taskRepository.findById(id).get();
    }


    public Task save(Task task){
        Sprint sprint = task.getSprint();
        sprintRepository.save(sprint);
        Task savedTask = taskRepository.save(task);
        return savedTask;
    }

    public Task delete(Long id){
        Task task = findOne(id);
        Sprint sprint = task.getSprint();
        if(sprint != null){
            sprint.deleteTask(task);
        }
        State state = task.getState();
        if(state != null){
            state.deleteTask(task);
        }
        this.taskRepository.delete(task);
        return task;
    }

    public Page<Task> findAll(int pageNum){
        return this.taskRepository.findAll( PageRequest.of(pageNum, 10));
    }

    public Page<Task> searchAll(String taskName, Long sprintId, int pageNum){
        return this.taskRepository.search(taskName, sprintId, PageRequest.of(pageNum, 10));
    }

    public Task changeState(TaskDTO taskDTO){
        Task task = findOne(taskDTO.getId());
        State state = this.stateRepository.findById(taskDTO.getStateId()).get();
        List<State> states = this.stateRepository.findAll();
        for(State newState : states){
            if(newState.getName().equals("in progress")&&state.getName().equals("new")){
                state.deleteTask(task);
                task.addState(newState);
                this.stateRepository.save(state);
            }else if(newState.getName().equals("finished")&&state.getName().equals("in progress")){
                state.deleteTask(task);
                task.addState(newState);
                this.stateRepository.save(state);
            }
        }
        return task;
    }



}
