package com.example.schedulebackend.services;

import com.example.schedulebackend.exceptions.ItemNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public class DbaService {
    public <T> T get(JpaRepository<T, Long> repo, String itemName, long id){
        return repo.findById(id).orElseThrow(() ->
                new ItemNotFoundException(itemName + " with id=" + id + " not found in DB", itemName));
    }
}
