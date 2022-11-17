## Spring Security
开发版本：
1. spring-boot-starter-security 2.7.5
2. spring-security-config/web 5.7.4

## 坑列表
1. 在这个版本中`SecurityConfig`中对于hasRole的处理，按照官方原代码进行跑是跑不通的。最查到原来hasRole里面的String不能 `ROLE_`开头，框架默认会有运行时添加前缀。字段一定不能以`"ROLE_"`开头。
更多可以看以下链接
- [spring-security-api](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/access/expression/SecurityExpressionOperations.html)
- [spring-security-doc](https://docs.spring.io/spring-security/reference/servlet/authorization/expression-based.html)
- [Chapter 4.3.1: configure(HttpSecurity http)-sample causes BeanCreationException ](https://github.com/habuma/spring-in-action-5-samples/issues/74)
> By default, if the supplied role does not start with ROLE_, it is added.

从查看的文档来看，好像说如果以ROLE_为开头，不会添加。但是好像这个版本并不是这个样子。还是不要以`ROLE_`开头为好。
2. `hasAuthority`中的变量必须以`ROLE_`开头。官方文档时说的是`By default, if the supplied role does not start with ROLE_, it is added.`。这个版本测试并不是这个样子。也是一个坑。
3. 如果(CSRF)跨站请求伪造没有关闭的情况，在所有表单中都要添加以下字段，否则到第二个表单会 403
```html
<input type="hidden" name="_csrf" th:value="${_csrf.token}"/>
```
4. 另外新版本的框架中`access`方法已经修改了参数类型，详情看[这里](https://github.com/spring-projects/spring-security/issues/11396) 。也就是说不能如下使用
```java
.antMatchers("/", "/**").access("permitAll")
```