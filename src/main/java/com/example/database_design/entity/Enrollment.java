package com.example.database_design.entity;

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
public class Enrollment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long enrollId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enrollment_student")
    private Student student;

}