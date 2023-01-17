package com.example.schedulebackend.services;


import com.example.schedulebackend.exceptions.handlers.ItemHandler;
import com.example.schedulebackend.model.dto.DisciplineDTO;
import com.example.schedulebackend.model.entities.Discipline;
import com.example.schedulebackend.repos.DisciplineRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisciplineService {
    private final DbaService dbaService;
    private final DisciplineRepo disciplineRepo;
    private final ItemHandler itemHandler;
    private final String itemName;


    public DisciplineService(DbaService dbaService,
                             DisciplineRepo disciplineRepo,
                             ItemHandler itemHandler,
                             @Value("discipline") String itemName) {
        this.dbaService = dbaService;
        this.disciplineRepo = disciplineRepo;
        this.itemHandler = itemHandler;
        this.itemName = itemName;
    }

    public void create(DisciplineDTO disciplineDTO) {
        itemHandler.handleIsIdNull(disciplineDTO.getId(), itemName);
        saveToDB(disciplineDTO);
    }

    public void update(DisciplineDTO disciplineDTO) {
        dbaService.get(disciplineRepo, itemName, disciplineDTO.getId());
        saveToDB(disciplineDTO);
    }

    public DisciplineDTO get(long id) {
        Discipline discipline = dbaService.get(disciplineRepo, itemName, id);
        return DisciplineDTO.toDTO(discipline);
    }

    public void delete(long id) {
        dbaService.get(disciplineRepo, itemName, id);
        disciplineRepo.deleteById(id);
    }

    public List<DisciplineDTO> getAll() {
        return disciplineRepo.findAll().stream().map(DisciplineDTO::toDTO).toList();
    }

    public void saveToDB(DisciplineDTO disciplineDTO) {
        disciplineRepo.save(Discipline.toEntity(disciplineDTO));
    }

    public Discipline getDiscipline(DisciplineDTO disciplineDTO) {
        if (disciplineDTO != null) {
            return dbaService.get(disciplineRepo, itemName, disciplineDTO.getId());
        }
        return null;
    }
}
