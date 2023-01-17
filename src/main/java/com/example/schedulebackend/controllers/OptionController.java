package com.example.schedulebackend.controllers;

import com.example.schedulebackend.model.options.Options;
import com.example.schedulebackend.services.OptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/options")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public ResponseEntity<Options> getOptions(){
        return ResponseEntity.ok(optionService.getOptions());
    }
}
