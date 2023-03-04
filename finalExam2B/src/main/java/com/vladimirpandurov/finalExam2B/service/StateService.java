package com.vladimirpandurov.finalExam2B.service;

import com.vladimirpandurov.finalExam2B.domain.State;
import com.vladimirpandurov.finalExam2B.repository.StateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;

    public State findOne(Long id){
        return this.stateRepository.getById(id);
    }

    public List<State> findAll(){
        return this.stateRepository.findAll();
    }

    public State save(State state){
        return this.stateRepository.save(state);
    }

    public State delete(Long id){
        State state = findOne(id);
        if(state != null){
            this.stateRepository.delete(state);
        }
        return state;
    }

}
