## MyBatis xml配置测试代码

## 重点

### 枚举（enum）在MyBatis中的处理
在这里进行简单的说明，官方文档对于这一块的处理过于粗糙。
1. 例如有这样一个枚举类
```java
public enum StudentSexEnum {
    UNKNOWN(0),
    MALE(1),
    FEMALE(2);

    // 添加 `@JsonValue` 之后，返回的json值是对应的`code`, 否则返回的为 `MAN` `FEMALE`之类的名称
    @JsonValue
    @Getter // lombok提供
    private int code;

    StudentSexEnum(int code) {
        this.code = code;
    }
}
```
对应的model是这样使用
```java
public class Student implements Serializable {
    private static final long serialVersionUID = -91969758749726312L;
    private Long id;
    private String name;
    private Integer age;
    private Date createTime;
    private StudentSexEnum gender;
}
```
2. 对应的`xml`配置转换
```xml
<insert id="insertByObject" parameterType="Student">
    insert into student (
        <if test="name != null and name!= ''">name,</if>
        <if test="age != null">age,</if>
        <if test="gender != null ">gender,</if>
    create_time
    ) values (
        <if test="name != null and name!= ''"> #{name},</if>
        <if test="age != null">#{age},</if>
        <if test="gender != null ">#{gender, typeHandler=org.apache.ibatis.type.EnumOrdinalTypeHandler},</if>
        sysdate()
    )
</insert>
```
这样进行配置仅仅能解决，插入数据时对应的枚举转换问题。在查询时仍然会出现异常。因此需要一个自定义的转换类
3. 创建转换类
```java
public class EnumStudentSexHandler extends BaseTypeHandler<StudentSexEnum> {

    private Class<StudentSexEnum> type;

    private final StudentSexEnum[] enums;

    /**
     * 设置配置文件设置的转换类以及枚举类内容，供其他方法更便捷高效的实现
     * @param type 配置文件中设置的转换类
     */
    public EnumStudentSexHandler(Class<StudentSexEnum> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        this.type = type;
        this.enums = type.getEnumConstants();
        if (this.enums == null)
            throw new IllegalArgumentException(type.getSimpleName()
                    + " does not represent an enum type.");
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    StudentSexEnum parameter, JdbcType jdbcType) throws SQLException {
        // baseTypeHandler已经帮我们做了parameter的null判断
        ps.setInt(i, parameter.getCode());

    }

    @Override
    public StudentSexEnum getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
        int i = rs.getInt(columnName);

        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位EnumStatus子类
            return locateEnumStatus(i);
        }
    }

    @Override
    public StudentSexEnum getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
        int i = rs.getInt(columnIndex);
        if (rs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位EnumStatus子类
            return locateEnumStatus(i);
        }
    }

    @Override
    public StudentSexEnum getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        // 根据数据库存储类型决定获取类型，本例子中数据库中存放INT类型
        int i = cs.getInt(columnIndex);
        if (cs.wasNull()) {
            return null;
        } else {
            // 根据数据库中的code值，定位EnumStatus子类
            return locateEnumStatus(i);
        }
    }

    /**
     * 枚举类型转换，由于构造函数获取了枚举的子类enums，让遍历更加高效快捷
     * @param code 数据库中存储的自定义code属性
     * @return code对应的枚举类
     */
    private StudentSexEnum locateEnumStatus(int code) {
        for(StudentSexEnum status : enums) {
            if(status.getCode()==(Integer.valueOf(code))) {
                return status;
            }
        }
        throw new IllegalArgumentException("未知的枚举类型：" + code + ",请核对" + type.getSimpleName());
    }
}
```
然后在对应的xml配置中进行修改，就可以完对于一个枚举的日常操作。
```xml
<resultMap id="StudentMap" type="com.zxd.mybatis.domain.Student">
    <result property="id" column="id" jdbcType="INTEGER"/>
    <result property="name" column="name" jdbcType="VARCHAR"/>
    <result property="age" column="age" jdbcType="INTEGER"/>
    <result property="createTime"  column="create_time"    />
    <result property="gender" column="gender" jdbcType="INTEGER" typeHandler="com.zxd.mybatis.utils.EnumStudentSexHandler"/>
</resultMap>
```

也可以分别这样进行配置：
- 直接在mysql-config.xml中进行如下配置：
```xml
<typeHandlers>
    <typeHandler handler="com.zxd.mybatis.utils.EnumStudentSexHandler" javaType="com.zxd.mybatis.enums.StudentSexEnum"/>
</typeHandlers>
```
移除在对应mapper.xml中的配置

总结：
对于初级且少的枚举可以采用此方法。缺点很明显：每添加一个枚举，我们都要写一个对应的转换类。如果多了会直接崩溃。
对于像这种字典类型的处理，对于前端最稳妥的方案。应该是在某个时刻把字典一次全部返回，让前端进行缓存。




## 参考
1. [MyBatis对于Java对象里的枚举类型处理](https://www.cnblogs.com/jeffen/p/6380724.html)
2. [Java、Mysql、MyBatis 中枚举 enum 的使用](https://yulaiz.com/java-mysql-enum/)