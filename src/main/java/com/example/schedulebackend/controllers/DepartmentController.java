package com.example.schedulebackend.controllers;

import com.example.schedulebackend.model.dto.DepartmentDTO;
import com.example.schedulebackend.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController{
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody DepartmentDTO departmentDTO) {
        departmentService.create(departmentDTO);
        return ResponseEntity.ok("Department is created");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody DepartmentDTO departmentDTO) {
        departmentService.update(departmentDTO);
        return ResponseEntity.ok("Department is updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(departmentService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        departmentService.delete(id);
        return ResponseEntity.ok("Department is deleted");
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        return ResponseEntity.ok(departmentService.getAll());
    }
}
