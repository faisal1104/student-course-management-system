package com.example.database_design.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Semester implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long semesterId;

    @Column
    private String semesterName;

    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "semester_course",
            joinColumns = @JoinColumn(name = "semester_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    public Semester(String semesterNumber) {
        this.semesterName = semesterNumber;
    }
}
