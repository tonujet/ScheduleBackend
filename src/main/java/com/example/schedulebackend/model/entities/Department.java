package com.example.schedulebackend.model.entities;

import com.example.schedulebackend.model.dto.DepartmentDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "departments")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String shortName;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    @JsonManagedReference
    private Faculty faculty;

    @OneToMany(mappedBy = "department")
    @JsonBackReference
    private List<Group> groups;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    public static Department toEntity(DepartmentDTO dto, Faculty faculty){
        return Department.builder()
                .id(dto.getId())
                .name(dto.getName())
                .shortName(dto.getShortName())
                .faculty(faculty)
                .build();
    }

    @PreRemove
    private void preRemove() {
        for (Group g: groups) {
            g.setDepartment(null);
        }
    }
}
