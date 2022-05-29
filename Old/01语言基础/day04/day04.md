# 语言基础第四天:



## 回顾：

1. 运算符：
   - 算术：+，-，*，/，%，++，--
   - 关系：>，<，>=，<=，==，!=   boolean
   - 逻辑：&&，||，!   boolean
   - 赋值：=，+=，-=，*=，/=，%=
   - 字符串连接：+
   - 条件：boolean?数1:数2
2. 分支结构：基于条件执行的语句
   - if结构：1条路
   - if...else结构：2条路





## 笔记：

1. Scanner接收用户输入的数据：共3步(先背下来，面向对象第五天能理解)

   - 在package下：

     ```java
     import java.util.Scanner; //1.我想用扫描仪
     ```

   - 在main中：

     ```java
     Scanner scan = new Scanner(System.in); //2.新建了个扫描仪scan
     ```

   - 在第2步下面：

     ```java
     System.out.println("请输入年龄:");
     int age = scan.nextInt(); //3.用扫描仪扫描用户输入的整数
     System.out.println("请输入商品价格:");
     double price = scan.nextDouble(); //3.用扫描仪扫描用户输入的小数
     ```

   完整代码如下：

   ```java
   package day04;
   import java.util.Scanner; //1.我想用扫描仪
   //Scanner的演示
   public class ScannerDemo {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in); //2.新建了个扫描仪scan
           System.out.println("请输入年龄:");
           int age = scan.nextInt(); //3.用扫描仪扫描用户输入的整数
           System.out.println("请输入商品价格:");
           double price = scan.nextDouble(); //3.用扫描仪扫描用户输入的小数
           System.out.println("年龄为:"+age+"，商品价格为:"+price);
       }
   }
   ```

2. 分支结构：

   - if...else if结构：多条路

     - 语法：

       ```java
       1)语法:-----以4路结构为例
          if(boolean-1){
            语句块1
          }else if(boolean-2){
            语句块2
          }else if(boolean-3){
            语句块3
          }else{
            语句块4
          }
       ```

     - 执行过程：

       ```java
       判断boolean-1，若为true则执行语句块1(结束)，若为false则
       再判断boolean-2，若为true则执行语句块2(结束)，若为false则
       再判断boolean-3，若为true则执行语句块3(结束)，
                       若为false则执行语句块4(结束)
       ```

     - 说明：语句块1/2/3/4，只能执行其中之一--------多选1

     案例：

     ```java
     package day04;
     import java.util.Scanner;
     //成绩等级判断
     public class ScoreLevel {
         public static void main(String[] args) {
             Scanner scan = new Scanner(System.in);
             System.out.println("请输入成绩:");
             double score = scan.nextDouble();
             //带数(95,85,65,45)
             if(score>=90){
                 System.out.println("A-优秀");
             }else if(score>=80){
                 System.out.println("B-良好");
             }else if(score>=60){
                 System.out.println("C-中等");
             }else{
                 System.out.println("D-不及格");
             }
         }
     }
     ```

   - switch...case结构：多条路

     - 说明：

       - 优点：效率高、结构清晰

       - 缺点：只能对整数判断相等

       - break：跳出switch

       - switch可以作用于哪些数据类型上

         ---byte、short、int、char、String、枚举类型-------------重要

     - 语法演示代码：

       ```java
       public class SwitchCaseDemo {
           public static void main(String[] args) {
               int num = 2;
               switch(num){
                   case 1: //if(num==1)
                       System.out.println(111);
                       break;
                   case 2: //以此为入口
                       System.out.println(222);
                       break; //跳出switch
                   case 3:
                       System.out.println(333);
                       break;
                   default: //所有case都未匹配时执行
                       System.out.println(888);
               }
           }
       }
       ```

     - 案例：

       ```java
       public class CommandBySwitch {
           public static void main(String[] args) {
               Scanner scan = new Scanner(System.in);
               System.out.println("请选择功能: 1.存款 2.取款 3.转帐 4.查询余额 0.退卡");
               int command = scan.nextInt();
       
               switch(command){
                   case 1:
                       System.out.println("存款操作...");
                       break;
                   case 2:
                       System.out.println("取款操作...");
                       break;
                   case 3:
                       System.out.println("转帐操作...");
                       break;
                   case 4:
                       System.out.println("查询余额操作...");
                       break;
                   case 0:
                       System.out.println("退卡操作...");
                       break;
                   default:
                       System.out.println("输入错误");
               }
           }
       }
       ```

3. 循环：反复多次执行一段相同或相似的代码

