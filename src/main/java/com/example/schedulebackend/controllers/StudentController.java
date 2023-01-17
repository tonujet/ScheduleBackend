package com.example.schedulebackend.controllers;


import com.example.schedulebackend.model.dto.StudentDTO;
import com.example.schedulebackend.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.create(studentDTO);
        return ResponseEntity.ok("Student is created");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.update(studentDTO);
        return ResponseEntity.ok("Student is updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(studentService.get(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.ok("Student was deleted");
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll() {
        return ResponseEntity.ok(studentService.getAll());
    }


}
