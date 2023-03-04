package com.vladimirpandurov.finalExam2B.web.controller;

import com.vladimirpandurov.finalExam2B.domain.Sprint;
import com.vladimirpandurov.finalExam2B.service.SprintService;
import com.vladimirpandurov.finalExam2B.support.SprintToSprintDTO;
import com.vladimirpandurov.finalExam2B.web.dto.SprintDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sprints")
@RequiredArgsConstructor
public class ApiSprintController {

    private final SprintService sprintService;
    private final SprintToSprintDTO sprintToSprintDTO;

    @GetMapping
    public ResponseEntity<List<SprintDTO>> getSprints(){
        List<Sprint> sprintList = sprintService.findAll();

        return new ResponseEntity<>(sprintToSprintDTO.convert(sprintList), HttpStatus.OK);
    }

}
