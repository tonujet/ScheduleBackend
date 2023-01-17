package com.example.schedulebackend.services;

import com.example.schedulebackend.exceptions.handlers.ItemHandler;
import com.example.schedulebackend.model.dto.TeacherDTO;
import com.example.schedulebackend.model.entities.Teacher;
import com.example.schedulebackend.repos.TeacherRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService {
    private final TeacherRepo teacherRepo;
    private final DbaService dbaService;
    private final ItemHandler itemHandler;
    private final String itemName;

    public TeacherService(TeacherRepo teacherRepo,
                          DbaService dbaService,
                          ItemHandler itemHandler,
                          @Value("teacher") String itemName) {
        this.teacherRepo = teacherRepo;
        this.dbaService = dbaService;
        this.itemHandler = itemHandler;
        this.itemName = itemName;
    }


    public void create(TeacherDTO teacherDTO) {
        itemHandler.handleIsIdNull(teacherDTO.getId(), itemName);
        saveToDB(teacherDTO);
    }

    public void update(TeacherDTO teacherDTO) {
        dbaService.get(teacherRepo, itemName, teacherDTO.getId());
        saveToDB(teacherDTO);
    }

    public TeacherDTO get(long id) {
        return TeacherDTO.toDTO(
                dbaService.get(teacherRepo, itemName, id)
        );
    }

    public void delete(long id) {
        dbaService.get(teacherRepo, itemName, id);
        teacherRepo.deleteById(id);
    }

    public List<TeacherDTO> getAll() {
        return teacherRepo.findAll().stream().map(TeacherDTO::toDTO).toList();
    }


    public Teacher getTeacher(TeacherDTO teacherDTO) {
        if (teacherDTO != null) {
            return dbaService.get(teacherRepo, itemName, teacherDTO.getId());
        }
        return null;
    }

    public void saveToDB(TeacherDTO teacherDTO) {
        teacherRepo.save(Teacher.toEntity(
                teacherDTO
        ));
    }

}
