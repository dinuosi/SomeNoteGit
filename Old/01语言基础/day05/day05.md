# 语言基础第五天:



## 回顾：

1. Scanner接收用户输入的数据：

   ```java
   1)import java.util.Scanner;
   2)Scanner scan = new Scanner(System.in);
   3)System.out.println("请输入整数:");
     int num = scan.nextInt();
     System.out.println("请输入价格:");
     double price = scan.nextDouble();
   ```

2. 分支结构：

   - switch...case结构：多条路

     优点：效率高、结构清晰

     缺点：只能对整数判断相等

     break：跳出switch

3. 循环：反复多次执行一段相同或相似的代码

4. 循环三要素：

   - 循环变量的初始化

   - 循环的条件(以循环变量为基础)

   - 循环变量的改变(向着循环的结束变)

     > 循环变量：在整个循环过程中所反复改变的那个数

5. 循环结构：

   - while结构：先判断后执行，有可能一次都不执行

   - do...while结构：先执行后判断，至少执行一次

     > 要素1与要素3相同时，首选do...while





## 笔记：

1. 循环结构：

   - for结构：应用率最高，与次数相关的循环

     - 语法：

       ```java
       //    1    2    3
       for(要素1;要素2;要素3){
          语句块/循环体-------------反复执行的语句  4
       }
       ```

     - 执行过程:

       ```
       1243243243243243...2
       ```

     - 案例：

       ```java
       //for中的循环变量i，它的作用域仅在当前for中
       for(int i=1;i<=9;i++){
           System.out.println(i+"*9="+i*9);
       }
       for(int i=9;i>=1;i--){
           System.out.println(i+"*9="+i*9);  //9:58继续
       }
       
       for(int times=0;times<5;times++){
           System.out.println("行动是成功的阶梯");
       }
       System.out.println("继续执行...");
       /*
         执行过程:
           times=0  true  输出
           times=1  true  输出
           times=2  true  输出
           times=3  true  输出
           times=4  true  输出
           times=5  false for循环结束
           输出继续执行...
        */
       ```

     - 特殊语法格式：-------了解

       ```java
       /*
         int i = 1;
         for(;i<=9;i++){
             System.out.println(i+"*9="+i*9);
         }
       */
       
       /*
       for(int i=1;i<=9;){
         System.out.println(i+"*9="+i*9);
         i++;
       }
       */
       
       /*
       for(;;){ //没有条件的循环就是一个死循环
         System.out.println("欢迎大家来到达内");
       }
       */
       
       /*
       for(int i=1,j=5;i<=5;i+=2,j-=2){
       }
       */
       /*
         i=1,j=5
         i=3,j=3
         i=5,j=1
         i=7,j=-1
       */
       ```

2. 三种循环如何选择：

   - 先看循环是否是与次数相关：
     - 若相关-----------------------------------直接上for
     - 若无关，再看要素1与要素3是否相同
       - 若相同-------------------------------直接上do...while
       - 若不同-------------------------------直接while

3. break：跳出循环

   ```java
   for(int i=1;i<=9;i++){
       if(i==4){ //在某种特定条件下，提前结束循环
           break; //跳出循环
       }
       System.out.println(i+"*9="+i*9);
   }
   /*
     执行过程:
       i=1  1*9=9
       i=2  2*9=18
       i=3  3*9=27
       i=4
    */
   ```

   continue：跳过循环体中剩余语句而进入下一次循环-------------了解

   ```java
   //输出9的乘法表，跳过能被3整除的
   for(int i=1;i<=9;i++){
       if(i%3==0){
           continue; //跳过循环体中剩余语句而进入下一次循环
       }
       System.out.println(i+"*9="+i*9);
   }
   /*
     执行过程:
       i=1  1*9=9
       i=2  2*9=18
       i=3
       i=4  4*9=36
       i=5  5*9=45
       i=6
       i=7  7*9=63
       i=8  8*9=72
       i=9
       i=10
    */
   ```

4. for循环综合案例：

   ```java
   public class Addition {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           int score = 0; //总分
           for(int i=1;i<=10;i++){ //10次   (1)25+90=?
               int a = (int)(Math.random()*100); //加数a(0到99之间的随机数)
               int b = (int)(Math.random()*100); //加数b
               int result = a+b; //存正确答案
               System.out.println("("+i+")"+a+"+"+b+"=?"); //1)出题
               System.out.println("算吧!");
               int answer = scan.nextInt(); //2)答题
               if(answer==-1){  //3)答题
                   break;
               }
               if(answer==result){
                   System.out.println("答对了");
                   score += 10; //答对1题加10分
               }else{
                   System.out.println("答错了");
               }
           }
           System.out.println("总分为:"+score);
       }
   }
   ```

5. 嵌套循环：

   - 循环中套循环，常常多行多列时使用

   - 执行规则：外层循环走一次，内层循环走所有次

   - 建议：嵌套层数越少越好，能一层就不用两层，能两层就不用三层

   - break只能跳出当前层循环

   - 案例：

     ```java
     for(int num=1;num<=9;num++){
         for(int i=1;i<=num;i++){
             System.out.print(i+"*"+num+"="+i*num+"\t");
         }
         System.out.println(); //换行
     }
     ```

6. 数组：

   - 是一种数据类型(引用类型)

   - 相同数据类型元素的集合

   - 定义：

     ```java
     //声明整型数组arr，包含10个元素，每个元素都是int型，默认值为0
     int[] arr = new int[10];
     //声明浮点型数组d，包含10个元素，每个元素都是double型，默认值为0.0
     double[] d = new double[10];
     //声明布尔型数组b，包含26个元素，每个元素都是boolean型，默认值false
     boolean[] b = new boolean[26];
     ```

   - 初始化：------初始化的是数组中的元素

     ```java
     int[] a = new int[3]; //0,0,0
     int[] a = {2,5,8}; //2,5,8
     int[] a = new int[]{2,5,8}; //2,5,8
     int[] a;
     //a = {2,5,8}; //编译错误，此方式只能声明同时初始化
     a = new int[]{2,5,8};
     ```

   - 访问：------访问的是数组中的元素

     - 通过(数组名.length)可以获取数组的长度(元素个数)

       ```java
       int[] arr = new int[10];
       System.out.println(arr.length); //输出arr的长度--10
       ```

     - 通过索引/下标来访问数组中的元素

       下标从0开始，最大到数组的长度-1

       ```java
       int[] arr = new int[3];
       arr[0] = 100; //给第1个元素赋值为100
       arr[1] = 200; //给第2个元素赋值为200
       arr[2] = 300; //给第3个元素赋值为300
       //arr[3] = 400; //运行时发生数组下标越界异常
       System.out.println(arr[arr.length-1]); //300，输出最后一个元素的值
       ```

   - 遍历/迭代：从头到尾走一遍

     ```java
     int[] arr = new int[10];
     for(int i=0;i<arr.length;i++){ //遍历arr数组
         arr[i] = 100; //给每个元素都赋值为100
         System.out.println(arr[i]); //输出每个元素的值
     }
     ```



补充：

1. \t为水平制表示，固定占8位

2. 数组的访问：

   ```java
   int[] arr = new int[10];
   for(int i=0;i<10;i++){
       arr[i]--------代表arr中的每一个元素
   }
   ```

   

练习：--------------------------------------每人最少两次

1. Addition随机加法运算器
2. MultiTable九九乘法表
3. 数组小代码：
   - 定义、初始化、访问、遍历





