package com.example.schedulebackend.model.options;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Option {
    @JsonProperty
    String name;
    @JsonProperty
    String inputType;
    @JsonProperty
    String dataSource;
    @JsonProperty
    String validatorName;
    @JsonProperty
    Boolean nullable;

    public Option(String name, String inputType, String dataSource, String validatorName, Boolean nullable) {
        this.name = name;
        this.inputType = inputType;
        this.dataSource = dataSource;
        this.validatorName = validatorName;
        this.nullable = nullable;
    }

    public Option(String name, String inputType, String validatorName, Boolean nullable) {
        this.name = name;
        this.inputType = inputType;
        this.validatorName = validatorName;
        this.nullable = nullable;
    }
}
