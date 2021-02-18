package com.example.database_design.repository;

import com.example.database_design.entity.Course;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {

    boolean existsByCourseName(String s);
}
