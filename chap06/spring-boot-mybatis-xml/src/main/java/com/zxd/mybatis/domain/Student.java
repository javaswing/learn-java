package com.zxd.mybatis.domain;

import com.zxd.mybatis.enums.StudentSexEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//@Alias("Student")
// @Alias等同于在 mybatis-config.xml如下配置
//   <typeAliases>
//        <typeAlias type="com.zxd.mybatis.domain.Student" alias="Student"/>
//    </typeAliases>
// 这里仅仅是用来设置别名，设置扫描目录是另一个设置
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -91969758749726312L;

    private Long id;
    private String name;
    private Integer age;

    private Date createTime;

    private StudentSexEnum gender;
}
