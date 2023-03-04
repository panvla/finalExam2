package com.vladimirpandurov.finalExam2B.web.controller;

import com.vladimirpandurov.finalExam2B.domain.Task;
import com.vladimirpandurov.finalExam2B.service.TaskService;
import com.vladimirpandurov.finalExam2B.support.TaskDTOToTask;
import com.vladimirpandurov.finalExam2B.support.TaskToTaskDTO;
import com.vladimirpandurov.finalExam2B.web.dto.TaskDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@RequiredArgsConstructor
@CrossOrigin
public class ApiTaskController {

    private final TaskService taskService;

    private final TaskToTaskDTO taskToTaskDTO;
    private final TaskDTOToTask taskDTOToTask;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getTasks(@RequestParam(value="pageNum", defaultValue = "0") int pageNum){
        Page<Task> taskList = taskService.findAll(pageNum);
        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(taskList.getTotalPages()));

        return new ResponseEntity<>(taskToTaskDTO.convert(taskList.getContent()), headers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getTask(@PathVariable("id") Long id){
        Task task = taskService.findOne(id);
        if(task == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(this.taskToTaskDTO.convert(task), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TaskDTO> addTask(@RequestBody TaskDTO taskDTO){
        Task savedTask = taskService.save(taskDTOToTask.convert(taskDTO));
        return new ResponseEntity<>(taskToTaskDTO.convert(savedTask), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> edit(@RequestBody TaskDTO taskDTO, @PathVariable("id") Long id){
        if(!id.equals(taskDTO.getId())){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Task savedTask = taskService.save(taskDTOToTask.convert(taskDTO));

        return new ResponseEntity<>(this.taskToTaskDTO.convert(savedTask), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TaskDTO> delete(@PathVariable("id") Long id){
        Task deletedTask = this.taskService.delete(id);

        return new ResponseEntity<>(taskToTaskDTO.convert(deletedTask), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TaskDTO>> searchAll(@RequestParam("taskName") String taskName,
                                                   @RequestParam("sprintId") Long sprintId,
                                                   @RequestParam(value="pageNum", defaultValue = "0") int pageNum){
        Page<Task> taskList = taskService.searchAll(taskName, sprintId, pageNum);
        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(taskList.getTotalPages()));
        return new ResponseEntity<>(taskToTaskDTO.convert(taskList.getContent()), headers, HttpStatus.OK);
    }
    @GetMapping("/changeState")
    public ResponseEntity<TaskDTO> changeState(@RequestBody TaskDTO taskDTO){
        Task taskStateChanged = this.taskService.changeState(taskDTO);

        return new ResponseEntity<>(taskToTaskDTO.convert(taskStateChanged),HttpStatus.OK);
    }

}
