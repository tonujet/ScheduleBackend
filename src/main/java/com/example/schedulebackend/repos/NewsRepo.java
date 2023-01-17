package com.example.schedulebackend.repos;

import com.example.schedulebackend.model.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepo extends JpaRepository<News, Long> {

}
