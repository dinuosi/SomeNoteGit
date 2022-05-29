# 语言基础第二天：



## 回顾：

1. java开发环境：

   - 编译运行过程：

     - 编译期：.java源文件，经过编译，生成.class字节码文件

     - 运行期：JVM加载.class并运行.class

       > 跨平台、一次编译到处运行

   - 名词：

     - JVM: java虚拟机

       ​         加载.class并运行.class

     - JRE: java运行环境

       ​        除了包含JVM以外还包含了运行java所必须的环境

       ​        JRE = JVM+java系统类库 (小零件)

     - JDK: java开发工具包

       ​         除了包含JRE以外还包含了开发java所必须的命令工具

       ​         JDK = JRE+编译、运行等命令工具

2. idea：

   - 开发步骤：
     - 新建Java项目/工程
     - 新建Java包
     - 新建Java类    main中：System.out.println("hello world");
   - 注释：解释性文本
     - 单行注释：//
     - 多行注释：/*    */
     - 文档注释：/**    */------------------API时讲





## 笔记：

1. 变量：存数的

   - 声明：-------相当于在银行开了个帐户

     ```java
     int a; //声明了一个整型的变量，名为a
     int b,c,d; //声明了三个整型的变量，名为b,c,d
     //int a; //编译错误，变量不能同名
     ```

   - 初始化：-----给帐户存钱

     ```java
     int a = 250; //声明整型变量a并赋值为250
     int b;   //声明整型变量b
     b = 250; //给变量b赋值为250
     b = 360; //修改b的值为360
     ```

   - 使用：-----使用的是帐户里面的钱

     - 对变量的使用就是对它所存的那个数的使用的

       ```java
       int a = 5;
       int b = a+10; //取出a的值5，加10后，再赋值给变量b
       System.out.println(b);   //输出变量b的值15
       System.out.println("b"); //输出b，双引号中的原样输出
       a = a+10; //在a本身基础之上增10
       System.out.println(a); //输出15
       ```

     - 变量在用之前必须声明并初始化

       ```java
       //System.out.println(m); //编译错误，变量m未声明
       int m;
       //System.out.println(m); //编译错误，变量m未初始化
       ```

   - 命名：

     - 只能包含字母、数字、_和$符，并且不能以数字开头
     - 严格区分大小写
     - 不能使用关键字
     - 允许中文命名，但不建议，建议"英文见名知意"、"小驼峰命名法"

     ```java
     int a1,a_5$,_3c,$6_f;
     //int a*b; //编译错误，不能包含*号等特殊符号
     //int 1a; //编译错误，不能以数字开头
     int aa = 5;
     //System.out.println(aA); //编译错误，严格区分大小写
     //int class; //编译错误，不能使用关键字
     
     int 年龄;      //允许中文，但不建议
     int nianLing; //必须杜绝
     int age; //建议"英文的见名知意"
     int score,myScore,myJavaScore; //建议"小驼峰命名法"
     ```

