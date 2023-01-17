package com.example.schedulebackend.repos;

import com.example.schedulebackend.model.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {

}
