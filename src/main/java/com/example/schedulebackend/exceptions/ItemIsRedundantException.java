package com.example.schedulebackend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemIsRedundantException extends RuntimeException{
    private final long id;
    private final String itemName;

    public ItemIsRedundantException(String message, long id, String itemName) {
        super(message);
        this.id = id;
        this.itemName = itemName;
    }
}