2. 八种基本数据类型：byte,short,int,long,float,double,boolean,char-重点

   - int：整型，4个字节，-21个多亿到21个多亿

     - 整数直接量默认int类型，但不能超范围，若超范围则发生编译错误

       ```java
       //1)int:整型，4个字节，-21个多亿到21个多亿
       int a = 25; //25为整数直接量
       //int b = 10000000000; //编译错误，100亿默认为int类型，但超出int范围了
       //int c = 3.14; //编译错误，整型只能装整数
       ```

     - 两个整数相除，结果还是整数，小数位无条件舍弃(不会四舍五入)

       ```java
       System.out.println(5/2); //2
       System.out.println(2/5); //0
       System.out.println(5/2.0); //2.5
       ```

     - 整数运算时，若超出范围则发生溢出(溢出不是错误，但需要避免)

       ```java
       int d = 2147483647; //int的最大值
       d = d+1;
       System.out.println(d); //-2147483648(int的最小值)，发生溢出了
       ```

   - long：长整型，8个字节，很大很大很大

     - 长整型直接量需在数字后加L或l

       ```java
       long a = 25L; //25L为长整型直接量
       //long b = 10000000000; //编译错误，100亿默认为int类型，但超出int范围了
       long c = 10000000000L; //100L为长整型直接量
       ```

     - 运算时若有可能溢出，建议在第1个数字后加L

       ```java
       long d = 1000000000*2*10L;
       System.out.println(d); //200亿
       long e = 1000000000*3*10L;
       System.out.println(e); //不是300亿
       long f = 1000000000L*3*10;
       System.out.println(f); //300亿
       ```

   - double：浮点型，8个字节，很大很大很大

     - 浮点数直接量默认为double型，若想表示float，需在数字后加F或f

       ```java
       double a = 3.14; //3.14为浮点数直接量，默认double型
       float b = 3.14F; //3.14F为float型直接量
       ```

     - float和double型数据参与运算时，有可能发生舍入误差，精确场合不能使用

       ```java
       double c=6.0,d=1.9;
       System.out.println(c-d); //0.10000000000000009
       ```

   - boolean：布尔型，1个字节

     - 只能赋值为true或false

       ```java
       boolean b1 = true;  //true为布尔型直接量
       boolean b2 = false; //false为布尔型直接量
       //boolean b3 = 25; //编译错误，只能赋值为true或false
       ```

   - char：字符型，2个字节

     - 采用的是Unicode字符集，一个字符对应一个码

       表现的形式是字符char，本质上是码int(0到65535之间)

       (ASCII码：'a'---97     'A'---65     '0'---48)

     - 字符型直接量必须放在单引号中，只能有一个

       ```java
       char c1 = '女'; //字符女
       char c2 = 'f';  //字符f
       char c3 = '6';  //字符6
       char c4 = '*';  //字符*
       
       //char c5 = 女; //编译错误，字符型直接量必须放在单引号中
       //char c6 = ''; //编译错误，必须有字符
       //char c7 = '10'; //编译错误，只能有一个字符
       ```

     - 特殊符号需要通过\来转义

       ```java
       char c8 = '\'';
       System.out.println(c8); //'
       ```

3. 类型间的转换：

   > 数据类型从小到大依次为: byte--short--int--long--float--double
   >
   > ​                                                        char--

   - 两种方式：

     - 自动/隐式类型转换：小类型到大类型

     - 强制类型转换：大类型到小类型

       > 语法：(要转换成为的数据类型)变量
       >
       > 强转有可能溢出或丢失精度

     ```java
     int a = 5;
     long b = a; //自动类型转换
     int c = (int)b; //强制类型转换
     
     long d = 5; //自动类型转换
     double e = 5; //自动类型转换
     
     long f = 10000000000L;
     int g = (int)f;
     System.out.println(g); //1410065408，强转有可能发生溢出
     double h = 25.987;
     int i = (int)h;
     System.out.println(i); //25，强转有可能丢失精度
     ```

   - 两点规则：

     - 整数直接量可以直接赋值给byte,short,char，但不能超出范围

       ```java
       byte b1 = 5;
       byte b2 = 6;
       ```

     - byte,short,char型数据参与运算时，系统自动将它转换为int再运算

       ```java
       byte b3 = (byte)(b1+b2);
       
       System.out.println(2+2);     //4
       System.out.println(2+'2');   //52，2加上'2'的码50
       System.out.println('2'+'2'); //100，'2'的码50，加上'2'的码50
       ```



补充：

```java
1G=1024M(兆)
1M=1024KB(千字节)
1KB=1024B(字节)

1B=8Bit(位)
1G=1024*1024*1024字节--------1073741824字节
```



练习：-------------------每人最少做两次

1. 变量小代码：声明、初始化、使用、命名
2. 基本数据类型小代码：int、long、double、boolean、char
3. 类型间转换小代码：自动转换、强制转换




















