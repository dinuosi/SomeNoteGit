

### DBCP

* DataBaseConnectionPool: 数据库连接池
* 作用: 可以将数据库连接重用,避免频繁开关连接导致的资源浪费
![image](C:\Users\Administrator\Desktop\笔记\04\文件\image.png)


* 如何使用数据库连接池?
    * 从苍老师文档服务器中找到连接池的依赖


### 注册功能:

* 创建user表
use empdb;

create table user(id int primary key auto_increment,username varchar(20),password varchar(20),nick varchar(20));

* 登录的SQL语句: 
select count(*) from user where username='tom' and password='123456';

### SQL注入

select count(*) from user 

where username='abcd' and password='' or '1'='1' 

* 什么是SQL注入? 
用户往传值的地方传递进来了SQL语句导致原有SQL语句的逻辑发生改变,从而达到一些非法目的.这个过程叫做SQL注入.

### PreparedStatement

* 带有预编译效果的执行SQL语句的对象
* 通过此对象可以解决SQL注入的问题:
将编译SQL语句的时间点从执行时提前到了创建时, 在创建PreparedStatement对象时将SQL语句进行编译,此时用户输入的内容还没有放到SQL语句里面, 这时编译的好处是将SQL语句业务逻辑锁死, 之后再将用户输入的内容添加进去,这样原有SQL语句的逻辑就不会被用户输入的内容改动,从而避免了SQL注入的问题.



