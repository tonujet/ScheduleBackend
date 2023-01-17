package com.example.schedulebackend.repos;

import com.example.schedulebackend.model.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {


}
