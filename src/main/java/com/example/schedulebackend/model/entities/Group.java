package com.example.schedulebackend.model.entities;

import com.example.schedulebackend.model.dto.GroupDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "university_groups")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonManagedReference
    private Department department;

    private String course;

    @OneToMany(mappedBy = "group")
    @JsonBackReference
    private List<Student> students;

    @OneToMany(mappedBy = "group")
    @JsonBackReference
    private List<Schedule> schedules;


    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    public static Group toEntity(GroupDTO groupDTO, Department d){
       return Group.builder()
               .id(groupDTO.getId())
               .name(groupDTO.getName())
               .course(groupDTO.getCourse())
               .department(d)
               .build();
    }

    @PreRemove
    private void preRemove() {
        for (Schedule s: schedules) {
            s.setGroup(null);
        }
        for (Student s: students) {
            s.setGroup(null);
        }
    }

}
