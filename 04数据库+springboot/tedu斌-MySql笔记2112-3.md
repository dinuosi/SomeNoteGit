### 课程回顾
- 主键约束+自增  primary key auto_increment  
-  去重  distinct
- is null 和 is not null  
- and 和 or  
- 比较运算符  >  <  >=  <=  =  !=和<>
-  两者之间  between x and  y 
- in    
- 模糊查询  like          _一个未知   %0或多个未知字符
- 排序    order by 字段名 asc(升序默认)/desc降序
- 分页   limit 跳过的条数,请求的条数
- 别名       select name 名字 from emp;
- 数值计算   select sal*5 from emp;
- 聚合函数:   平均值avg()    最大值max  最小值min   求和sum  计数count(*)
- 分组查询:   group  by 字段名   
- having: 用于写聚合函数条件  
- 各个关键字的顺序:    select * from 表名 where 普通字段条件 group by 分组字段名 having 聚合函数条件 order by 排序字段名 limit 跳过条数,请求条数;


### 子查询(嵌套查询)
use empdb;
1. 查询工资大于2号部门平均工资的员工信息
select avg(sal) from emp where dept_id=2;
select * from emp where sal>(select avg(sal) from emp where dept_id=2);
2. 查询工资高于程序员最高工资的员工信息
select max(sal) from emp where job="程序员";
select * from emp where sal>(select max(sal) from emp where job="程序员");
3. 查询工资最高的员工信息
select * from emp where sal=(select max(sal) from emp);
4. 查询和孙悟空相同工作的员工信息
select * from emp where job=(select job from emp where name='孙悟空') and name!='孙悟空';
5. 查询拿最低工资员工的同事们的信息(同事指同一部门)
select min(sal) from emp;
select dept_id from emp where sal=(select min(sal) from emp);
select * from emp where dept_id=(select dept_id from emp where sal=(select min(sal) from emp)) and sal!=(select min(sal) from emp);

### 关联关系
- 指一个项目中创建的表和表之间存在的业务关系
- 有哪几种关系?
	- 一对一:有AB两张表,A表中一条数据对应B表中的一条数据, 同时B表中的一条数据也对应A表中的一条.
