# 语言基础第三天：

## 回顾：

1. 变量：存数的

   ```java
   int a;  int b,c;
   int a = 5;  int a; a=5;
   int b = a+10;  System.out.println(a);
   a = a+10; //在a本身基础之上增10
   ```

2. 八种基本数据类型：byte,short,int,long,float,double,boolean,char

   - int：整型，4个字节，5,10,10000000...
   - long：长整型，8个字节，5L,100000000000000L...
   - double：浮点型，８个字节，3.14,54.0,5.678...
   - boolean：布尔型，1个字节，true,false
   - char：字符型，2个字节，'你','y','4','*'...

3. 类型间的转换：

   - 两种方式：自动/隐式、强制  (要转换成为的数据类型)变量

   - 两点规则：

     ```java
     short s1 = 5;
     short s2 = 6;
     short s3 = (short)(s1+s2);
     ```

## 笔记：

1. 运算符：运算的符号

   - 算术：+，-，*，/，%，++，--

     - %:取模/取余，余数为0即为整除
     - ++/--：自增1/自减1，可在变量前也可在变量后
       - 单独使用时，在前在后都一样
       - 被使用时，在前在后不一样
         - a++的值为a--------(a--的值为a)
         - ++a的值为a+1------(--a的值为a-1)                    

     ```java
     //%的演示
     System.out.println(8%2); //0，商4余0----整除
     System.out.println(5%2); //1，商2余1
     System.out.println(2%8); //2，商0余2
     
     //++单独使用:
     int a=5,b=5;
     a++; //相当于a=a+1
     ++b; //相当于b=b+1
     System.out.println(a); //6
     System.out.println(b); //6
     
     //++被使用:
     int a=5,b=5;
     int c = a++; //1)保存a++的值5  2)a自增1变为6  3)将第1步保存的值5赋值给c--底层运算过程
     //---粗暴记法:a++的值为5，c就是5
     int d = ++b; //1)保存++b的值6  2)b自增1变为6  3)将第1步保存的值6赋值给d--底层运算过程
     //---粗暴记法:++b的值为6，d就是6
     System.out.println(a); //6
     System.out.println(b); //6
     System.out.println(c); //5
     System.out.println(d); //6
     
     //--单独使用:
     int a=5,b=5;
     a--; //相当于a=a-1
     --b; //相当于b=b-1
     System.out.println(a); //4
     System.out.println(b); //4
     
     //--被使用:
     int a=5,b=5;
     int c = a--; //a--的值为5，所以c的值为5
     int d = --b; //--b的值为4，所以d的值为4
     System.out.println(a); //4
     System.out.println(b); //4
     System.out.println(c); //5
     System.out.println(d); //4
     ```

   - 关系：>，<，>=，<=，==，!=

     - 关系运算的结果为boolean型，
        关系成立则为true，关系不成立则为false

     ```java
     int a=5,b=10,c=5;
     boolean b1 = a>b;
     System.out.println(b1);   //false
     System.out.println(c<b);  //true
     System.out.println(a>=c); //true
     System.out.println(a<=b); //true
     System.out.println(a==c); //true
     System.out.println(a!=c); //false
     
     System.out.println(a+c>b);  //false
     System.out.println(a%2==0); //false
     System.out.println(c++>5);  //false-------c自增1变为6
     System.out.println(c++>5);  //true--------c自增1变为7
     ```

   - 逻辑：&&，||，!

     - &&：短路与(并且)，两边都为真则为真，见false则false

       > 当第1个条件为false时，发生短路(后面的不执行了) 

     - ||：短路或(或者)，有真则为真，见true则true

       > 当第1个条件为true时，发生短路(后面的不执行了)

     - !：逻辑非(取反)，非真则假，非假则真

     ```java
     int a=5,b=10,c=5;
     //&&的演示:
     boolean b1 = b>=a && b<c;
     System.out.println(b1);          //true&&false=false
     System.out.println(b<=c && b>a); //false&&true=false
     System.out.println(a==b && c>b); //false&&false=false
     System.out.println(b!=c && a<b); //true&&true=true
     int age = 25;
     System.out.println(age>=18 && age<=50); //看age是否在18到50之间
     
     //||的演示:
     System.out.println(b>=a || b<c); //true||false=true
     System.out.println(b<=c || b>a); //false||true=true
     System.out.println(b!=c || a<b); //true||true=true
     System.out.println(a==b || c>b); //false||false=false
     int score = 89;
     System.out.println(score<0 || score>100); //看score是否不合法
     
     //!的演示
     boolean b2 = !(a<b);
     System.out.println(b2);     //!true=false
     System.out.println(!(a>b)); //!false=true
     
     //短路的演示
     int a=5,b=10,c=5;
     boolean b3 = a>b && c++>2;
     System.out.println(b3); //false
     System.out.println(c);  //5，发生短路了
     
     boolean b4 = a<b || c++>2;
     System.out.println(b4); //true
     System.out.println(c);  //5，发生短路了
     ```

   - 赋值：=，+=，-=，*=，/=，%=

     - 简单赋值运算符：=

     - 扩展赋值运算符：+=，-=，*=，/=，%=

       > 注：扩展赋值自带强转功能

     ```java
     int a = 5;
     a += 10; //相当于a=(int)(a+10)
     System.out.println(a); //15
     a *= 2; //相当于a=(int)(a*2)
     System.out.println(a); //30
     a /= 6; //相当于a=(int)(a/6)
     System.out.println(a); //5
     
     //小面试题:
     short s = 5;
     //s = s+10; //编译错误，需强转: s=(short)(s+10);
     s += 10; //相当于s=(short)(s+10)
     ```

   - 字符串连接：+

     - +：
       -  若两边为数字，则做加法运算
       - 若两边出现了字符串，则做字符串连接
       - 任何类型与字符串相连，结果都会变为字符串类型----同化作用

     ```java
     //字符串拼接演示
     int age = 38;
     System.out.println("age="); //age=
     System.out.println(age);    //38
     System.out.println("age="+age); //age=38
     System.out.println("我的年龄是"+age); //我的年龄是38
     System.out.println("我今年"+age+"岁了"); //我今年38岁了
     
     String name = "WKJ";
     System.out.println("name="+name); //name=WKJ
     System.out.println("大家好，我叫"+name); //大家好，我叫WKJ
     System.out.println("大家好，我叫"+name+"，今年"+age+"岁了"); //大家好，我叫WKJ，今年38岁了
     
     //同化作用演示
     System.out.println(10+20+30+""); //60---------String
     System.out.println(10+20+""+30); //3030-------String
     System.out.println(""+10+20+30); //102030-----String
     ```

   - 条件/三目：?:

     - 语法：

       ​	boolean?数1:数2

     - 执行过程：

       >  注：整个表达式是有值的，它的值要么是?号后的数1，要么是:号后的数2

       - 计算boolean的值：
         - 若为true，则整个表达式的值为?号后的数1
         - 若为false，则整个表达式的值为:号后的数2

     ```java
     int num = 5;
     int flag = num>0?1:-1;
     System.out.println(flag); //1
     
     int a=8,b=55;
     int max = a>b?a:b;
     System.out.println("max="+max);
     ```

