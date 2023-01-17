package com.example.schedulebackend.controllers;

import com.example.schedulebackend.model.dto.GroupDTO;
import com.example.schedulebackend.services.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/groups")
public class GroupController{

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody GroupDTO groupDTO) {
        groupService.create(groupDTO);
        return ResponseEntity.ok("Group is created");
    }

    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody GroupDTO groupDTO) {
        groupService.update(groupDTO);
        return ResponseEntity.ok("Group is updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(groupService.get(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        groupService.delete(id);
        return ResponseEntity.ok("Group is deleted");
    }

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAll() {
        return ResponseEntity.ok(groupService.getAll());
    }

}
