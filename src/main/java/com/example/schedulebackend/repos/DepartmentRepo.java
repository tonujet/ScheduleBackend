package com.example.schedulebackend.repos;


import com.example.schedulebackend.model.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {

}
