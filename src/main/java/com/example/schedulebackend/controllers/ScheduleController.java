package com.example.schedulebackend.controllers;

import com.example.schedulebackend.model.dto.ScheduleDTO;
import com.example.schedulebackend.services.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController{

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.create(scheduleDTO);
        return ResponseEntity.ok("Schedule is created");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.update(scheduleDTO);
        return ResponseEntity.ok("Schedule is updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(scheduleService.get(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        scheduleService.delete(id);
        return ResponseEntity.ok("Schedule is deleted");
    }

    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAll() {
        return ResponseEntity.ok(scheduleService.getAll());
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<HashMap<String, List<ScheduleDTO>>> getFullSchedule(@PathVariable long id) {
        return ResponseEntity.ok(scheduleService.getFullSchedule(id));
    }
}

