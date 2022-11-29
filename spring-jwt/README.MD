## Spring Boot + Spring Security + jwt
版本：
- Spring Boot : `2.7.6`
- Spring Security: `5.7.5`
- jjwt : `0.9.1`

目前该版本测试通过配置Jwt校验。网上目前大部分通过`WebSecurityConfigurerAdapter`来实现的。
该代码是通过`SecurityFilterChain`进行实现的。需要注意的是一定要在`java8`下运行，我在`openjdk 16`运行有异常。

## 参考
- https://www.inlighting.org/archives/spring-boot-security-jwt-restful
- https://juejin.cn/post/6844903810603696135

## 整合Swagger3

### 坑
1. 启动之后遇到` Failed to start bean 'documentationPluginsBootstrapper'; nested exception is java.lang.NullPointerException`异常
这个主要是要Spring Boot 2.6（包含2.7.x）之后 版本的默认匹配策略是 path-pattern-matcher，不修改会造成下方documentationPluginsBootstrapper报错，详情看[这里](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.6-Release-Notes)。
方案在yml中添加以下：
```yaml
spring:
  mvc:
    path-match:
      matching-strategy: ant_path_matcher

```

Swagger3的访问地址为：`http://localhost:8080/swagger-ui/index.html`


### 参考资源
- https://www.cnblogs.com/Subaru/p/16478402.html