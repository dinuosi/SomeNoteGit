# 语言基础第六天：



## 回顾：

1. 循环结构：

   - for：应用率高、与次数相关

2. 选择原则：

3. break：跳出循环

   continue：跳过循环体中剩余语句而进入下一次循环

4. 嵌套循环:

   ​	循环中套循环，外层循环走一次，内层循环走所有次

   ​    越少越好，break只能跳出当前一层循环

5. 数组：

   ​	引用类型，相同数据类型元素的集合

   ​	int[ ]  arr = new int[3];   //0,0,0

   ​    int[ ] arr = {2,4,6};
   
   ​    int[ ] arr = new int[ ]{2,4,6};
   
   ​    System.out.println(arr[0]);
   
   ​    arr[1] = 100;
   
   ​    System.out.println(arr[arr.length-1]);
   
   ​	for(int i=0;i<arr.length;i++){
   
   ​       arr[i] = (int)(Math.random()*100);
   
   ​       System.out.println(arr[i]);
   
   ​    }



## 笔记：

1. 数组：
   - 复制：
   
     - System.arraycopy(a,1,b,0,4);
   
       ```java
       //7)数组的复制:
       int[] a = {10,20,30,40,50};
       int[] b = new int[6]; //0,0,0,0,0,0
       //a:源数组
       //1:源数组的起始下标
       //b:目标数组
       //0:目标数组的起始下标
       //4:要复制的元素个数
       System.arraycopy(a,1,b,0,4); //灵活性好
       for(int i=0;i<b.length;i++){
           System.out.println(b[i]);
       }
       
       ```
   
     - int[ ] b = Arrays.copyOf(a,6);
   
       ```java
       //常规复制
       int[] a = {10,20,30,40,50};
       //a:源数组
       //b:目标数组
       //6:目标数组的长度(元素个数)
       //---若目标数组长度>源数组长度，则末尾补默认值
       //---若目标数组长度<源数组长度，则将末尾的截掉
       int[] b = Arrays.copyOf(a,6); //灵活性差
       for(int i=0;i<b.length;i++){
           System.out.println(b[i]);
       }
       
       //数组的扩容
       int[] a = {10,20,30,40,50};
       //数组扩容(创建了一个更大的新的数组，并将源数组数据复制进去了)
       a = Arrays.copyOf(a,a.length+1);
       for(int i=0;i<a.length;i++){
           System.out.println(a[i]);
       }
       ```
   
     - 综合案例：
   
       ```java
       package day06;
       import java.util.Arrays;
       //求数组元素的最大值，并将最大值放在数组最后一个元素的下一个位置
       public class MaxOfArray {
           public static void main(String[] args) {
               int[] arr = new int[10];
               for(int i=0;i<arr.length;i++){
                   arr[i] = (int)(Math.random()*100);
                   System.out.println(arr[i]);
               }
       
               int max = arr[0]; //假设第1个元素为最大值
               for(int i=1;i<arr.length;i++){ //遍历剩余元素
                   if(arr[i]>max){ //若剩余元素大于max
                       max = arr[i]; //则修改max为较大的
                   }
               }
               System.out.println("最大值为:"+max);
       
               arr = Arrays.copyOf(arr,arr.length+1); //扩容
               arr[arr.length-1] = max; //将最大值max赋值给arr中的最后一个元素
               for(int i=0;i<arr.length;i++){
                   System.out.println(arr[i]);
               }
           }
       }
       ```
   
   - 排序：
   
     - Arrays.sort(arr); //升序排列(从小到大)
   
       ```java
       //8)数组的排序:
       int[] arr = new int[10];
       for(int i=0;i<arr.length;i++){
           arr[i] = (int)(Math.random()*100);
           System.out.println(arr[i]);
       }
       
       Arrays.sort(arr); //对arr进行升序排列
       
       System.out.println("数组排序后的数据:");
       for(int i=0;i<arr.length;i++){
           System.out.println(arr[i]);
       }
       
       System.out.println("倒序输出:");
       for(int i=arr.length-1;i>=0;i--){
           System.out.println(arr[i]);
       }
       ```
   
2. 方法：函数、过程

   - 封装一段特定的业务逻辑功能
   - 尽可能的独立，一个方法只干一件事
   - 方法可以被反复多次调用
   - 减少代码重复，有利于代码复用，有利于代码维护

3. 定义方法：五要素

   ​	修饰词    返回值类型   方法名(参数列表) {

   ​         方法体--------------具体的业务逻辑功能实现

   ​    }

   ```java
   //无参无返回值
   public static void say(){
       System.out.println("大家好，我叫WKJ，今年38岁了");
   }
   //有参无返回值
   public static void sayHi(String name){ //---------形参
       System.out.println("大家好，我叫"+name+"，今年38岁了");
   }
   //有参无返回值
   public static void sayHello(String name,int age){
       if(age>=35){ //在某种特定条件下，提前结束方法
           return; //结束方法
       }
       System.out.println("大家好，我叫"+name+"，今年"+age+"岁了");
   }
   
   //无参有返回值
   public static double getNum(){
       //在有返回值的方法中:
       //--必须得通过return来返回一个值，并且这个值的类型必须与返回值类型匹配
       //return "abc"; //编译错误，返回的值必须与返回值类型匹配
       return 8.88; //1)结束方法的执行  2)返回一个结果给调用方
   }
   
   //有参有返回值
   public static int plus(int num1,int num2){
       int num = num1+num2;
       return num; //返回的是num里面的那个数
       //return num1+num2; //返回的是num1与num2的和
   }
   //获取指定整型数组元素的最小值
   public static int getMinOfArray(int[] arr){
       int min = arr[0]; //假设第1个元素为最小值
       for(int i=1;i<arr.length;i++){
           if(arr[i]<min){
               min = arr[i];
           }
       }
       return min;
   }
   ```

