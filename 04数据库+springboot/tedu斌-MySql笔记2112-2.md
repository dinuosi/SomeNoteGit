### 课程回顾:
1. 数据库相关SQL
- 查询所有 show databases;
- 创建 create database db1 charset=utf8/gbk;
- 查看数据库信息: show create database db1;
- 删除数据库: drop database db1;
- 使用: use db1;
2. 表相关SQL
- 创建表: create table t1(name varchar(20),age int) charset=utf8/gbk;
- 查询所有: show tables;
- 查询表信息: show create table t1;
- 表字段: desc t1; 
- 删除表: drop table t1;
- 修改表名: rename table t1 to t2;
- 添加表字段: alter table t1 add gender varchar(5) first/after xxx;
- 删除表字段: alter table t1 drop gender;
- 修改表字段: alter table t1 change 原名 新名 新类型;
3. 数据相关SQL
- 插入: insert into t1(字段1名,字段2名) values(值1,值2),(值1,值2),(值1,值2);
- 查询: select name,age from t1 where 条件;
- 修改: update t1 set xxx=xxx,xxx=xxx where 条件;
- 删除: delete from t1 where 条件;

### 综合练习题2
1. 创建数据库newdb1, 字符集utf8并使用
create database newdb1 charset=utf8;
use newdb1;
2. 在数据库中创建员工表emp 字段:id,name,sal(工资),deptId(部门id) 字符集utf8
create table emp(id int,name varchar(50),sal int,deptId int)charset=utf8;
3. 创建部门表dept 字段:id,name,loc(部门地址) 字符集utf8
create table dept(id int,name varchar(20),loc varchar(50))charset=utf8;
4. 部门表插入以下数据: 1 神仙部 天庭       2 妖怪部  盘丝洞
insert into dept values(1,'神仙部','天庭'),(2,'妖怪部','盘丝洞');
5. 员工表插入一下数据:  1 悟空 5000 1  ,   2 八戒  2000       1  ,      3 蜘蛛精 8000  2  ,  4 白骨精 9000  2
insert into emp values(1,'悟空',5000,1),(2,'八戒',2000,1),(3,'蜘蛛精',8000,2),(4,'白骨精',9000,2);
6. 查询工资6000以下的员工姓名和工资 
select name,sal from emp where sal<6000;
7. 修改神仙部的名字为取经部	  
update dept set name='取经部' where name='神仙部';
8. 给员工添加奖金comm字段 
alter table emp add comm int;
9. 修改员工表中部门id为1的 奖金为500 
update emp set comm=500 where deptId=1;
10. 把取经部的地址改成五台山 
update dept set loc="五台山" where name='取经部';
11. 修改奖金字段为性别gender字段 类型为varchar
alter table emp change comm gender varchar(5);
12. 修改孙悟空和猪八戒性别为男  
update emp set gender='男' where deptId=1;
13. 删除没有性别的员工(null不能用= 要用is)
delete from emp where gender is null;
14. 删除性别字段  
alter table emp drop gender;
15. 删除表  和 删除数据库 
drop table emp;
drop table dept;
drop database newdb1;  

### SQL分类
- DDL: 数据定义语言, 包括: 数据库向和表相关的SQL
- DML: 数据操作语言, 包括: 增删改查  
- DQL: 数据查询语言, 只包括查询 select  
- TCL:  事务控制语言, 包含和事务相关的内容 
- DCL: 数据控制语言, 包含用户管理,权限分配相关SQL  

### *.sql 批处理文件  
- 把老师的emp.zip解压出来得到一个emp.sql文件, 把这个文件放到某个盘的根目录 比如 D盘根目录, 然后在命令行执行以下指令
- 格式: source 路径;              举例: source d:/emp.sql;     
- 测试: 
	show databases;  检查里面是否多了一个empdb;
	show tables;  会出现两个表  emp 和dept
	select * from emp;  检查里面的数据
- 如果出现乱码执行  set names utf8;

### 数据类型
- 整数:  int(m)和bigint(m) , bigint相当于Java中的long, m代表显示长度,m=5 存18  得到00018,  需要结合zerofill关键字使用
	举例: create table t1(age int(5) zerofill); 
	insert into t1 values(18);
	select * from t1;
- 浮点数: double(m,d)  m代表总长度, d代表小数长度   m=5,d=3  23.123
	举例: create table t2(price double(5,3));
		insert into t2 values(54.321);
		insert into t2 values(54.332211);
		insert into t2 values(5433.2211);   //会报错或出现99.999  