![在这里插入图片描述](https://img-blog.csdnimg.cn/af2235f1db11442996747bcfe7390e4b.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)

	- 一对多:有AB两张表,A表中一条数据对应B表中的多条数据, 同时B表中的一条数据对应A表中的一条.
![在这里插入图片描述](https://img-blog.csdnimg.cn/ac4369f24f254e628db08b65a8e5614c.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)
	- 多对多: 有AB两张表,A表中一条数据对应B表中的多条数据, 同时B表中的一条数据也对应A表中的多条.
![在这里插入图片描述](https://img-blog.csdnimg.cn/746708f94e234ea5a666dc9148c9a099.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)
### 表和表之间如何建立关系
- 一对一:  在AB 任意一张表里面添加一个建立关系的字段 指向另外一张表的主键 
- 一对多:  在一对多的两张表中, 在"多"的表里面添加建立关系的字段 指向另外一张表的主键  
- 多对多:  创建一个单独的关系表, 表里面有两个字段指向另外两个表的主键  
- 多对多举例:        苍老师: 小红,小花,小绿      传奇哥: 小花,小绿 
	create table t(id int primary key auto_increment,name varchar(20));
	create table s(id int primary key auto_increment,name varchar(20));
	create table t_s(tid int ,sid int);
	insert into t values(null,'苍老师'),(null,'传奇哥');
	insert into s values(null,'小红'),(null,'小花'),(null,'小绿');
	insert into t_s values(1,1),(1,2),(1,3),(2,2),(2,3);

	老师表:t   
	学生表:s  
	关系表:t_s
	1. 查询每个老师名字和对应的学生名字
	select t.name,s.name
	from t join t_s ts on t.id=ts.tid join s on s.id=ts.sid;
	2. 查询苍老师的学生姓名
	select s.name 
	from t join t_s ts on t.id=ts.tid join s on s.id=ts.sid where t.name="苍老师"; 







### 关联查询
- 查询存在关联关系的表的查询方式称为关联查询 
- 关联查询的方式包括: 等值连接, 内连接, 外连接 
### 等值连接
- 格式: select * from A,B where 关联关系 and 其它条件;
1. 查询工资高于2000的员工的姓名和对应的部门名
	select e.name,d.name 
	from emp e,dept d where e.dept_id=d.id and sal>2000;
2. 查询程序员的姓名,工资,部门名,部门地点   
	select e.name,sal,d.name,loc 
	from emp e,dept d where e.dept_id=d.id and job='程序员';
### 内连接
- 等值连接和内连接查询到的是一样的数据, 推荐使用内连接
- 格式: select * from A join B on 关联关系 where 其它条件;
1. 查询工资高于2000的员工的姓名和对应的部门名
	select e.name,d.name 
	from emp e join dept d on e.dept_id=d.id where sal>2000;
2. 查询程序员的姓名,工资,部门名,部门地点  
	select e.name,sal,d.name,loc 
	from emp e join dept d on e.dept_id=d.id where job='程序员';
### 外连接
- 等值连接和内连接查询到的是两个表的交集数据  
- 外连接查询到的是一张表的全部和另外一张表的交集  
- 格式: select * from A left/right join B on 关联关系 where 其它条件;
insert into emp(name) values("灭霸");
1. 查询所有员工姓名和对应的部门名
select e.name,d.name 
from emp e left join dept d on e.dept_id=d.id;
2. 查询所有部门的名字,地址, 和对应的员工姓名,工资.
select d.name,loc,e.name,sal 
from emp e right join dept d on e.dept_id=d.id;

### 关联查询总结
1. 如果查询的数据是两个表的交集数据,使用等值连接或内连接(推荐)
2. 如果查询的是一张表的全部和另外一张表的交集则使用外连接.

### JDBC
- Java DataBase Connectivity: Java数据库连接 
- 学习JDBC主要学习的就是如何通过Java语言和数据库软件进行连接并执行SQL语句.
- JDBC是Sun公司提供的一套用于Java语言和数据库软件进行连接的API (Application Programma Interface)
- 为什么Sun公司定义JDBC系列接口?
	Sun公司为了避免Java程序员,每一种数据库软件都学习一套全新的方法,通过JDBC接口将方法名定义好, 让各个数据库厂商根据此接口中方法名写各自的实现类(驱动),这样Java程序员只需要掌握JDBC接口中方法的调用即可访问任何数据库软件.    
- 如何通过JDBC连接MySQL
	1. 创建Maven工程
	2. 从苍老师文档服务器中复制以下MySQL驱动的依赖
```
<!-- 连接MySQL数据库的依赖 -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.15</version>
        </dependency>
```
	3. 刷新maven   
	4. 检查工程目录中 external Libraries 里面 是否出现了  mysql相关的内容
![在这里插入图片描述](https://img-blog.csdnimg.cn/ed9453ea0b834eee841980379ea0f4a5.png?x-oss-process=image/watermark,type_d3F5LXplbmhlaQ,shadow_50,text_Q1NETiBAdGVkdeaWjA==,size_20,color_FFFFFF,t_70,g_se,x_16)
5. 添加Demo01.java 并在main方法中添加以下代码
	

```
//1. 获取数据库连接 导包java.sql 异常抛出 参数:数据库连接地址,用户名,密码
        Connection conn =
                DriverManager.getConnection("jdbc:mysql://localhost:3306/empdb?characterEncoding=utf8&serverTimezone=Asia/Shanghai",
                        "root","root");
        System.out.println("连接对象:"+conn);
        //2. 创建执行SQL语句的对象
        Statement s = conn.createStatement();
        //3. 执行SQL语句 execute执行
        s.execute("create table jdbct1(name varchar(20))");
        //4. 关闭连接
        conn.close();
        System.out.println("创建完成!");
```

### Statement执行SQL语句的对象
- execute(sql);   此方法可以执行任意SQL语句,推荐执行DDL(数据库相关和表相关的SQL语句)
- int row = executeUpdate(sql); 此方法执行增删改相关的SQL语句  ,方法返回值是一个整数 表示生效的行数
- ResultSet rs =  executeQuery(sql);  此方法执行查询相关的SQL语句 ,方法的返回值为结果集对象, 里面装着查询回来的所有数据

### 表设计面试题：     晚课7:30公布答案   
2021年过年时小明在这些天都收到了许多亲戚\朋友还有同事的红包,也发出了一些红包,有的是微信,有的是支付宝也有现金,请参考下面的题目帮小明设计表格保存红包的信息
(至少包含一张流水表)  先列出需要保存的数据有哪几种

1. 统计2021年2月15号到现在的所有红包收益

2. 查询2021年2月15号到现在 金额大于100 所有女性亲戚的名字和金额

3. 查询三个平台(微信,支付宝,现金)分别收入的红包金额  








