package com.example.schedulebackend.services;

import com.example.schedulebackend.exceptions.handlers.ItemHandler;
import com.example.schedulebackend.model.dto.FacultyDTO;
import com.example.schedulebackend.model.entities.Faculty;
import com.example.schedulebackend.repos.FacultyRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService{
    private final FacultyRepo facultyRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final String itemName;

    public FacultyService(FacultyRepo facultyRepo,
                          ItemHandler itemHandler,
                          DbaService dbaService,
                          @Value("faculty") String itemName) {
        this.facultyRepo = facultyRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.itemName = itemName;
    }

    public void create(FacultyDTO facultyDTO) {
        itemHandler.handleIsIdNull(facultyDTO.getId(),itemName);
        saveToDB(facultyDTO);
    }

    public void update(FacultyDTO facultyDTO) {
        dbaService.get(facultyRepo, itemName, facultyDTO.getId());
        saveToDB(facultyDTO);
    }

    public FacultyDTO get(long id) {
        Faculty faculty = dbaService.get(facultyRepo, itemName, id);
        return FacultyDTO.toDTO(faculty);
    }

    public void delete(long id) {
        dbaService.get(facultyRepo, itemName, id);
        facultyRepo.deleteById(id);
    }

    public List<FacultyDTO> getAll() {
            return facultyRepo.findAll().stream().map(FacultyDTO::toDTO).toList();
    }


    public void saveToDB(FacultyDTO facultyDTO){
        facultyRepo.save(Faculty.toEntity(facultyDTO));
    }

    public Faculty getFaculty(FacultyDTO facultyDTO) {
        if (facultyDTO != null) {
            return dbaService.get(facultyRepo, itemName, facultyDTO.getId());
        }
        return null;
    }

}