- 字符串:
	- char(m): 固定长度,  m=5 存"abc"  占5个字符长度, 执行效率略高,当存储内容长度固定时使用,比如:性别    m最大值255
	- varchar(m):可变长度, m=5 存"abc"  占3个字符长度,更节省存储空间,m最大值65535, 长度在255以内的建议使用  
	- text(m):可变长度,	m最大值65535 , 建议长度大于255时使用  
- 日期:
	- date:保存年月日
	- time:保存时分秒
	- datetime:保存年月日时分秒,默认值null
	- timestamp(时间戳举例1970年1月1日的毫秒数):保存年月日时分秒,默认值为当前系统时间
	测试:
	create table t3(t1 date,t2 time,t3 datetime,t4 timestamp);
	insert into t3 values("2021-11-22","10:38:20",null,null);
	insert into t3 values(null,null,"2021-11-22 10:38:20",null);   
### 主键约束
- 主键: 表示数据唯一性的字段称为主键  
- 约束: 创建表时,给表字段添加的限制条件  
- 主键约束:限制主键的值 唯一且非空.
- 举例: create table t4(id int primary key,name varchar(20));
	insert into t4 values(1,'aaa');
	insert into t4 values(1,'bbb');     报错   重复数据
	insert into t4 values(null,'ccc');   报错  不能为null
### 主键约束+自增 
- 自增规则:从历史最大值+1   
create table t5(id int primary key auto_increment,name varchar(20));
insert into t5 values(null,'aaa');
insert into t5 values(null,'bbb');
insert into t5 values(10,'ccc');
insert into t5 values(null,'ddd');
delete from t5 where id>=10;
insert into t5 values(null,'eee');
- truncate table t5;   //把表删除 并重新创建   此时计数器才会清零 

### 去重distinct
1. 查询员工表中有哪几种不同的工作?
select distinct job from emp;
2. 查询员工表中有哪几个不同的部门id?  
select distinct dept_id from emp;
### is null 和 is not null
1. 查询没有上级领导的员工姓名
select name from emp where manager is null;
2. 查询有上级领导的员工姓名
select name from emp where manager is not null; 
### and和or
- 多个条件同时满足时使用and
- 多个条件满足一个就可以时 使用or
1. 查询1号部门工资高于2000的员工信息
select * from emp where dept_id=1 and sal>2000;
2. 查询3号部门的员工或者工资等于5000的员工信息
select * from emp where dept_id=3 or sal=5000;
3. 查询孙悟空和猪八戒的员工信息
select * from emp where name="孙悟空" or name="猪八戒";
### 比较运算符   > < >= <=  =  !=和<>
1. 查询不是程序员的姓名和工资
select name,sal from emp where job!="程序员";
select name,sal from emp where job<>"程序员";
### 两者之间between x and y  包含x和y
1. 查询工资在2000到3000之间的员工信息
select * from emp where sal between 2000 and 3000;
select * from emp where sal not between 2000 and 3000;     范围取反
### in关键字
- 当查询某个字段的值为多个值的时候使用in
1. 查询工资为3000,1500,5000的员工信息
select * from emp where sal=3000 or sal=1500 or sal=5000;
select  * from emp where sal in(3000,1500,5000);
2. 查询孙悟空,猪八戒和白骨精的信息
select * from emp where name in('孙悟空','猪八戒','白骨精');

### 综合练习题
1. 查询1号部门有哪几种不同的工作?
select distinct job from emp where dept_id=1; 
2. 查询1号部门中有上级领导的员工信息
select * from emp where dept_id=1 and manager is not null;
3. 查询工作是程序员,销售和人事的员工信息
select * from emp where job in('程序员','销售','人事');
4. 查询有奖金的员工信息  
select * from emp where comm>0;
### 模糊查询like  
- %: 代表0或多个未知字符
- _:代表1个未知字符
- 举例:
		以x开头              x%
		以x结尾              %x
		包含x                 %x%
		第二个字符是x      _x%
		以x开头以y结尾     x%y
		第二个是x倒数第三个是y        _x%y__
1. 查询姓孙员工信息  
	select * from emp where name like "孙%";
2. 查询名字以精结尾的员工姓名
	select name from emp where name like "%精";
3. 查询工作第二个字是售的员工姓名和工作
	select name,job from emp where job like "_售%";
5. 查询名字中包含僧并且工资大于2000的员工姓名和工资
	select name,sal from emp where name like "%僧%" and sal>2000;
