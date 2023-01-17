package com.example.schedulebackend.repos;


import com.example.schedulebackend.model.entities.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepo extends JpaRepository<Discipline, Long> {

}
