package com.example.schedulebackend.repos;

import com.example.schedulebackend.model.entities.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByGroupId(long id);

}
