package com.zxd.mybatis.mapper;

import com.zxd.mybatis.domain.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {
    public Student findById(@Param("id") Long id);

    public int updateStudent(Student student);

    public int insertByObject(Student student);

    public int deleteById(Long id);
}
