package com.odk.assistantticket.service;

import com.odk.assistantticket.model.BaseDeConnaissance;
import com.odk.assistantticket.repository.BaseDeConnaissanceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BaseDeConnaissanceService {

    private BaseDeConnaissanceRepository baseDeConnaissanceRepository;

    public Iterable<BaseDeConnaissance> listAllBaseDeConnaissance() {
        return baseDeConnaissanceRepository.findAll();
    }

    public void insertBaseDeConnaissance(BaseDeConnaissance baseDeConnaissance) {
        baseDeConnaissanceRepository.save(baseDeConnaissance);
    }
}
