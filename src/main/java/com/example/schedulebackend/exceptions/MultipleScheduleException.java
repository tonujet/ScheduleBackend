package com.example.schedulebackend.exceptions;

import lombok.Getter;

@Getter
public class MultipleScheduleException extends RuntimeException {
    private final String name = "FullSchedule";
    public MultipleScheduleException(String message) {
        super(message);
    }
}
