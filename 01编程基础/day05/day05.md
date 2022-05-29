# 语言基础第五天：



## 回顾：

1. Scanner接收用户的数据：共3步

2. 分支结构：

   - if...else if结构：多条路

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

   - for结构：应用率高、与次数相关的循环

     - 语法：

       ​    //      1         2         3

       ​    for(要素1;要素2;要素3){

       ​        语句块/循环体----------------反复执行的代码    4

       ​     }

     - 执行过程：

       ​     1243243243243243243......2

     - 代码演示：

       ```java
       //for循环中的循环变量i，作用域仅在当前for中
       for(int i=1;i<=9;i++){
           System.out.println(i+"*9="+i*9);
       }
       for(int i=1;i<=9;i+=2){
           System.out.println(i+"*9="+i*9);
       }
       for(int i=9;i>=1;i--){
           System.out.println(i+"*9="+i*9);
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
       
       //for的特殊格式:----------了解
       int i=1;
       for(;i<=9;i++){
           System.out.println(i+"*9="+i*9);
       }
       for(int i=1;i<=9;){
           System.out.println(i+"*9="+i*9);
           i++;
       }
       for(;;){ //没有条件的循环就是一个死循环
           System.out.println("我要学习...");
       }
       
       for(int i=1,j=5;i<=5;i+=2,j-=2){
       }
       /*
         i=1,j=5  true
         i=3,j=3  true
         i=5,j=1  true
         i=7,j=-1 false
        */
       ```

2. 三种循环结构的选择规则：

   - 先看循环是否与次数相关：
     - 若相关----------------------------直接上for
     - 若无关，再看要素1与要素3是否相同：
       - 若相同------------------------直接上do...while
       - 若不同------------------------直接上while

3. break：跳出循环

   ```java
   for(int i=1;i<=9;i++){
       if(i==4){ //在某种特定条件下，提前结束循环
           break;
       }
       System.out.println(i+"*9="+i*9);
   }
   /* 
     i=1  true  1*9=9
     i=2  true  2*9=18
     i=3  true  3*9=27
     i=4  true
   */        
   ```

   continue：跳过循环体中剩余语句而进入下一次循环

   ```java
   //输出9的乘法表，只要不能被3整除的
   for(int i=1;i<=9;i++){
       if(i%3!=0){
           System.out.println(i+"*9="+i*9);
       }
   }
   
   
   //输出9的乘法表，跳过能被3整除的
   for(int i=1;i<=9;i++){
       if(i%3==0){
           continue; //跳过循环体中剩余语句而进入下一次循环
       }
       System.out.println(i+"*9="+i*9);
   }
   /*
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

4. 随机加法运算器案例：

   ```java
   package day05;
   import java.util.Scanner;
   //随机加法运算器
   public class Addition {
       public static void main(String[] args) {
           Scanner scan = new Scanner(System.in);
           int score = 0; //总分
           for(int i=1;i<=10;i++) { //10次
               int a = (int)(Math.random()*100); //加数a，0到99之间
               int b = (int)(Math.random()*100); //加数b
               int result = a+b; //存正确答案
               System.out.println("("+i+")"+a+"+"+b+"=?"); //1)出题
   
               System.out.println("算吧!----输入-1可提前结束");
               int answer = scan.nextInt(); //2)答题
   
               if(answer==-1){ //3)判题
                   break;
               }
               if(answer==result){
                   System.out.println("答对了");
                   score += 10; //答对1题，加10分
               }else{
                   System.out.println("答错了");
               }
           }
           System.out.println("总分为:"+score);
       }
   }
   ```

5. 嵌套循环：

   - 循环中套循环，常常多行多列时使用，一般外层控制行，内层控制列
   - 执行过程：外层循环走一次，内层循环走所有次
   - 建议：嵌套层数越少越好，能用一层就不用两层，能用两层就不用三层，若业务必须通过三层以上的循环才能解决，说明你的设计有问题
   - break只能跳出当前一层循环

   ```java
   for(int num=1;num<=9;num++){ //控制行
       for(int i=1;i<=num;i++){ //控制列
           System.out.print(i+"*"+num+"="+i*num+"\t");
       }
       System.out.println(); //换行
   }
   /*
     执行过程:
       num=3
         i=1  1*3=3
         i=2  2*3=6
         i=3  3*3=9
         i=4  false
         换行
       num=2
         i=1  1*2=2
         i=2  2*2=4
         i=3  false
         换行
       num=1
         i=1  1*1=1
         i=2  false
         换行
    */
   ```

6. 数组：

   - 是一种数据类型(引用类型)

   - 相同数据类型元素的集合

   - 定义：

     ```java
     //声明int型数组arr，包含10个元素，每个元素都是int型，默认值为0
     int[] arr = new int[10];
     ```

   - 初始化：给数组中的元素做初始化

     ```java
     int[] arr = new int[3]; //0,0,0
     int[] arr = {1,4,7}; //1,4,7
     int[] arr = new int[]{1,4,7}; //1,4,7
     int[] arr;
     //arr = {1,4,7}; //编译错误
     arr = new int[]{1,4,7}; //正确
     ```

   - 访问：访问的是数组中的元素

     - 通过(数组名.length)可以获取数组的长度(元素的个数)

       ```java
       int[] arr = new int[3];
       System.out.println(arr.length); //3
       ```

     - 通过下标/索引来访问数组中的元素

       下标从0开始，最大到(数组的长度-1)

       ```java
       int[] arr = new int[3];
       arr[0] = 100; //给第1个元素赋值为100
       arr[1] = 200; //给第2个元素赋值为200
       arr[2] = 300; //给第3个元素赋值为300
       System.out.println(arr[arr.length-1]); //输出最后一个元素的值
       ```

   - 遍历/迭代：从头到尾挨个走一遍

     ```java
     int[] arr = new int[10];
     for(int i=0;i<arr.length;i++){ //遍历arr数组
         //arr[i]代表arr中的每一个元素
         //给每个元素赋值为0到99之间的随机数
         arr[i] = (int)(Math.random()*100); 
         System.out.println(arr[i]); //输出每个元素的值
     }
     ```



## 精华笔记：

1. 循环结构：

   - for结构：应用率高、与次数相关的循环

2. 三种循环结构的选择规则：

   - 先看循环是否与次数相关：
     - 若相关----------------------------直接上for
     - 若无关，再看要素1与要素3是否相同：
       - 若相同------------------------直接上do...while
       - 若不同------------------------直接上while

3. break：跳出循环   

   continue：跳过循环体中剩余语句而进入下一次循环

4. 嵌套循环：

   - 循环中套循环，常常多行多列时使用，一般外层控制行，内层控制列
   - 执行过程：外层循环走一次，内层循环走所有次
   - 建议：嵌套层数越少越好，能用一层就不用两层，能用两层就不用三层，若业务必须通过三层以上的循环才能解决，说明你的设计有问题
   - break只能跳出当前一层循环

5. 数组：

   - 是一种数据类型(引用类型)

   - 相同数据类型元素的集合

   - 定义：

   - 初始化：给数组中的元素做初始化

   - 访问：访问的是数组中的元素

     - 通过(数组名.length)可以获取数组的长度(元素的个数)

     - 通过下标/索引来访问数组中的元素

       下标从0开始，最大到(数组的长度-1)

   - 遍历/迭代：从头到尾挨个走一遍





补充：

1. 变量的同名问题：
   - 作用域重叠时，变量不能同名的
2. \t：水平制表位，固定占8位
3. ArrayIndexOutOfBoundsException数组下标越界异常
   - 数组下标范围为0到(数组长度-1)，超出范围则发生如上的异常





