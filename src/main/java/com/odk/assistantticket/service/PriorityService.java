package com.odk.assistantticket.service;

import com.odk.assistantticket.model.Priorite;
import com.odk.assistantticket.model.Utilisateur;
import com.odk.assistantticket.repository.PrioriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Utilisateur  utilisateur = (Utilisateur) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        priorite.setUtilisateur(utilisateur);
        prioriteRepository.save(priorite);
    }
}
