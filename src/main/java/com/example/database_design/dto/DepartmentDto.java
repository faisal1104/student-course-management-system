package com.example.database_design.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class DepartmentDto {
    private long depId;
    private String departmentName;
}
