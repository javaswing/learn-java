package com.zxd.mybatis.controller;

import com.zxd.mybatis.domain.Student;
import com.zxd.mybatis.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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

    @GetMapping(params = {"page", "size"})
    public List<Student> getStudentByParams(@RequestParam("page") int page, @RequestParam("size") int size) {
        return studentServiceI.queryStudentByPaged(page,size);
    }
}
