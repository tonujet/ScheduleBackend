package com.example.schedulebackend.exceptions.handlers;


import com.example.schedulebackend.exceptions.ItemIsRedundantException;
import org.springframework.stereotype.Component;

@Component
public class ItemHandler {
    public void handleIsIdNull(long id, String itemName) {
        if (id != 0) {
            throw new ItemIsRedundantException(
                    "Input id is forbidden. Please delete field id=" + id, id, itemName);
        }
    }
}
