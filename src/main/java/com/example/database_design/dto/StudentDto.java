package com.example.database_design.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class StudentDto {
    private long stuId;
    @NotBlank(message = "Name can to be null or empty")
    private String studentName;
    private long depId;
    private String depName;
    private long batchId;
    private String batchName;
    private long semesterId;
    private String semesterName;
    private List<String> courseNames;
}
