package com.example.schedulebackend.services;

import com.example.schedulebackend.exceptions.handlers.ItemHandler;
import com.example.schedulebackend.model.dto.DepartmentDTO;
import com.example.schedulebackend.model.dto.FacultyDTO;
import com.example.schedulebackend.model.entities.Department;
import com.example.schedulebackend.repos.DepartmentRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService{

    private final DepartmentRepo departmentRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final FacultyService facultyService;
    private final String itemName;

    public DepartmentService(DepartmentRepo departmentRepo,
                             ItemHandler itemHandler,
                             DbaService dbaService,
                             FacultyService facultyService,
                             @Value("department") String itemName) {
        this.departmentRepo = departmentRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.facultyService = facultyService;
        this.itemName = itemName;
    }


    public void create(DepartmentDTO departmentDTO) {
        itemHandler.handleIsIdNull(departmentDTO.getId(), itemName);
        saveToDB(departmentDTO);
    }

    public void update(DepartmentDTO departmentDTO) {
        dbaService.get(departmentRepo, itemName, departmentDTO.getId());
        saveToDB(departmentDTO);
    }

    public DepartmentDTO get(long id) {
        Department department = dbaService.get(departmentRepo, itemName, id);
        return DepartmentDTO.toDTO(
                department,
                FacultyDTO.toDTO(department.getFaculty())
        );
    }


    public void delete(long id) {
        dbaService.get(departmentRepo, itemName, id);
        departmentRepo.deleteById(id);
    }

    public List<DepartmentDTO> getAll() {
        return departmentRepo.findAll().stream().map(this::toDto).toList();
    }


    public void saveToDB(DepartmentDTO departmentDTO) {
        departmentRepo.save(Department.toEntity(
                departmentDTO,
                facultyService.getFaculty(departmentDTO.getFacultyDTO())
        ));
    }

    public Department getDepartment(DepartmentDTO departmentDTO) {
        if (departmentDTO != null) {
            return dbaService.get(departmentRepo, itemName, departmentDTO.getId());
        }
        return null;
    }
    public DepartmentDTO toDto(Department department){
        return DepartmentDTO.toDTO(
                department,
                FacultyDTO.toDTO(department.getFaculty())
        );
    }
}
