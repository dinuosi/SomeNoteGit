# 语言基础第六天：



## 回顾：

1. for结构：应用率最高

2. 选择规则：

3. break: 跳出循环

   continue: 跳过循环体中剩余语句而进入下一次循环

4. 嵌套循环：

   ​	循环中套循环，外层走一次，内层走所有次。

   ​    嵌套层数越少越好，break只能跳出当前一层循环

5. 数组：引用类型、相同数据类型元素的集合

   ```java
   int[] arr = new int[3]; //0,0,0
   int[] arr = {3,5,7};
   int[] arr = new int[]{3,5,7};
   arr[0] = 100;
   System.out.println(arr[arr.length-1]);
   for(int i=0;i<arr.length;i++){
       System.out.println(arr[i]);
   }
   ```



## 笔记:

1. 数组：

   - 复制：

     - java提供的System.arraycopy()方法，代码如下：

       ```java
       int[] a = {10,20,30,40,50};
       int[] b = new int[6]; //0,0,0,0,0,0
       //a:源数组
       //1:源数组的起始下标
       //b:目标数组
       //0:目标数组的起始下标
       //4:要复制的元素个数
       System.arraycopy(a,1,b,0,4);
       for(int i=0;i<b.length;i++){
           System.out.println(b[i]);
       }
       ```

     - 借助于java提供的Arrays类的copyOf()方法，代码如下：

       ```java
       int[] a = {10,20,30,40,50};
       //a:源数组
       //b:目标数组
       //6:目标数组的长度(元素个数)
       //----若目标数组长度>源数组长度，则在末尾补默认值
       //----若目标数组长度<源数组长度，则将末尾的截掉
       int[] b = Arrays.copyOf(a,4);
       for(int i=0;i<b.length;i++){
           System.out.println(b[i]);
       }
       ```

       > copyOf()可以实现数组的扩容，代码如下：

       ```java
       int[] a = {10,20,30,40,50};
       //数组的扩容(创建了一个更大的新数组，并将源数组数据复制进去了)
       a = Arrays.copyOf(a,a.length-1);
       for(int i=0;i<a.length;i++){
           System.out.println(a[i]);
       }
       ```

     综合案例如下所示：

     ```java
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
                     max = arr[i]; //则将max修改为较大的
                 }
             }
             System.out.println("最大值为:"+max);
     
             arr = Arrays.copyOf(arr,arr.length+1); //扩容
             arr[arr.length-1] = max; //将最大值max赋值给arr的最后一个元素
             for(int i=0;i<arr.length;i++){
                 System.out.println(arr[i]);
             }
         }
     }
     ```

   - 排序：

     - 排序是对数组所施加的最常用的算法
     - 排序分两种：升序(从小到大)、降序(从大到小)

     - 常见排序算法有：快速排序、冒泡排序、插入排序......

     - java自带的排序功能:  Arrays.sort(arr); //升序排列

     - 代码演示：

       ```java
       int[] arr = new int[10];
       for(int i=0;i<arr.length;i++){
           arr[i] = (int)(Math.random()*100);
           System.out.println(arr[i]);
       }
       
       Arrays.sort(arr); //对arr进行升序排列
       
       System.out.println("排序后:");
       for(int i=0;i<arr.length;i++){
           System.out.println(arr[i]);
       }
       ```

2. 方法：又称函数

   - 封装一段特定的业务逻辑功能
   - 方法尽可能的独立，一个方法只干一件事
   - 方法可以被反复多次调用
   - 可以减少代码重复，有利于代码的维护

3. 方法的定义：五要素

   - 语法：

     - 修饰词   返回值类型   方法名(参数列表) {

     ​          方法体---------具体的业务逻辑实现

     ​     }

   - 说明：

     - 方法可以有参，也可以无参。有参可以使方法更加灵活
     - 方法可以有返回值，也可以没有返回值
       - 没有返回值-------返回值类型统一写成void
       - 有返回值---------返回值类型写成特定的数据类型即可
     - 何时有返回值？何时没有返回值？------方法操作完成后:
       - 若还需要用到方法中的某个数据---------有返回值
       - 若不需要用到方法中的某个数据---------没有返回值

