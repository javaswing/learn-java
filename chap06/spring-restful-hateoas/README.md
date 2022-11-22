## 说明
这个章节主要介绍了restful的基本用法，同时也介绍了一新的restful模式`hateoas`.更多资料可以参考：
- https://spring.io/projects/spring-hateoas
- https://www.cnblogs.com/kaixinyufeng/p/8283289.html

## 坑
- 书上的hateoas版本为0.5.x，写这个代码样例的时候最新版本为：`1.5.2`。在hateoas这个包的变化很大。自己也是参考了另一个[项目](https://github.com/saechimdaeki/SpringInAction5)才跑通。
- 另外我把`tacocloud-security`这一个子包合并到`tacocloud-api`包中。原因：如果在单独的Modules中对于 Spring-Security的设置并没有起作用，不知道为什么。放到一个项目就正常了。很是纳闷。

