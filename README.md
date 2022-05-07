## 浙江大学软工project后端
## e-change

项目组织分层参考 https://www.jianshu.com/p/53091c647ccc

1. *entity*文件夹是持久层，与数据库表定义相同，自动生成

2. *dao*文件夹中实现数据库访问与修改等操作接口，采用jpa语法编写

3. *service*文件夹中是具体服务，对数据处理并通过dao与数据库交互

4. *controller*文件夹中实现对外的http接口，调用service中的方法进行处理

5. *util*文件夹中实现一些工具类

带有*_impl* 的文件夹中是接口类的implement

**e-change.sql**为建表文件

