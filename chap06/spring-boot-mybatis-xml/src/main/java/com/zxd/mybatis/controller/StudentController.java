package com.zxd.mybatis.controller;

import com.zxd.mybatis.domain.Student;
import com.zxd.mybatis.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("student")
public class StudentController {

    private StudentServiceI studentServiceI;

    @Autowired
    public StudentController(StudentServiceI studentServiceI) {
        this.studentServiceI = studentServiceI;
    }

    @GetMapping("/{id}")
    public Student selectOne(@PathVariable("id") Long id) {
        return studentServiceI.findById(id);
    }

    @PostMapping
    public boolean createStudent(@RequestBody Student student) {
        Integer result =  studentServiceI.insert(student);
        return result > 0;
    }
}