4. 循环三要素：

   - 循环变量的初始化

   - 循环的条件(以循环变量为基础)

   - 循环变量的改变(向着循环的结束变)

     > 循环变量：在整个循环过程中所反复改变的那个数

   ```java
   示例1:跑3圈
      循环变量:所跑圈数count
      1)int count=0;
      2)count<3
      3)count++;
        count=0/1/2/ 3时结束
                           圈数为0
      够3圈吗？ 不够  跑一圈  圈数为1
      够3圈吗？ 不够  跑一圈  圈数为2
      够3圈吗？ 不够  跑一圈  圈数为3
      够3圈吗？ 够了
            
   示例2:打印机打印4份简历
      循环变量:所打份数num
      1)int num=0;
      2)num<4
      3)num++;
        num=0/1/2/3/ 4时结束
                             份数为0
      够4份吗？ 不够  打印一份  份数为1
      够4份吗？ 不够  打印一份  份数为2
      够4份吗？ 不够  打印一份  份数为3
      够4份吗？ 不够  打印一份  份数为4
      够4份吗？ 够了
            
   示例3:输出5次"行动是成功的阶梯":
          行动是成功的阶梯
          行动是成功的阶梯
          行动是成功的阶梯
          行动是成功的阶梯
          行动是成功的阶梯
     循环变量:次数times
     1)int times=0;
     2)times<5
     3)times++;
       times=0/1/2/3/4/ 5时结束
           
   示例4:输出9的乘法表:
   	  1*9=9
         2*9=18
         3*9=27
         4*9=36
         5*9=45
         6*9=54
         7*9=63
         8*9=72
         9*9=81
     循环变量:乘数num
     1)int num=1;
     2)num<=9
     3)num++;
       num=1/2/3/4/5/6/7/8/9/ 10时结束    
   ```

5. 循环结构：

   - while结构：先判断后执行，有可能一次都不执行

     - 语法：

       ```
       while(boolean){
          语句块-------------反复执行的代码
       }
       ```

     - 执行过程：

       ```java
       判断boolean的值，若为true则执行语句块，
       再判断boolean的值，若为true则再执行语句块，
       再判断boolean的值，若为true则再执行语句块，
       如此反复，直到boolean的值为false时，while循环结束
       ```

     - 代码演示：

       ```java
       //1)输出5次"行动是成功的阶梯":
       int times = 0;  //1)循环变量的初始化
       while(times<5){ //2)循环的条件
           System.out.println("行动是成功的阶梯");
           times++;    //3)循环变量的改变
       }
       System.out.println("继续执行...");
       /*
         执行过程:
                      times=0
           true  输出  times=1
           true  输出  times=2
           true  输出  times=3
           true  输出  times=4
           true  输出  times=5
           false while循环结束
           输出继续执行...
        */
       2)输出9的乘法表:
         int num = 1;
         while(num<=9){
            System.out.println(num+"*9="+num*9);
             num++;
         } 
       
       ```

     - 案例：

       ```java
       public class Guessing {
           public static void main(String[] args) {
               Scanner scan = new Scanner(System.in);
               int num = (int)(Math.random()*1000+1); //1到1000之内的随机数
               System.out.println(num); //作弊
       
               //300(大),200(小),250(对)
               System.out.println("猜吧!");
               int guess = scan.nextInt(); //1.
               while(guess!=num){ //2.
                   if(guess>num){
                       System.out.println("太大了");
                   }else{
                       System.out.println("太小了");
                   }
                   System.out.println("猜吧!");
                   guess = scan.nextInt(); //3.
               }
               System.out.println("恭喜你猜对了!");
           }
       }
       
       案例中的num为1到1000之内的随机数，推倒过程如下:
       Math.random()会生成0.0到0.999999999...之内的随机数
       /*
         Math.random()------------0.0到0.99999999999...
         *1000--------------------0.0到999.999999999...
         +1-----------------------1.0到1000.99999999...
         (int)--------------------1到1000
        */
       ```

   - do...while结构：先执行后判断，至少执行一次

     - 语法:

       ```java
       do{
         语句块
       }while(boolean);
       ```

     - 执行过程:

       ```java
       先执行语句块，再判断boolean的值，若为true则
       再执行语句块，再判断boolean的值，若为true则
       再执行语句块，如此反复，直到boolean的值为false时，do...while循环结束
       ```

     - 选择规则：要素1与要素3相同时，首选do...while

     - 案例：

       ```java
       public class Guessing {
           public static void main(String[] args) {
               Scanner scan = new Scanner(System.in);
               int num = (int)(Math.random()*1000+1); //1到1000之内的随机数
               System.out.println(num); //作弊
       
               //假设num=250
               //300(大),200(小)，250(对)
               int guess;
               do{
                   System.out.println("猜吧!");
                   guess = scan.nextInt(); //1+3
                   if(guess>num){
                       System.out.println("太大了");
                   }else if(guess<num){
                       System.out.println("太小了");
                   }else{
                       System.out.println("恭喜你猜对了!");
                   }
               }while(guess!=num); //2
           }
       }
       ```

     

补充：

1. 变量的作用域/范围：从变量的声明开始，到包含它最近的大括号结束



```java
练习:-----------------------最少两次
1.完成ScoreLevel成绩等级判断
2.完成CommandBySwitch命令解析程序
3.完成Guessing猜数字之while版
4.完成Guessing猜数字之do...while版
```













