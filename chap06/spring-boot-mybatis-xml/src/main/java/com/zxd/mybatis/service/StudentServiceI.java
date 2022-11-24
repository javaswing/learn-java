package com.zxd.mybatis.service;

import com.zxd.mybatis.domain.Student;

import java.util.List;

public interface StudentServiceI {

    Student findById(Long id);

    int insert(Student student);

    int update(Student student);

    boolean deleteById(Long id);

    List<Student> queryStudentByPaged(Integer page, Integer pageSize);
}
