package com.example.database_design.entity;

import lombok.Data;
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
public class Department implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long depId;
    @Column
    private String departmentName;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "dep_semester",
            joinColumns = @JoinColumn(name = "dep_id"),
            inverseJoinColumns = @JoinColumn(name = "semester_id"))
    private List<Semester> semesters;

    public Department(String departmentName) {
        this.departmentName = departmentName;
    }
}
