package com.example.database_design.controller;

import com.example.database_design.repository.BatchRepository;
import com.example.database_design.repository.DepartmentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("")
public class IndexController {

    private final DepartmentRepository departmentRepository;
    private final BatchRepository batchRepository;

    public IndexController(DepartmentRepository departmentRepository, BatchRepository batchRepository) {
        this.departmentRepository = departmentRepository;
        this.batchRepository = batchRepository;
    }

    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/index");
    }

    @GetMapping("/index")
    public String getAll(Model m) {
        m.addAttribute("departmentList", departmentRepository.findAll());
        m.addAttribute("batchList", batchRepository.findAll());
        return "student/manage-student";
    }
}
