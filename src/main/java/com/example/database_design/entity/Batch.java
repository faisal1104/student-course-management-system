package com.example.database_design.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Batch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long batchId;
    @Column
    private String batchName;

    public Batch(String batchName) {
        this.batchName = batchName;
    }
}
