package com.aimsoft.patterns.circuitbreaker.client.controller;

import com.aimsoft.patterns.circuitbreaker.client.model.dto.ProcessDTO;
import com.aimsoft.patterns.circuitbreaker.client.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/processes")
public class ProcessesController {

    @Autowired
    private ProcessService processService;

    @PostMapping
    public void addProcess(@RequestBody ProcessDTO process) {
        processService.add(process);
    }
}
