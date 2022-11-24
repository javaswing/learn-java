package com.zxd.mybatis.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import com.zxd.mybatis.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum StudentSexEnum {
    UNKNOWN(0, "未知"),
    MALE(1, "男"),
    FEMALE(2, "女");

//    // 添加 `@JsonValue` 之后，返回的json值是对应的`code`, 否则返回的为 `MAN` `FEMALE`之类的名称
//    @JsonValue
//    @Getter // lombok提供
//    private int code;


    @JsonValue
    @EnumValue
    private final int code;

//    @JsonValue
//    @EnumValue
    private final String desc;

    StudentSexEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
