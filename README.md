## 浙江大学软工project后端
## e-change

项目组织分层参考 https://www.jianshu.com/p/53091c647ccc

1. *entity*文件夹是数据库实体层，与数据库表定义相同，自动生成

2. *dao*文件夹是持久层，实现数据库访问与修改等操作接口，采用jpa语法编写

3. *service*文件夹是数据服务接口层，实现controller的业务逻辑，对数据进行处理并通过dao与数据库交互

4. *controller*文件夹为控制层，实现对外的http请求接口，调用service中的方法进行处理

5. *util*文件夹中实现一些工具类

**e-change.sql**为建表文件

安装MySQL，用建表文件创建所有表，在idea工程中选择好数据库源，安装所有依赖

idea有很多快捷方式，比如自动生成接口的实现类等，可以省很多力气

1