### 排序  order by
- 格式: order by 字段名 asc(默认升序)/desc降序;
1. 查询员工姓名和工资,按照工资升序
select name,sal from emp order by sal;
2. 查询员工姓名和工资,按照工资降序
select name,sal from emp order by sal desc;
3. 查询1号部门的姓名和工资并且按照工资降序排序
select name,sal from emp where dept_id=1 order by sal desc;
4. 查询每个员工的姓名,工资和部门id按照部门id升序排序,如果部门id相同则按照工资降序排序
select name,sal,dept_id from emp order by dept_id,sal desc;
### 分页查询 limit
- 格式: limit 跳过的条数,请求的条数(每页的条数)
- 举例:                  跳过的条数=(请求页数-1)*每页条数
	第一页的5条数据       limit 0,5
	第一页的10条            limit 0,10
	第2页的10条             limit 10,10
	第5页的10条             limit  40,10
	第8页的9条               limit 63,9   
	第15页的3条             limit 42,3
1. 查询工资最高的前三个人的信息
	select * from emp order by sal desc limit 0,3;
2. 查询员工的id和名字,请求第二页的3条数据
	select id,name from emp limit 3,3;
3. 查询工资最低的员工信息    
	select * from emp order by sal limit 0,1;
### 综合练习题
1. 查询员工表中3号部门工资高于1500的员工信息
	select * from emp where dept_id=3 and sal>1500;
2. 查询2号部门员工或者没有领导的员工信息
	select * from emp where dept_id=2 or manager is null;
3. 查询有领导的员工姓名,工资按照工资降序排序
	select name,sal from emp where manager is not null order by sal desc;
4. 查询2号和3号部门的员工姓名和入职日期hiredate 按照入职日期降序排序
	select name,hiredate from emp where dept_id in(2,3) order by hiredate desc;
5. 查询名字中包含僧和包含精的员工姓名
	select name from emp where name like "%僧%" or name like "%精%";
6. 查询工资高于2000的工作有哪几种?
	select distinct job from emp where sal>2000;
7. 查询工资升序第4页的2条数据
	select * from emp order by sal limit 6,2;
### 别名 
select name from emp;
select name as "名字" from emp;
select name "名字" from emp;
select name 名字 from emp;
### 数值计算 
1. 查询每个员工的姓名,工资和年终奖(3个月的工资)
	select name,sal,3*sal 年终奖 from emp;
2. 给3号部门的员工每人涨薪5块钱  
	update emp set sal=sal+5 where dept_id=3; 
### 聚合函数
- 可以对查询的多条数据进行统计查询,  统计方式包括: 求平均值,最大值,最小值,求和,计数
1. 平均值avg(字段名)
- 查询2号部门的平均工资
select avg(sal) from emp where dept_id=2;
- 查询销售的平均工资
select avg(sal) from emp where job="销售";
2. 最大值max(字段名)
- 查询1号部门的最高工资
select max(sal) from emp where dept_id=1;
3. 最小值min(字段名)
- 查询1号部门的最低工资
select min(sal) from emp where dept_id=1;
4. 求和sum(字段名)
- 查询1号部门的工资总和
select sum(sal) from emp where dept_id=1;
5. 计数count(*)
- 查询工资高于2000的员工人数
select count(*) from emp where sal>2000;

- 查询2号部门的平均工资,最高工资,最低工资,工资总和,人数
select avg(sal) 平均工资,max(sal) 最高工资,min(sal) 最低工资,sum(sal) 工资总和,count(*) 人数 from emp where dept_id=2;
### 综合练习
1. 查询1号部门名字中包含僧的员工信息
select * from emp where dept_id=1 and name like "%僧%";
2. 查询2号和3号部门中工资高于1500的员工人数
select count(*) from emp where dept_id in(2,3) and sal>1500;
3. 查询名字里面包含精的最高工资
select max(sal) from emp where name like "%精%";
4. 查询程序员的平均工资
select avg(sal) from emp where job="程序员";
5. 查询销售的最低工资
select min(sal) from emp where job="销售";
6. 查询有领导的员工人数 
select count(*) from emp where manager is not null;
7. 查询3号部门的人数和平均工资,起别名
select count(*) 人数,avg(sal) 平均工资 from emp where dept_id=3;

### 分组查询group by  
- 将某个字段相同值的数据划分为一组, 然后以组为单位进行统计查询  

