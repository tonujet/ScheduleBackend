package com.example.schedulebackend.controllers;

import com.example.schedulebackend.model.dto.FacultyDTO;
import com.example.schedulebackend.services.FacultyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController{
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody FacultyDTO facultyDTO) {
        facultyService.create(facultyDTO);
        return ResponseEntity.ok("Faculty is created");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody FacultyDTO facultyDTO) {
        facultyService.update(facultyDTO);
        return ResponseEntity.ok("Faculty is updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(facultyService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        facultyService.delete(id);
        return ResponseEntity.ok("Faculty is deleted");
    }

    @GetMapping
    public ResponseEntity<List<FacultyDTO>> getAll() {
        return ResponseEntity.ok(facultyService.getAll());
    }
}
