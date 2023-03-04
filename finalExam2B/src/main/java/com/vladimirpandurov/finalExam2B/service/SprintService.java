package com.vladimirpandurov.finalExam2B.service;

import com.vladimirpandurov.finalExam2B.domain.Sprint;
import com.vladimirpandurov.finalExam2B.repository.SprintRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SprintService{

    private final SprintRepository sprintRepository;

    public Sprint findOne(Long id) {
        return sprintRepository.getById(id);
    }

    public List<Sprint> findAll(){
        return sprintRepository.findAll();
    }

    @Transactional
    public Sprint save(Sprint sprint) {
        return sprintRepository.save(sprint);
    }

    public Sprint delete(Long id){
        Sprint sprint = findOne(id);
        if(sprint!=null){
            sprintRepository.delete(sprint);
        }
        return sprint;
    }

}
