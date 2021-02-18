package com.example.database_design.repository;

import com.example.database_design.entity.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, Long> {

    boolean existsByBatchName(String s);
}
