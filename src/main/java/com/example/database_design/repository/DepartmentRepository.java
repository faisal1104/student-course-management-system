package com.example.database_design.repository;

import com.example.database_design.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    boolean existsByDepartmentName(String s);
}
