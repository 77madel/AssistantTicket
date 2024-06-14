package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.Priorite;
import com.odk.assistantticket.service.PriorityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("priorite")
public class PrioriteController {

    private PriorityService priorityService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<Priorite> listAllPriorites() {
        return this.priorityService.getAllPriorite();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void Add(@RequestBody Priorite priorite) {

        priorityService.AddPriorite(priorite);
    }
}