1. 查询每个部门的平均工资
select dept_id,avg(sal) from emp group by dept_id;
2. 查询每种工作的平均工资
select job,avg(sal) from emp group by job;
3. 查询每个部门的最高工资
select dept_id,max(sal) from emp group by dept_id;
4. 查询每种工作的人数
select job,count(*) from emp group by job;
5. 查询每个部门工资高于2000的人数
select dept_id,count(*) from emp where sal>2000 group by dept_id;
6. 查询每个部门有领导的员工的人数
select dept_id,count(*) from emp where manager is not null group by dept_id;

### having关键字
- where后面只能写普通字段的条件,不能写聚合函数函数.
- having关键字 作用和where类似都是用来添加条件的, 但是having后面专门写聚合函数条件,而且having要和group by分组查询结合使用, 写在分组关键字的后面
1. 查询每个部门的平均工资要求平均工资大于2000
select dept_id,avg(sal) from emp group by dept_id having avg(sal)>2000 ;
别名用法
select dept_id,avg(sal) a from emp group by dept_id having a>2000 ;
2. 查询每种工作的人数,只查询人数大于1 的
select job,count(*) c from emp group by job having c>1;
3. 查询每个部门的工资总和,只查询有领导的员工, 并且要求工资总和大于5400
select dept_id,sum(sal) s from emp where manager is not null group by dept_id having s>5400;
5. 查询每个部门的平均工资, 只查询工资在1000到3000之间的,并且过滤掉平均工资低于2000的
select dept_id,avg(sal) a from emp where sal between 1000 and 3000 group by dept_id having a>=2000;

### 各个关键字的顺序
- select * from 表名 where 普通字段条件 group by 分组字段名 having 聚合函数条件 order by 排序字段名 desc  limit 跳过条数,请求条数;

### 晚课练习题:
1. 查询工资大于等于3000的员工姓名和工资 
2. 查询1号部门的员工姓名和工作
3. 查询不是程序员的员工姓名和工作(两种写法) 
4. 查询奖金等于300的员工姓名,工资和工作  
5. 查询1号部门工资大于2000的员工信息
6. 查询3号部门或工资等于5000的员工信息
7. 查询出CEO和项目经理的名字
8. 查询工资为3000,1500和5000的员工信息
9. 查询工资不等于3000,1500和5000的员工信息
10. 查询工资在1000到2000之间的员工信息
11. 查询工资在1000到2000以外的员工信息
12. 查询有领导的员工姓名和领导id
13. 查询没有领导的员工姓名和领导id
14. 查询员工表中出现了哪几种不同的工作
15. 查询员工表中出现了那几个部门的id
16. 查询姓孙的员工姓名
17. 查询名字最后一个字是精的员工信息
18. 查询工作中包含销售的员工信息
19. 查询工作中第二个字是售的员工信息
20. 查询名字中包含僧的员工并且工资高于2000的员工信息
21. 查询1号和2号部门中工作以市开头的员工信息
22. 查询所有员工的姓名和工资 按照工资升序排序
23. 查询所有员工的姓名和工资 按照工资降序排序
24. 查询所有员工姓名 工资和部门id 按照部门id降序排序,如果部门id一致则按照工资升序排序
25. 查询员工表中3号部门工资高于1500的员工信息
26. 查询2号部门员工或者没有领导的员工信息
27. 查询有领导的员工姓名,工资按照工资降序排序
28. 查询2号和3号部门的员工姓名和入职日期hiredate 按照入职日期降序排序
29. 查询名字中包含僧和包含精的员工姓名
30. 查询工资高于2000的工作有哪几种?
31. 查询工资最高的前三个员工
32. 查询员工表按照id排序, 第2页的5条数据
33. 查询员工表按照id排序, 第3页的4条数据
34. 查询3号部门工资最低的员工姓名和工资
35. 查询工作不是人事的员工中工资降序第二页的3条数据 
36. 查询每个员工的姓名,工资和年终奖(年终奖=5个月的工资)
37. 给3号部门所有员工涨薪5块钱 
38. 查询没有领导的员工和3号部门的员工,工资降序取前三条
39. 查询2号部门的最高工资 
40. 查询有领导的员工中工资在1000到2000之间的人数
41. 查询3号部门的工资总和  
42. 查询程序员和销售的总人数 
43. 查询1号部门有领导的员工的平均工资 
44. 查询1号部门的最低工资和最高工资
45. 查询和销售相关的工作人数
46. 查询工资不是1500和3000的员工人数
47. 查询1号部门出现了哪几种工作
48. 查询每个部门的平均工资
49. 查询每种工作的人数
50. 查询每个部门的最高工资
51. 查询每种工作的最低工资
52. 查询每个部门的工资总和
53. 查询每个部门的工资总和,只查询有领导的员工, 并且要求工资总和大于5400









