package com.vladimirpandurov.finalExam2B.support;

import com.vladimirpandurov.finalExam2B.domain.Task;
import com.vladimirpandurov.finalExam2B.web.dto.TaskDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskToTaskDTO implements Converter<Task, TaskDTO> {
    @Override
    public TaskDTO convert(Task task) {
        if(task == null){
            return null;
        }

        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setName(task.getName());
        dto.setSubscribers(task.getSubscribers());
        dto.setPoints(task.getPoints());
        dto.setSprintId(task.getSprint().getId());
        dto.setSprintName(task.getSprint().getName());
        dto.setStateId(task.getState().getId());
        dto.setStateName(task.getState().getName());

        return dto;
    }

    public List<TaskDTO> convert(List<Task> taskList){
        List<TaskDTO> dtoList = new ArrayList<>();
        for(Task task : taskList){
            dtoList.add(convert(task));
        }
        return dtoList;
    }
}