2. 分支结构：基于条件执行某语句

   - if结构：1条路

     - 语法：

          if(boolean){
                语句块
           }   

     - 执行过程：

       ​    判断boolean的值:
       ​         若为true，则执行语句块1(if整个结束)
       ​         若为false，则if直接结束

     ```java
     //1)偶数的判断:
     int num = 5;  //带数(6,5)
     if(num%2==0){
         System.out.println(num+"是偶数");
     }
     System.out.println("继续执行...");
     
     //2)满500打8折:
     double price = 300.0; //消费金额  带数(600.0,300.0)
     if(price>=500){ //满500
         price *= 0.8; //打8折
     }
     System.out.println("最终结算金额为:"+price);
     
     //3)判断年龄是否在18到50之间，若满足则输出:"满足条件"
     int age = 88;  //带数(25,5,88)
     if(age>=18 && age<=50){
         System.out.println("年龄满足条件");
     }
     System.out.println("继续执行...");
     ```

   - if...else结构：2条路

     - 语法：

       ​    if(boolean){
       ​         语句块1
       ​    }else{
       ​         语句块2
       ​    }

     - 执行过程：

       ​    判断boolean的值:
       ​         若为true，则执行语句块1(整个结束)
       ​         若为false，则执行语句块2(整个结束)

     - 说明：

       ​    语句块1和语句块2，必走其中之一------------2选1

     ```java
     //1)偶数、奇数的判断:
     int num = 5;  //带数(6,5)
     if(num%2==0){
         System.out.println(num+"是偶数");
     }else{
         System.out.println(num+"是奇数");
     }
     System.out.println("继续执行...");
     
     //2)满500打8折，不满500打9折:
     double price = 300.0;  //带数(600.0,300.0)
     if(price>=500){ //满500
         price *= 0.8;
     }else{ //不满500
         price *= 0.9;
     }
     System.out.println("最终结算金额为:"+price);
     
     //3)判断成绩是否合法，合法则输出"该成绩合法"，否则输出"该成绩不合法":
     int score = 560; //带数(95,-90,560)
     if(score<0 || score>100){
         System.out.println("该成绩不合法");
     }else{
         System.out.println("该成绩合法");
     }
     System.out.println("继续执行...");
     ```



## 精华笔记：

1. 运算符：运算的符号

   - 算术：+，-，*，/，%，++，--       
   - 关系：>，<，>=，<=，==，!=
   - 逻辑：&&，||，！
   - 赋值：=，+=，-=，*=，/=，%=
   - 字符串连接：+

   - 条件/三目：?:

2. 分支结构：基于条件执行某语句

   - if结构：1条路
   - if...else结构：2条路



补充：

1. &为不短路与，|为不短路或
2. 任何复杂的程序逻辑都可以通过三种结构来实现：
   - 顺序结构：从上往下逐行执行，每句必走
   - 分支结构：有条件的执行某语句，并非每句必走
   - 循环结构：明天讲



练习到6点去吃饭，7点开始上晚课



