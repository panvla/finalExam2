package com.vladimirpandurov.finalExam2B.web.controller;

import com.vladimirpandurov.finalExam2B.domain.State;
import com.vladimirpandurov.finalExam2B.service.StateService;
import com.vladimirpandurov.finalExam2B.support.StateToStateDTO;
import com.vladimirpandurov.finalExam2B.web.dto.StateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/states")
@RequiredArgsConstructor
public class ApiStateController {

    private final StateService stateService;
    private final StateToStateDTO stateToStateDTO;

    @GetMapping
    public ResponseEntity<List<StateDTO>> getStates(){
        List<State> stateList = this.stateService.findAll();

        return new ResponseEntity<>(stateToStateDTO.convert(stateList), HttpStatus.OK);
    }

}
