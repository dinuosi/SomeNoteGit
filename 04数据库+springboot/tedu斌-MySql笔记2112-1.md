# 数据库
- 学习数据库阶段内容,主要学习的就是如何对数据进行增删改查操作

# DBMS
- DataBaseManagementSystem,数据库管理系统(数据库软件) 
- 常见的DBMS:
	- MySQL:   Oracle公司产品 08年被Sun收购 09年Sun被Oracle , 原MySQL团队从Oracle离职又创建了MariaDB.  市占率第一 
	- Oracle:    Oracle公司当家产品  市占率第二       闭源产品
	- SqlServer:  微软公司产品 , 市占率第三   闭源产品    .net   编程语言+web服务软件+操作系统+数据库软件  
	- DB2: IBM公司产品   闭源产品      
	- SQLite: 轻量级数据库, 安装包只有几十k  , 只有最基础的增删改查功能  

     
### SQL
- Structured Query Language: 结构化查询语言.  
- 通过这门语言让程序员和数据库软件进行交流  
- 举例:       insert into person values("刘德华",18);  

### 和数据库软件建立连接   
1. 通过命令行/终端  和数据库软件建立连接  
	- 首先检查MySQL/MariaDB服务是否开启: 在我的电脑/此电脑上右键->管理  参见下图
