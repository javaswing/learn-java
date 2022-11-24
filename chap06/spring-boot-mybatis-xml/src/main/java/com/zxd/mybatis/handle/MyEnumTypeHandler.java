package com.zxd.mybatis.handle;

import com.zxd.mybatis.annotation.EnumValue;
import com.zxd.mybatis.enums.StudentSexEnum;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 处理WeekDayEnum枚举
 *
 * @link https://juejin.cn/post/6953076996310040589
 */
@MappedTypes(value = {StudentSexEnum.class})
public class MyEnumTypeHandler<E extends Enum<E>> extends BaseTypeHandler<E> {

    private final Class<E> type;
    private final E[] enums;

    public MyEnumTypeHandler(Class<E> type) {
        if (type == null) {
            throw new IllegalArgumentException("Type argument cannot be null");
        }
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null) {
            throw new IllegalArgumentException(type.getSimpleName() + " does not represent an enum type.");
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            EnumValue annotation = declaredField.getAnnotation(EnumValue.class);
            if (annotation == null) {
                continue;
            }
            declaredField.setAccessible(true);
            Object paramValue = null;
            try {
                // 获取到设置了EnumValue注解字段的value值
                paramValue = declaredField.get(parameter);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            ps.setObject(i, paramValue);
            return;
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        Field[] declaredFields = type.getDeclaredFields();
        Field enumField = null;
        Object value4Db = null;
        for (Field declaredField : declaredFields) {
            EnumValue annotation = declaredField.getAnnotation(EnumValue.class);
            if (annotation == null) {
                continue;
            }
            enumField = declaredField;
            value4Db = rs.getObject(columnName, enumField.getType());
            break;
        }
        if (enumField == null) {
            return getResultByOrdinal(rs.getInt(columnName), rs.wasNull());
        }
        enumField.setAccessible(true);
        for (E anEnum : enums) {
            try {
                Object value = enumField.get(anEnum);
                if (value.equals(value4Db)) {
                    return anEnum;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return getResultByOrdinal(rs.getInt(columnName), rs.wasNull());
    }

    private E getResultByOrdinal(int anInt, boolean b) {
        int ordinal = anInt;
        if (ordinal == 0 && b) {
            return null;
        }
        return toOrdinalEnum(ordinal);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return getResultByOrdinal(rs.getInt(columnIndex), rs.wasNull());
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return getResultByOrdinal(cs.getInt(columnIndex), cs.wasNull());
    }

    private E toOrdinalEnum(int ordinal) {
        try {
            return enums[ordinal];
        } catch (Exception ex) {
            throw new IllegalArgumentException("Cannot convert " + ordinal + " to " + type.getSimpleName() + " by ordinal value.", ex);
        }
    }
}

