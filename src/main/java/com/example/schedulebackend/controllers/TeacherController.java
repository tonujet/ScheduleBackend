package com.example.schedulebackend.controllers;

import com.example.schedulebackend.model.dto.TeacherDTO;
import com.example.schedulebackend.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController{
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody TeacherDTO teacherDTO) {
        teacherService.create(teacherDTO);
        return ResponseEntity.ok("Teacher is created");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody TeacherDTO teacherDTO) {
        teacherService.update(teacherDTO);
        return ResponseEntity.ok("Teacher is updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(teacherService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        teacherService.delete(id);
        return ResponseEntity.ok("Teacher is deleted");
    }

    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAll() {
        return ResponseEntity.ok(teacherService.getAll());
    }

}
