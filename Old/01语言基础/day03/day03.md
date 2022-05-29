# 语言基础第三天：



## 回顾：

1. 变量：存数的

   ```java
   int a;  int b,c,d;
   int a = 5;  int b; b = 10;
   int c = a+10;  System.out.println(a);
   ```

2. 八种基本数据类型：byte,short,int,long,float,double,boolean,char

   - int: 整型，4个字节，5，25，250...
   - long: 长整型，8个字节，5L，100000000000L...
   - double: 浮点型，8个字节，3.14，5.0，25.678...
   - boolean: 布尔型，1个字节，true，false
   - char: 字符型，2个字节，'你'，'y'，'3'，'*'...

3. 类型转换：

   - 自动类型转换(小到大)   强制类型转换(大到小)

   - 两点规则：

     ```java
     short s1 = 5;
     short s2 = 6;
     short s3 = (short)(s1+s2);
     ```

     

## 笔记：

1. 运算符：运算的符号

   - 算术：+，-，*，/，%，++，--

     - %: 取模/取余，余数为0即为整除

       ```java
       System.out.println(5%2); //1，商2余1
       System.out.println(8%2); //0，商4余0----整除
       System.out.println(2%8); //2，商0余2
       ```

     - ++/--：自增1/自减1，可在变量前也可在变量后

       - 单独使用时，在前在后都一样

         ```java
         int a=5,b=5;
         a++; //相当于a=a+1
         ++b; //相当于b=b+1
         System.out.println(a); //6
         System.out.println(b); //6
         ```

       - 被使用时，在前在后不一样

         - a++的值为a
         - ++a的值为a+1

         ```java
         int a=5,b=5;
         int c = a++; //1)将a++的值5赋值给c  2)a自增1变为6
         int d = ++b; //1)将++b的值6赋值给d  2)b自增1变为6
         System.out.println(a); //6
         System.out.println(b); //6
         System.out.println(c); //5
         System.out.println(d); //6
         ```

       > 注：--与++同理

       ```java
       int a=5,b=5;
       a--; //相当于a=a-1
       --b; //相当于b=b-1
       System.out.println(a); //4
       System.out.println(b); //4
       
       int a=5,b=5;
       int c = a--; //1)将a--的值5赋值给c  2)a自减1变为4
       int d = --b; //1)将--b的值4赋值给d  2)b自减1变为4
       System.out.println(a); //4
       System.out.println(b); //4
       System.out.println(c); //5
       System.out.println(d); //4
       
       ```

   - 关系：>，<，>=，<=，==，!= 

     ​           关系运算的结果为boolean型，关系成立为true，不成立为false

     ```java
     int a=5,b=10,c=5;
     boolean b1 = a>b;
     System.out.println(b1);   //false
     System.out.println(c<b);  //true
     System.out.println(a>=c); //true
     System.out.println(a<=b); //true
     System.out.println(a==c); //true
     System.out.println(a!=c); //false
     
     System.out.println(a+c>10); //false
     System.out.println(a%2==0); //false
     System.out.println(c++>5);  //false--------c自增1变为6
     System.out.println(c++>5);  //true---------c自增1变为7
     ```

   - 逻辑：&&，||，!

     - 逻辑运算是建立在关系运算基础之上的，逻辑结果也是boolean型

     - &&：逻辑与(并且)，两边都为真则为真，见false则false

       ```java
       int a=5,b=10,c=5;
       boolean b1 = b>=a && b<c;
       System.out.println(b1);          //true&&false=false
       System.out.println(b<=c && b>a); //false&&true=false
       System.out.println(a==b && c>b); //false&&false=false
       System.out.println(b!=c && a<b); //true&&true=true
       ```

     - ||：逻辑或(或者)，有真则为真，见true则true

       ```java
       int a=5,b=10,c=5;
       System.out.println(b>=a || b<c); //true||false=true
       System.out.println(b<=c || b>a); //false||true=true
       System.out.println(b!=c || a<b); //true||true=true
       System.out.println(a==b || c>b); //false||false=false
       ```

     - !：逻辑非(取反)，非真则假，非假则真

       ```java
       int a=5,b=10,c=5;
       boolean b2 = !(a<b);
       System.out.println(b2);     //!true=false
       System.out.println(!(a>b)); //!false=true
       ```

     > 注意：&&和||会有短路的概念
     >
     > 1. 对于&&而言，当第1个条件为false时，会发生短路(后面的不走)
     > 2. 对于||而言，当第1个条件为true时，会发生短路(后面的不走)

     ```java
     int a=5,b=10,c=5;
     boolean b3 = a>b && c++>2;
     System.out.println(b3); //false
     System.out.println(c);  //5，发生短路了
     
     boolean b4 = c<b || a++>2;
     System.out.println(b4); //true
     System.out.println(a);  //5，发生短路了
     ```

   - 赋值：=，+=，-=，*=，/=，%=

     ```java
     int a = 5;
     a += 10; //相当于a=(int)(a+10)
     System.out.println(a); //15
     a *= 2; //相当于a=(int)(a*2)
     System.out.println(a); //30
     a /= 6; //相当于a=(int)(a/6)
     System.out.println(a); //5
     ```

     扩展赋值运算符(+=,-=,*=,/=,%=)，自带强转功能：

     ```java
     short s = 5;
     //s = s+10; //编译错误，需改为: s=(short)(s+10);
     s += 10; //正确，相当于s=(short)(s+10)
     ```

   - 字符串连接：+

     ```java
     int age = 38;
     System.out.println("age="); //age=
     System.out.println(age);    //38
     System.out.println("age="+age); //age=38
     System.out.println("我的年龄是"+age); //我的年龄是38
     System.out.println("我今年"+age+"岁了"); //我今年38岁了
     
     String name = "WKJ"; //声明字符串型变量name并赋值为WKJ
     System.out.println("name="+name); //name=WKJ
     System.out.println("大家好，我叫"+name); //大家好，我叫WKJ
     System.out.println("大家好，我叫"+name+"，今年"+age+"岁了"); //大家好，我叫WKJ，今年38岁了
     ```

     > 任何类型的数据与String相连，结果都会变为String类型

     ```java
     System.out.println(10+20+""+30); //3030---------String
     System.out.println(""+10+20+30); //102030-------String
     System.out.println(10+20+30+""); //60-----------String
     ```

   - 条件：

     - 语法：

       ```java
       boolean?数1:数2
       ```

     - 执行过程：

       ```java
       判断boolean的值:
         若为true，则整个表达式的值为?号后的数1
         若为false，则整个表达式的值为:号后的数2
       ```

     - 代码展示：

       ```java
       int num = 5;
       int flag = num>0?1:-1;
       System.out.println(flag); //1
       
       int a=8,b=55;
       int max = a>b?a:b;
       System.out.println("max="+max);
       ```

     

