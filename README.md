**bbs**

**1.创建一个maven项目**
* 加载包
  * javax.servlet-api
  * json-lib
  * mysql-connector-java
  * jsp-api
  * commons-beanutils
* 创建资源包和Java包
  * 资源包：resource
  * Java包：java

**2.开始项目**
* 在Java包下分层
  * dao（数据层）
  * entity（实体类层）
  * service（业务逻辑层）
  * servlet（控制层）
  * untils（工具类层）
* 在webapp下建试图
  * js------存放js文件
  * css----存放css文件
  * web---存放页面
  * fonts--存放字体样式
  
**3.具体实现**
* untils
  * 封装数据库连接：DbUtlis.java
  * 封装查询修改语句：DaoUtlis.java
* entity
  * 用户实体类：Users.java
  * 帖子实体类：Tiezi.java``
* servlet
  * 登录和注册的servlet：LoginServlet.java
  * 帖子展示的servlet：TieziServlet.java
* service
  * 用户登录和用户注册的接口：LoginService.java
  * 帖子查询展示的接口：TieziService.java
  * 实现接口：impl
    * 实现用户登录和注册接口：LoginServiceImpl.java
    * 实现帖子查询展示接口：TieziServiceImpl.java
* dao
  * 用户登录和注册查询和插入接口：LoginDao.java
  * 帖子查询接口：TieziDao.java
  * impl
    * 实现用户登录和注册接口：LoginDaoImpl.java
    * 实现帖子查询接口：TieziDaoImpl.java
  
 fork过后新增的功能 2021年8月3日 

目的:

1.把当初的html页面升级为jsp的方式

2.对所学知识的一些集成和升级


已完成:

1.index 的右边是加精贴，这个是由管理员来加精的(ok)

2.管理员可以管理帖子并且登录后台，普通用户不可以登录后台(ok)

3.游客只能看不能回帖(ok)

4.shiro权限管理

5.回帖功能(ok)

6.热帖展示完成(ok)

7.关于分页的功能(ok) 手动的方式，没有通过插件，插入方式后面新增.

8.页面提供搜索功能(ok)

9.spring事务操作管理功能(ok)

10.添加缓存操作 (ok)

a.mybatis的缓存  ok 注意这个缓存是mybatis自己的缓存

b.spring框架的缓存 ok 这个是用的 ehcache第三方的缓存


待完成:

1.通过aop支持日志记录，例如用户访问的接口

2.mybatis之后分页插件和id自动生成插件

3.通过redis来进行缓存和分布式锁的支持

4.通过jenkins来自动部署

5.部分jar包通过nexus来支持

6.通过docker来部署项目

7.通过k8s来管理docker容器


备注: 暂无

