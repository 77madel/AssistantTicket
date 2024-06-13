package com.odk.assistantticket.controller;

import com.odk.assistantticket.model.BaseDeConnaissance;
import com.odk.assistantticket.service.BaseDeConnaissanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("basedeconnaissance")
public class BaseDeConnaissanceController {

    private BaseDeConnaissanceService baseDeConnaissanceService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public Iterable<BaseDeConnaissance> getAllBaseDeConnaissance() {
        return this.baseDeConnaissanceService.listAllBaseDeConnaissance();
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public void save(@RequestBody BaseDeConnaissance baseDeConnaissance) {
        this.baseDeConnaissanceService.insertBaseDeConnaissance(baseDeConnaissance);
    }
}
