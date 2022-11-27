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