4. 调用方法：

   - 无返回值：方法名(有参传参);
   - 有返回值：数据类型  变量  =  方法名(有参传参);

   ```java
   //say();
   
   //sayHi(); //编译错误，有参则必须传参
   //sayHi(250); //编译错误，参数类型必须匹配
   sayHi("zhangsan"); //String name="zhangsan" //-------实参
   sayHi("lisi"); //String name="lisi" //---------------实参
   sayHi("wangwu"); //String name="wangwu" //-----------实参
   
   sayHello("zhangsan",25); //实参
   sayHello("WKJ",38); //实参
   
   double a = getNum(); //getNum()的值就是8.88
   System.out.println(a); //8.88----模拟对返回值的后续操作
   
   int b = plus(5,6);
   System.out.println(b); //11----模拟对返回值的后续操作
   
   int m=5,n=6;
   int c = plus(m,n); //传递的是m和n里面的那个数
   System.out.println(c); //11----模拟对返回值的后续操作
   
   int[] arr = {13,45,1,35};
   int min = getMinOfArray(arr);
   System.out.println(min); //1----模拟对返回值的后续操作
   
   int[] a = new int[10];
   for(int i=0;i<a.length;i++){
       a[i] = (int)(Math.random()*100);
   }
   int b = getMinOfArray(a);
   System.out.println(b);
   ```

5. return：

   - return 值;  //1)结束方法的执行    2)返回结果给调用方

     ​                   ----------用在有返回值方法中

   - return;       //1)结束方法的执行-----------------用在无返回值的方法中





## 精华笔记：

1. 数组：

   - 复制：

     - System.arraycopy(a,1,b,0,4);

     - int[ ] b = Arrays.copyOf(a,6);

   - 排序：

     - Arrays.sort(arr); //升序排列(从小到大)

2. 方法：函数、过程

   - 封装一段特定的业务逻辑功能
   - 尽可能的独立，一个方法只干一件事
   - 方法可以被反复多次调用
   - 减少代码重复，有利于代码复用，有利于代码维护

3. 定义方法：五要素

   ​	修饰词    返回值类型   方法名(参数列表) {

   ​         方法体--------------具体的业务逻辑功能实现

   ​    }

4. 调用方法：

   - 无返回值：方法名(有参传参);
   - 有返回值：数据类型  变量  =  方法名(有参传参);

5. return：

   - return 值;  //1)结束方法的执行    2)返回结果给调用方

     ​                   ----------用在有返回值方法中

   - return;       //1)结束方法的执行

     ​                   -----------------用在无返回值的方法中





补充：

1. 形参：形式参数，定义方法时的参数为形参

   实参：实际参数，调用方法时的参数为实参



```java
public static void main(String[] args){
    say();
    sayHi("zhangsan");
    sayHello("zhangsan",25);
    double a = getNum(); //输出a(模拟对返回值的后续操作)
    int b=plus(5,6); //输出b(模拟对返回值的后续操作)
    int m=5,n=6;int c=plus(m,n); //输出c(模拟对返回值的后续操作)
    int[] arr={2,4,3};
    int d = getMinOfArray(arr); //输出d(模拟对返回值的后续操作)
}
public static void say(){...}
public static void sayHi(String name){...}
public static void sayHello(String name,int age){...}
public static double getNum(){ return 8.88; }
public static int plus(int num1,int num2){
    int num = num1+num2;  return num;  //return num1+num2;
}
public static int getMinOfArray(int[] arr){ ... }
```



```java
方法可以有参，也可以无参
----有参可以使方法更加灵活
    
何时有参？何时无参？
1)若方法中的数据都可以写成固定/写死的---------无参
2)若方法中的数据不是固定的数据-----------有参
    
double c = Math.random(); //0.0到0.9999999999999... 

假设random()有参:
  double c = Math.random(1,1000);
  double c = Math.random(0,99);
  double c = Math.random(20,50);
```

```java
System.out.            println("hello");
System.                arraycopy(a,1,b,0,4);
Arrays.                sort(arr);
int[]  b = Arrays.     copyOf(a,6); //--------有参数

int    a = scan.       nextInt();
double b = scan.       nextDouble();
double c = Math.       random(); //-----------无参数
```




```java
方法可以有返回值，也可以没有返回值
1)无返回值: 返回值类型统一设计为void
2)有返回值: 返回值类型设计为特定的数据类型即可
    
何时有返回值？何时没有返回值？
---方法操作完成后:
   1)若还需要用到方法中的某个数据---------有返回值
   2)若不需要用到方法中的数据------------无返回值
```

```java
System.out.println("hello");
System.arraycopy(a,1,b,0,4);
Arrays.sort(arr); //-------------------没有返回值

int    a = scan.nextInt();
double b = scan.nextDouble();
double c = Math.random();
int[]  b = Arrays.copyOf(a,6); //------有返回值

```

