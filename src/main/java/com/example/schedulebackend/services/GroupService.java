package com.example.schedulebackend.services;

import com.example.schedulebackend.exceptions.handlers.ItemHandler;
import com.example.schedulebackend.model.dto.DepartmentDTO;
import com.example.schedulebackend.model.dto.GroupDTO;
import com.example.schedulebackend.model.entities.Group;
import com.example.schedulebackend.repos.GroupRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    private final GroupRepo groupRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final DepartmentService departmentService;
    private final String itemName;

    public GroupService(GroupRepo groupRepo,
                        ItemHandler itemHandler,
                        DbaService dbaService,
                        DepartmentService departmentService,
                        @Value("group") String itemName) {
        this.groupRepo = groupRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.departmentService = departmentService;
        this.itemName = itemName;
    }

    public void create(GroupDTO groupDTO) {
        itemHandler.handleIsIdNull(groupDTO.getId(), itemName);
        saveToDB(groupDTO);
    }

    public void update(GroupDTO groupDTO) {
        dbaService.get(groupRepo, itemName, groupDTO.getId());
        saveToDB(groupDTO);
    }

    public GroupDTO get(long id) {
        Group group = dbaService.get(groupRepo, itemName, id);
        return GroupDTO.toDTO(
                group,
                DepartmentDTO.toDTO(group.getDepartment(), null)
        );

    }

    public void delete(long id) {
        dbaService.get(groupRepo, itemName, id);
        groupRepo.deleteById(id);
    }

    public List<GroupDTO> getAll() {
        return groupRepo.findAll().stream().map(this::toDTO).toList();
    }


    public void saveToDB(GroupDTO groupDTO) {
        groupRepo.save(Group.toEntity(
                groupDTO,
                departmentService.getDepartment(groupDTO.getDepartmentDTO())
        ));
    }

    public Group getGroup(GroupDTO groupDTO) {
        if (groupDTO != null) {
            return dbaService.get(groupRepo, itemName, groupDTO.getId());
        }
        return null;
    }

    public GroupDTO toDTO(Group group) {
        return GroupDTO.toDTO(
                group,
                DepartmentDTO.toDTO(group.getDepartment(), null)
        );
    }
}
