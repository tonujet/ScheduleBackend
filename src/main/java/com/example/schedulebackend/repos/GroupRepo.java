package com.example.schedulebackend.repos;


import com.example.schedulebackend.model.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepo extends JpaRepository<Group, Long> {

}
