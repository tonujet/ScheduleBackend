package com.example.schedulebackend.controllers;

import com.example.schedulebackend.model.dto.DisciplineDTO;
import com.example.schedulebackend.services.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController{
    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody DisciplineDTO disciplineDTO) {
        disciplineService.create(disciplineDTO);
        return ResponseEntity.ok("Discipline is created");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody DisciplineDTO disciplineDTO) {
        disciplineService.update(disciplineDTO);
        return ResponseEntity.ok("Discipline is updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(disciplineService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        disciplineService.delete(id);
        return ResponseEntity.ok("Discipline is deleted");
    }

    @GetMapping
    public ResponseEntity<List<DisciplineDTO>> getAll() {
        return ResponseEntity.ok(disciplineService.getAll());
    }
}
