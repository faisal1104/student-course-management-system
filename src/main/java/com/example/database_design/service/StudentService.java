package com.example.database_design.service;

import com.example.database_design.dto.StudentDto;
import com.example.database_design.entity.*;
import com.example.database_design.repository.BatchRepository;
import com.example.database_design.repository.DepartmentRepository;
import com.example.database_design.repository.SemesterRepository;
import com.example.database_design.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final BatchRepository batchRepository;
    private final SemesterRepository semesterRepository;

    public StudentService(StudentRepository studentRepository, DepartmentRepository departmentRepository, BatchRepository batchRepository, SemesterRepository semesterRepository) {
        this.studentRepository = studentRepository;
        this.departmentRepository = departmentRepository;
        this.batchRepository = batchRepository;
        this.semesterRepository = semesterRepository;
    }

    public StudentDto save(StudentDto studentDtoTemp){
        Department department = departmentRepository.getOne(studentDtoTemp.getDepId());
        Batch batch = batchRepository.getOne(studentDtoTemp.getBatchId());
        Semester semester = semesterRepository.getOne(studentDtoTemp.getSemesterId());

        Student student = new Student();
        BeanUtils.copyProperties(studentDtoTemp, student);
        student.setDepartment(department);
        student.setBatch(batch);
        student.setSemester(semester);
        studentRepository.save(student);

        StudentDto studentDto = new StudentDto();
        BeanUtils.copyProperties(student, studentDto);
        studentDto.setDepName(department.getDepartmentName());
        studentDto.setBatchName(batch.getBatchName());
        studentDto.setSemesterName(semester.getSemesterName());

        List<Course> courseListTemp1 = student.getSemester().getCourses();
        List<String> courseNameTemmp;
        courseNameTemmp = new ArrayList<>();
        for (Course c : courseListTemp1) {
            courseNameTemmp.add(c.getCourseName());
        }
        studentDto.setCourseNames(courseNameTemmp);

        return studentDto;
    }

    public StudentDto getOne(Long id) {

        Student studentTemp = studentRepository.findById(id).get();

        StudentDto studentDtoTemp = new StudentDto();
        BeanUtils.copyProperties(studentTemp, studentDtoTemp);
        studentDtoTemp.setDepId(studentTemp.getDepartment().getDepId());
        studentDtoTemp.setDepName(studentTemp.getDepartment().getDepartmentName());
        studentDtoTemp.setSemesterId(studentTemp.getSemester().getSemesterId());
        studentDtoTemp.setSemesterName(studentTemp.getSemester().getSemesterName());

        studentDtoTemp.setBatchId(studentTemp.getBatch().getBatchId());
        studentDtoTemp.setBatchName(studentTemp.getBatch().getBatchName());
        return studentDtoTemp;
    }

    public List<StudentDto> update(StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getStuId()).get();

        student.setDepartment(departmentRepository.getOne(studentDto.getDepId()));
        student.setBatch(batchRepository.getOne(studentDto.getBatchId()));
        student.setSemester(semesterRepository.getOne(student.getSemester().getSemesterId()));
        student.setStudentName(studentDto.getStudentName());
        studentRepository.save(student);

        return getAllStudentDtos();
    }

    public List<StudentDto> delete(Long id) {
        studentRepository.deleteById(id);
        return getAllStudentDtos();
    }

    public List<Semester> getSemesters(Long id) {
        Department department = departmentRepository.findById(id).get();
        return department.getSemesters();
    }

    public List<Course> getCourses(Long id) {
        Semester semesterTemp = semesterRepository.findById(id).get();
        return semesterTemp.getCourses();
    }

    private List<StudentDto> getAllStudentDtos() {
        List<Student> studentList = studentRepository.findAll();
        List<StudentDto> studentDtoList = new ArrayList<>();
        List<Course> courseListTemp;
        List<String> courseNameTemmp;
        for (Student s : studentList) {
            courseListTemp = s.getSemester().getCourses();

            StudentDto sTemp = new StudentDto();
            BeanUtils.copyProperties(s, sTemp);
            sTemp.setDepName(s.getDepartment().getDepartmentName());
            sTemp.setBatchName(s.getBatch().getBatchName());
            sTemp.setSemesterName(s.getSemester().getSemesterName());

            courseNameTemmp = new ArrayList<>();
            for (Course c : courseListTemp) {
                courseNameTemmp.add(c.getCourseName());
            }
            sTemp.setCourseNames(courseNameTemmp);
            studentDtoList.add(sTemp);
        }
        return studentDtoList;
    }

    public List<StudentDto> getAll() {
        return getAllStudentDtos();
    }
}
