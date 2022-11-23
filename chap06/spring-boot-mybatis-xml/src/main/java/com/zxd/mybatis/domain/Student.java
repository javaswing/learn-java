package com.zxd.mybatis.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//@Alias("Student")
// alias 同样也可以在 mybatis-config.xml中配置使用，也可以在注解中使用
@Data
public class Student implements Serializable {
    private static final long serialVersionUID = -91969758749726312L;

    private Long id;
    private String name;
    private Integer age;

    private Date createTime;
}
