package com.example.database_design.controller;
import com.example.database_design.dto.StudentDto;
import com.example.database_design.entity.*;
import com.example.database_design.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentRestConroller {

    @Autowired
    StudentService studentService;

    @GetMapping(value = "/getAll")
    public List<StudentDto> getAllStudent_onLoad() {
        return studentService.getAll();
    }

    @PostMapping(value = "/save")
    public StudentDto saveStudent(@Validated @RequestBody StudentDto studentDto) {
        return studentService.save(studentDto);
    }

    @GetMapping(value = "/getOne/{id}")
    public StudentDto getOne(@PathVariable("id") Long id) {
        return studentService.getOne(id);
    }

    @PostMapping(value = "/update")
    public List<StudentDto> update(@RequestBody StudentDto studentDto) {
        return studentService.update(studentDto);
    }

    @PostMapping(value = "/delete/{id1}")
    public List<StudentDto> delete(@PathVariable("id1") Long id) {
        return studentService.delete(id);
    }

    @GetMapping(value = "/getSemesters/{id}")
    public List<Semester> getSemesters(@PathVariable("id") Long id1) {
        return studentService.getSemesters(id1);
    }

    @GetMapping(value = "/getCourses/{id}")
    public List<Course> getCourse(@PathVariable("id") Long id) {
        return studentService.getCourses(id);
    }
}