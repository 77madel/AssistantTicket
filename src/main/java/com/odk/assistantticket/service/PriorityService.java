package com.odk.assistantticket.service;

import com.odk.assistantticket.model.Priorite;
import com.odk.assistantticket.repository.PrioriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PriorityService {

    private PrioriteRepository prioriteRepository;

    public Iterable<Priorite> getAllPriorite() {
        return prioriteRepository.findAll();
    }

    public void AddPriorite(Priorite priorite) {
        prioriteRepository.save(priorite);
    }
}
