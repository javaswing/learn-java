## Spring Properties

开发版本
- spring-boot 2.7.5

## 注意事项
### 1. Configuration Metadata

对于在`application.properties`或者`application.yml`中添加自定的`property`时，从这个版本需要添加额外的包。然后Re-Run就可以正常解析自定义的属性，更多详情内容就参考[这里](https://docs.spring.io/spring-boot/docs/2.7.5/reference/html/configuration-metadata.html#appendix.configuration-metadata.annotation-processor)。
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-configuration-processor</artifactId>
    <optional>true</optional>
</dependency>
```

### 2. 在程序启动时指定特定的环境可以通过以下几种方式

- 通过在jar命令启动时指定环境，例如：
```shell
java -jar spring-properites-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```
- 通过在系统变量中注入对应的变量名
```shell
$ export SPRING_PROFILES_ACTIVE=prod
```

注：根据自己的经验不过一般都推荐在docker启动时通过env进行环境变量的注入。比如：数据库链接、用户名、密码。更多在docker启动时注入参考：
- [Starting Spring Boot Application in Docker With Profile](https://www.baeldung.com/spring-boot-docker-start-with-profile)
- [https://github.com/hyeonsangjeon/springboot-properties-docker](https://github.com/hyeonsangjeon/springboot-properties-docker)
### 3. 通过使用`Profile`注解可以实现代码动态根据环境进行执行
```java
@Bean
@Profile("dev")
public CommandLineRunner dataLoader(IngredientRepository repo,
        UserRepository userRepo, PasswordEncoder encoder) {
        ...
}
```