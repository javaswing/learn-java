package com.zxd.mybatis.service.impl;

import com.zxd.mybatis.domain.Student;
import com.zxd.mybatis.mapper.StudentMapper;
import com.zxd.mybatis.service.StudentServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentServiceI {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findById(Long id) {
        return studentMapper.findById(id);
    }

    @Override
    public int insert(Student student) {
        return studentMapper.insertByObject(student);
    }

    @Override
    public int update(Student student) {
        return studentMapper.updateStudent(student);
    }

    @Override
    public boolean deleteById(Long id) {
        return studentMapper.deleteById(id) > 0;
    }
}