![在这里插入图片描述](https://img-blog.csdnimg.cn/53f5b09e22e040b28522e6e920c69b3d.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)
	- 从开始菜单中找到MySQL/MariaDB->MySQL Client 打开客户端 , 输入自己的密码(如果用的是达内电脑尝试root或没密码直接回车)  显示Welcome ..... 说明建立好了连接
	- 退出指令: exit
	- 建立连接指令: mysql -uroot -p  回车后输入密码  再回车 
3. 通过三方的可视化工具和数据库软件建立连接 

### 数据库相关 
- 在MySQL数据库软件中保存数据需要先建库再建表,然后再往表里面添加数据
1. 查询所有数据库
- 格式: show databases;
2. 创建数据库
- 默认字符集格式: create database 数据库名;
- 指定字符集格式: create database 数据库名 charset=utf8/gbk;
- 举例:
	create database db1;
	create database db2 charset=utf8;
	create database db3 charset=gbk;
	show databases;   
3. 查询数据库信息
- 格式: show create database 数据库名;
- 举例:
	show create database db1;
	show create database db2;
	show create database db3;
4. 删除数据库
- 格式: drop database 数据库名;
- 举例:
	drop database db3;
	drop database db2;
5. 使用数据库
- 在执行表相关和数据相关的SQL语句之前必须使用了某个数据库,
- 格式: use 数据库名;
- 举例:
	use db1;
### 数据库相关练习题
1. 创建 mydb1和mydb2 数据库 字符集分别为utf8和gbk
create database mydb1 charset=utf8;
create database mydb2 charset=gbk;
2. 查询所有数据库检查是否创建成功
show databases;
3. 检查两个数据库的字符集是否正确
show create database mydb1;
show create database mydb2;
4. 先使用mydb2 再使用 mydb1
use mydb2;
use mydb1;
5. 删除两个数据库
drop database mydb1;
drop database mydb2;
### 表相关
- 执行表相关的SQL之前必须使用了某个数据库.
create database newdb1 charset=utf8;
use newdb1;
1. 创建表
- 格式: create table 名字(字段1名 类型,字段2名 类型,.....)charset=utf8/gbk;
- 举例: 
	create table person(name varchar(50),age int)charset=utf8;
	create table student(name varchar(50),chinese int,math int,english int);
2. 查询所有表
- 格式: show tables;
3. 查询表信息 
- 格式: show create table 表名;
- 举例:
	show create table person;
4. 查询表字段
- 格式: desc 表名;
- 举例:
	desc student;
5. 删除表
- 格式; drop table 表名;
- 举例:
	drop table student;
6. 修改表名
- 格式: rename table 原名 to 新名;
- 举例:
	rename table person to per;
### 表相关练习题
1. 创建数据库mydb3 字符集gbk 并使用
create database mydb3 charset=gbk;
use mydb3;
2. 创建t_hero英雄表, 有名字和年龄字段  默认字符集
create table t_hero(name varchar(50),age int);
3. 修改表名为hero
rename table t_hero to hero;
4. 查看表的字符集
show create table hero;
5. 查询表字段
desc hero;
6. 删除表
drop table hero;
7. 删除数据库
drop database mydb3;

### 表相关(续)
1. 添加表字段 
create database newdb2;
use newdb2;
create table emp(name varchar(30));
- 最后面添加格式: alter table emp add 字段名 类型;
- 最前面添加格式: alter table emp add 字段名 类型 first;
- 某字段后面添加格式: alter table emp add 字段名 类型 after 字段名;
- 举例:
alter table emp add age int; 
alter table emp add id int first;
alter table emp add gender varchar(10) after name;
2. 删除表字段
- 格式: alter table 表名  drop 字段名;
- 举例: 
	alter table emp drop gender;  
	alter table emp drop id;
3. 修改表字段
- 格式: alter table 表名 change 原名 新名 新类型;
- 举例:
	alter table emp change age gender varchar(10);
	
### 表相关SQL语句回顾:
1. 创建 create table 表名(name varchar(20),age int)charset=utf8/gbk;
2. 查询所有表  show tables;
3. 查询表信息  show create table 表名;
4. 表字段:  desc 表名;
5. 删除表: drop table 表名;
6. 修改表名:   rename table  原名 to 新名;
7. 添加表字段; alter table 表名 add 字段名 类型 first/after xxx;
8. 删除表字段: alter table 表名  drop 字段名;
9. 修改表字段; alter table 表名 change 原名 新名 新类型;
###  表相关练习题:
1. 创建数据库mydb4 字符集utf8并使用
create database mydb4 charset=utf8;
use mydb4;
2. 创建teacher表 有名字字段
create table teacher(name varchar(20));
3. 添加表字段: 最后添加age  最前面添加id , age前面添加salary工资
alter table teacher add age int;
alter table teacher add id int first;
alter table teacher add salary int after name;
4. 删除age字段     alter table teacher drop age;
5. 修改表名为t      rename table teacher to t;
6. 删除表        drop table t;
7. 删除数据库  drop database mydb4;

### 数据相关
create database mydb5 charset=utf8;
use mydb5;
create table person(name varchar(50),age int);
1. 插入数据
- 全表插入格式: insert into 表名 values(值1,值2);
- 指定字段插入格式: insert into 表名(字段1名,字段2名) values(值1,值2);
- 举例:
	insert into person values("tom",18);
	insert into person(name) values("jerry");
- 批量插入格式: 
	- 全表插入格式: insert into 表名 values(值1,值2),(值1,值2),(值1,值2);
	- 指定字段插入格式: insert into 表名(字段1名,字段2名) values(值1,值2),(值1,值2);
- 举例:
	insert into person values("lucy",20),("lily",21);
	insert into person(name) values("lilei"),("hanmeimei");
- 中文问题:
	insert into person values("刘德华",50);
	- 执行上面SQL语句报错的话  执行以下指令
	set names gbk;
	![在这里插入图片描述](https://img-blog.csdnimg.cn/de5272b726a94a24ad1f90536fa9498c.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)
2. 查询数据
- 格式: select 字段信息 from 表名 where 条件;
- 举例:
	select name from person;
	select name,age from person;
	select * from person;
	select * from person where age>20;
	select age from person where name="tom";
3. 修改数据
- 格式: update 表名 set 字段名=值,字段名=值 where 条件;
- 举例: 
	update person set name='汤姆' where name="tom";
	update person set name="杰瑞",age=15 where name="jerry";
	update person set age=10 where age is null;
4. 删除数据
- 格式: delete from 表名 where 条件;
- 举例:
	delete from person where name="汤姆";
 	delete from person where age>20;
	delete from person;



### 数据相关SQL回顾
1. 插入数据: insert into 表名(字段1名,字段2名) values(值1,值2),(值1,值2); 
2. 查询数据: select 字段信息 from 表名 where 条件;
3. 修改数据: update 表名 set 字段名=值,字段名=值 where 条件;
4. 删除数据; delete  from 表名 where 条件;
### 综合练习题
1. 创建数据库day1db  字符集utf8并使用
create database day1db charset=utf8;
use day1db;
2. 创建t_hero表, 有name字段 字符集utf8
create table t_hero(name varchar(20))charset=utf8;
3. 修改表名为hero
rename table t_hero to hero;
4. 最后面添加价格字段money, 最前面添加id字段, name后面添加age字段
alter table hero add money int;
alter table hero add id int first;
alter table hero add age int after name;
5. 表中添加以下数据:  1,李白,50,6888  2,赵云,30,13888    3,刘备,25,6888 
insert into hero values(1,'李白',50,6888),(2,'赵云',30,13888),(3,'刘备',25,6888);
6. 查询价格为6888的英雄名 
select name from hero where money=6888;
7. 修改刘备年龄为52岁
update hero set age=52 where name='刘备';
8. 修改年龄小于等于50岁的价格为5000
update hero set money=5000 where age<=50;
9. 删除价格为5000的信息
delete from hero where money=5000;
10. 删除表, 删除数据库
drop table hero;
drop database day1db;

### 综合练习题2
1. 创建数据库newdb1, 字符集utf8并使用
2. 在数据库中创建员工表emp 字段:id,name,sal(工资),deptId(部门id) 字符集utf8
3. 创建部门表dept 字段:id,name,loc(部门地址) 字符集utf8
4. 部门表插入以下数据: 1 神仙部 天庭       2 妖怪部  盘丝洞
5. 员工表插入一下数据:  1 悟空 5000 1  ,   2 八戒  2000       1  ,      3 蜘蛛精 8000  2  ,  4 白骨精 9000  2
6. 查询工资6000以下的员工姓名和工资 
7. 修改神仙部的名字为取经部	  
8. 给员工添加奖金comm字段 
9. 修改员工表中部门id为1的 奖金为500 
10. 把取经部的地址改成五台山 
11. 修改奖金字段为性别gender字段 类型为varchar
12. 修改孙悟空和猪八戒性别为男  
13. 删除没有性别的员工(null不能用= 要用is)
14. 删除性别字段  
15. 删除表  和 删除数据库 






### 常见错误提示:
1. You have an error in your SQL;   你的SQL语句中有个错  (检查SQL拼写)
2. database exists;      数据库已存在 
3. Column count doesn't match value count  插入的值的数量和列(表字段)的数量不一致 


### idea中配置数据库步骤 参考苍老师文档服务器
![在这里插入图片描述](https://img-blog.csdnimg.cn/2b86628cb62f46cea41eaee6d7fda49f.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)
- 推荐使用三方的可视化工具, 像SQLYog


