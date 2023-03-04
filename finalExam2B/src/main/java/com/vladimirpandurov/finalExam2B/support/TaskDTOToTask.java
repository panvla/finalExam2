package com.vladimirpandurov.finalExam2B.support;

import com.vladimirpandurov.finalExam2B.domain.Sprint;
import com.vladimirpandurov.finalExam2B.domain.State;
import com.vladimirpandurov.finalExam2B.domain.Task;
import com.vladimirpandurov.finalExam2B.service.SprintService;
import com.vladimirpandurov.finalExam2B.service.StateService;
import com.vladimirpandurov.finalExam2B.service.TaskService;
import com.vladimirpandurov.finalExam2B.web.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskDTOToTask implements Converter<TaskDTO, Task> {

    private final TaskService taskService;
    private final StateService stateService;
    private final SprintService sprintService;

    @Override
    public Task convert(TaskDTO taskDTO) {

        Task task;

        State state = this.stateService.findOne(taskDTO.getStateId());

        Sprint sprint = this.sprintService.findOne(taskDTO.getSprintId());

        if(taskDTO.getId() != null){
            task = taskService.findOne(taskDTO.getId());
            if(task.getPoints()>taskDTO.getPoints()){
                task.getSprint().substratePoints(task.getPoints() - taskDTO.getPoints());
            }else if(task.getPoints()< taskDTO.getPoints()){
                task.getSprint().addPoints(taskDTO.getPoints() - task.getPoints());
            }
        }else{
            task = new Task();

        }

        task.setName(taskDTO.getName());
        task.setSubscribers(taskDTO.getSubscribers());

        task.setPoints(taskDTO.getPoints());
        task.addSprint(sprint);
        task.addState(state);

        return task;

    }

    public List<Task> convert(List<TaskDTO> dtoList){
        List<Task> taskList = new ArrayList<>();
        for(TaskDTO dto : dtoList){
            taskList.add(convert(dto));
        }
        return taskList;
    }
}