2. 分支结构：基于条件执行的语句

   - if结构：1条路
   
     - 语法：
   
       ```
       if(boolean){
       	语句块-----------基于条件执行的语句
       }
       ```
   
     - 执行过程：
   
       ```java
       判断boolean的值:
       	若为true，则执行语句块(整个结束)
       	若为false，则if直接结束
       ```
   
     - 代码演示：
   
       ```java
       int num = 6; //带数(6,5)
       if(num%2==0){
           System.out.println(num+"是偶数");
       }
       System.out.println("继续执行...");
       
       double price = 300.0; //消费金额
       if(price>=500){ //满500   带数(600.0,300.0)
           price*=0.8; //打8折
       }
       System.out.println("最终结算金额为:"+price);
       ```
   
   - if...else结构：2条路
   
     - 语法：
   
       ```
       if(boolean){
       	语句块1
       }else{
           语句块2
       }
       ```
   
     - 执行过程：
   
       ```java
       判断boolean的值:
         若为true，则执行语句块1(整个结束)
         若为false，则执行语句块2(整个结束)
       ```
   
     - 说明：语句块1和语句块2，必走其中之一---------2选1
   
     - 代码演示：
   
       ```java
       //带数(6,5)
       int num = 5;
       if(num%2==0){
           System.out.println(num+"是偶数");
       }else{
           System.out.println(num+"是奇数");
       }
       System.out.println("继续执行...");
       
       //带数(600.0,300)
       double price = 300.0; //消费金额
       if(price>=500){ //满500
           price*=0.8; //打8折
       }else{ //不满500
           price*=0.9; //打9折
       }
       System.out.println("最终结算金额为:"+price);
       ```



```java
练习：-------------------最少两遍
1.练习运算符的小代码：
  --算术、关系、逻辑、赋值、字符串连接、条件
2.练习if：
  --偶数的判断  满500打8折
3.练习if...else：
  --偶数、奇数的判断   满500打8折，不满500打9折
```



补充：

```java
任何复杂的业务逻辑都可以通过三种结构来实现:
1)顺序结构:从上往下逐行执行，每句必走
2)分支结构:有条件的执行某语句，并非每句必走
3)循环结构:------明天讲
```







