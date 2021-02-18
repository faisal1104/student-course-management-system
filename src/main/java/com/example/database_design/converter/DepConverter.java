package com.example.database_design.converter;

import com.example.database_design.entity.Department;
import com.example.database_design.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public class DepConverter implements Converter<String, Department> {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Department convert(String id) {
        System.out.println("Converting Dep");

        List<Department> departmentList = departmentRepository.findAll();
        int indexTemp = Integer.parseInt(id);
        return departmentList.get(indexTemp - 1);
    }
}
