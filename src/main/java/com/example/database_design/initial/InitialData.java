package com.example.database_design.initial;

import com.example.database_design.entity.*;
import com.example.database_design.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//@Component
public class InitialData implements CommandLineRunner {

    private final DepartmentRepository departmentRepository;
    private final BatchRepository batchRepository;
    private final CourseRepository courseRepository;
    private final SemesterRepository semesterRepository;
    private final EnrollmentRepository enrollmentRepository;

    public InitialData(DepartmentRepository departmentRepository, BatchRepository batchRepository, CourseRepository courseRepository, SemesterRepository semesterRepository, EnrollmentRepository enrollmentRepository) {
        this.departmentRepository = departmentRepository;
        this.batchRepository = batchRepository;
        this.courseRepository = courseRepository;
        this.semesterRepository = semesterRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Stream.of("32", "34", "36", "38", "39", "40")
                .filter(s -> !batchRepository.existsByBatchName(s))
                .map(Batch::new)
                .map(batchRepository::save)
                .forEach(l -> {
                    System.out.print(" ");
                });

        Stream.of("CSE-1001", "CSE-1002", "CSE-1003", "CSE-2201", "CSE-2202", "CSE-2203", "CSE-3301", "CSE-3302", "CSE-3303",
                "EEE-1001", "EEE-1002", "EEE-2201", "EEE-2202", "ETE-1001", "ETE-1002", "ETE-2201", "ETE-2202")
                .filter(s -> !courseRepository.existsByCourseName(s))
                .map(Course::new)
                .map(courseRepository::save)
                .forEach(l -> {
                    System.out.print(" ");
                });

        Semester s1 = new Semester("CSE-1st");
        Semester s2 = new Semester("CSE-2nd");
        Semester s3 = new Semester("CSE-3rd");
        Semester s4 = new Semester("EEE-1st");
        Semester s5 = new Semester("EEE-2nd");
        Semester s6 = new Semester("ETE-1st");
        Semester s7 = new Semester("ETE-2nd");

        List<Course> coursesTemp = courseRepository.findAll();

        s1.setCourses(Stream.of(coursesTemp.get(0), coursesTemp.get(1), coursesTemp.get(2))
                .collect(Collectors.toList()));
        s2.setCourses(Stream.of(coursesTemp.get(3), coursesTemp.get(4), coursesTemp.get(5))
                .collect(Collectors.toList()));
        s3.setCourses(Stream.of(coursesTemp.get(6), coursesTemp.get(7), coursesTemp.get(8))
                .collect(Collectors.toList()));
        s4.setCourses(Stream.of(coursesTemp.get(9), coursesTemp.get(10))
                .collect(Collectors.toList()));
        s5.setCourses(Stream.of(coursesTemp.get(11), coursesTemp.get(12))
                .collect(Collectors.toList()));
        s6.setCourses(Stream.of(coursesTemp.get(13), coursesTemp.get(14))
                .collect(Collectors.toList()));
        s7.setCourses(Stream.of(coursesTemp.get(15), coursesTemp.get(16))
                .collect(Collectors.toList()));

        Stream.of(s1, s2, s3, s4, s5, s6, s7)
                .map(semesterRepository::save)
                .forEach(l -> System.out.print(" "));

        Department d1 = new Department("CSE");
        Department d2 = new Department("EEE");
        Department d3 = new Department("ETE");
        d1.setSemesters(Stream.of(s1, s2, s3).collect(Collectors.toList()));
        d2.setSemesters(Stream.of(s4, s5).collect(Collectors.toList()));
        d3.setSemesters(Stream.of(s6, s7).collect(Collectors.toList()));
        departmentRepository.save(d1);
        departmentRepository.save(d2);
        departmentRepository.save(d3);
    }
}
