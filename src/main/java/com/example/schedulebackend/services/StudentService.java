package com.example.schedulebackend.services;


import com.example.schedulebackend.exceptions.handlers.ItemHandler;
import com.example.schedulebackend.model.dto.GroupDTO;
import com.example.schedulebackend.model.dto.StudentDTO;
import com.example.schedulebackend.model.entities.Student;
import com.example.schedulebackend.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepo studentRepo;
    private final GroupService groupService;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final String itemName;

    public StudentService(StudentRepo studentRepo,
                          GroupService groupService,
                          ItemHandler itemHandler,
                          DbaService dbaService,
                          @Value("student") String itemName) {
        this.studentRepo = studentRepo;
        this.groupService = groupService;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.itemName = itemName;
    }

    public void create(StudentDTO studentDTO) {
        itemHandler.handleIsIdNull(studentDTO.getId(), itemName);
        saveToDB(studentDTO);
    }

    public void update(StudentDTO studentDTO) {
        dbaService.get(studentRepo, itemName, studentDTO.getId());
        saveToDB(studentDTO);
    }

    public StudentDTO get(long id) {
        Student student = dbaService.get(studentRepo, itemName, id);
        return StudentDTO.toDTO(
                student,
                GroupDTO.toDTO(student.getGroup(), null)
        );
    }

    public void delete(long id) {
        dbaService.get(studentRepo, itemName, id);
        studentRepo.deleteById(id);
    }

    public List<StudentDTO> getAll() {
        return studentRepo.findAll().stream().map(this::toDTO).toList();
    }

    public void saveToDB(StudentDTO studentDTO) {
        studentRepo.save(Student.toEntity(
                studentDTO,
                groupService.getGroup(studentDTO.getGroupDTO())
        ));
    }

    public StudentDTO toDTO(Student student) {
        return StudentDTO.toDTO(
                student,
                GroupDTO.toDTO(student.getGroup(), null)
        );
    }

}