4. 方法的调用：

   - 无返回值：方法名(有参传参);
   - 有返回值：数据类型  变量  =  方法名(有参传参);

5. return：

   - return  值;  //1)结束方法的执行    2)返回结果给调用方-----应用率高

     -----------------用在有返回值的方法中

   - return;        //1)结束方法的执行----------------应用率不高

     ----------------用在无返回值的方法中

6. 完整代码演示：

   ```java
   public class MethodDemo {
       public static void main(String[] args) {
           //say();
   
           /*
           //sayHi(); //编译错误，有参则必须传参
           //sayHi(250); //编译错误，参数类型必须匹配
           sayHi("zhangsan"); //String name="zhangsan" //------------实参
           sayHi("lisi"); //String name="lisi" //--------------------实参
           sayHi("wangwu"); //String name="wangwu" //----------------实参
           */
   
           /*
           sayHello("zhangsan",25); //String name="zhangsan",int age=25 //实参
           sayHello("lisi",24); //----------------------------------------实参
           */
   
           /*
           double a = getNum(); //getNum()的值就是return后的那个数
           System.out.println(a); //8.88---模拟对返回值的后续操作
           */
   
           /*
           int b = plus(5,6);
           System.out.println(b); //11---模拟对返回值的后续操作
   
           int m=5,n=6;
           int c = plus(m,n); //传的是m和n里面的那个数
           System.out.println(c); //11---模拟对返回值的后续操作
           */
   
           /*
           int[] d = testArray();
           System.out.println(d.length); //4---模拟对返回值的后续操作
           */
   
           /*
           sayHello("zhangsan",25);
           sayHello("lisi",68);
           */
   
           a(); //方法的嵌套调用----1,3,2,4
           System.out.println(4);
       }
   
       public static void a(){
           System.out.println(1);
           b();
           System.out.println(2);
       }
       public static void b(){
           System.out.println(3);
       }
       //有参无返回值
       public static void sayHello(String name,int age){
           if(age>=50){ //在某种特定条件下提前结束方法
               return; //1)结束方法的执行
           }
           System.out.println("大家好，我叫"+name+"，今年"+age+"岁了");
       }
   
       //无参有返回值
       public static int[] testArray(){
           int[] arr = {2,5,8,1};
           return arr;
       }
   
       //有参有返回值
       public static int plus(int num1,int num2){
           int num = num1+num2;
           return num; //返回的是num里面的那个数
           //return num1+num2; //返回的是num1与num2的和
       }
   
       //无参有返回值
       public static double getNum(){
           //规定:有返回值的方法中，必须有return，并且return后的数必须与返回值类型匹配
           //return "abc"; //编译错误，返回值类型不匹配
           return 8.88; //1)结束方法的执行  2)返回8.88给调用方
       }
   
       //无参无返回值
       public static void say(){
           System.out.println("大家好，我叫WKJ，今年38岁了");
       }
       //有参无返回值
       public static void sayHi(String name){ //-----------------------形参
           System.out.println("大家好，我叫"+name+"，今年38岁了");
       }
       
   }
   ```



```java
public static void main(String[] args){
    say();
    sayHi("zhangsan");
    sayHello("zhangsan",25);
    double a = getNum();  输出a(模拟对返回值的后续操作)
    int b = plus(5,6); 输出b(模拟对返回值的后续操作) 
    int m=5,n=6; int c = plus(m,n); 输出c(模拟对返回值后续操作)
    int[] d = testArray(); 输出d.length(模拟对返回值的后续操作)
}
public static void say(){ ... }
public static void sayHi(String name){ ... }
public static void sayHello(String name,int age){ ... }
public static double getNum(){ return 8.8; }
public static int plus(int num1,int num2){
    int num = num1+num2;  return num;
}
public static int[] testArray(){
    int[] arr = {3,5,8,9};  return arr;
}
```



补充：

1. 形参：形式参数----定义方法时候的参数

   实参：实际参数----调用方法时候的参数



需上传的作业：

1. 练习方法的小代码

   

练习：--------------最少两遍

1. MaxOfArray求数组元素最大值，并放在最后一个元素的下一个位置

2. 练习Arrays.sort(arr)的小代码
3. 练习方法的小代码



