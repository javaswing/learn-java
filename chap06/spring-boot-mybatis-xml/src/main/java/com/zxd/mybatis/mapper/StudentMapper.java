package com.zxd.mybatis.mapper;

import com.zxd.mybatis.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
//此注解需要在 application.properties中的 mybatis.type-aliases-package=com.zxd.mybatis.domain 配合使用，
public interface StudentMapper {
    public Student findById(@Param("id") Long id);

    public int updateStudent(Student student);

    public int insertByObject(Student student);

    public int deleteById(Long id);
}
