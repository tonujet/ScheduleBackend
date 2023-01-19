package com.example.schedulebackend.configs;

import com.example.schedulebackend.model.options.Option;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OptionConfig {
    @Bean("V1scheduleOptions")
    public List<Option> scheduleOptions() {
        return List.of(
                new Option("name", "text", "textOnly", false),
                new Option("time", "data", "startsTime", null, true),
                new Option("classroom", "text", "textNumberDashValidator", true),
                new Option("dayOfWeek", "selectWeekDays", null, true),
                new Option("teacher", "data", "teachers", null, true),
                new Option("discipline", "data", "disciplines", null, true),
                new Option("group", "data", "groups", null, true)
        );
    }

    @Bean("V1disciplineOptions")
    public List<Option> disciplineOptions() {
        return List.of(
                new Option("name", "text", "textOnly", false)
        );
    }

    @Bean("V1groupOptions")
    public List<Option> groupOptions() {
        return List.of(
                new Option("name", "text", "textOnly", false),
                new Option("course", "text", "courseValidator", true),
                new Option("department", "data", "departments", null, true)

        );
    }

    @Bean("V1studentOptions")
    public List<Option> studentOptions() {
        return List.of(
                new Option("name", "text", "textOnly", false),
                new Option("email", "email", "emailValidator", false),
                new Option("phone", "phone", "phoneValidator", false),
                new Option("group", "data", "groups", null, true)
        );
    }

    @Bean("V1teacherOptions")
    public List<Option> teacherOptions() {
        return List.of(
                new Option("name", "text", "textOnly", false),
                new Option("surname", "text", "textOnly", false),
                new Option("email", "email", "emailValidator", false),
                new Option("phone", "phone", "phoneValidator", false)
        );
    }

    @Bean("V1departmentOptions")
    public List<Option> departmentOptions() {
        return List.of(
                new Option("name", "text", "textOnly", false),
                new Option("shortName", "text", "textNumberValidator", false),
                new Option("faculty", "data", "faculties", null, true)
        );
    }

    @Bean("V1newsOptions")
    public List<Option> newsOptions() {
        return List.of(
                new Option("title", "text", null, false),
                new Option("description", "textarea", null, false),
                new Option("text", "textarea", null, false),
                new Option("dateCreated", "date", "dateValidator", true)
        );
    }

    @Bean("V1facultyOptions")
    public List<Option> facultyOptions() {
        return List.of(
                new Option("name", "text", "textOnly", false),
                new Option("shortName", "text", "textOnly", false)
        );
    }
}
