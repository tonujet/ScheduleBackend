package com.example.schedulebackend.model.options;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Option {
    @JsonProperty
    String name;
    @JsonProperty
    String type;
    @JsonProperty
    String data;
    @JsonProperty
    Boolean nullable;

    public Option(String name, String type, Boolean nullable) {
        this.name = name;
        this.type = type;
        this.nullable = nullable;
    }

    public Option(String name, String type, String data, Boolean nullable) {
        this.name = name;
        this.type = type;
        this.data = data;
        this.nullable = nullable;
    }
}
