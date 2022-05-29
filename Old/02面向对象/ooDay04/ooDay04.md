# 面向对象第四天：



## 潜艇游戏第一天：

1. 创建7个类，创建World类并测试

## 潜艇游戏第二天：

1. 给7个类添加构造方法，并测试



## 潜艇游戏第三天：

1. 设计侦察潜艇数组、鱼雷潜艇数组、水雷潜艇数组、鱼雷数组、水雷数组、深水炸弹数组，并测试
2. 设计SeaObject超类，设计7个类继承超类
3. 在SeaObject中设计两个构造方法，在派生类中分别调用



## 潜艇游戏第四天：

1. 将侦察潜艇、鱼雷潜艇、水雷潜艇统一组合为SeaObject数组，并测试

   将鱼雷、水雷统一组合为SeaObject数组，并测试

2. 在7个类中重写move()移动

3. 画窗口：-------在World类中不需要理解，晚上CV大法

   - import JFrame+JPanel
   - 设计World类继承JPanel
   - main中：CV大法



## 回顾：

1. 引用类型数组：

   ```java
   Student[] stus = new Student[3];
   stus[0] = new Student("zhangsan",25,"LF");
   System.out.println(stus[0].name);
   ```

2. 继承：

   - 作用：代码复用
   - extends
   - 超类：共有的      派生类：特有的
   - 派生类可以访问超类的，但超类不能访问派生类的
   - 单一继承
   - 传递性
   - 规定：构造派生类之前必须先构造超类
     - 派生类的构造方法中若没有调用超类构造方法，则默认super()调超类无参构造方法
     - 派生类的构造方法中若自己调用了超类的构造方法，则不再默认提供

3. super：指代当前对象的超类对象

   super.成员变量名---------------访问超类的成员变量

   super.方法名()--------------------调用超类的方法

   super()------------------------------调用超类的构造方法



## 正课：

1. 向上造型：
   - 超类型的引用指向派生类的对象
   
     超类型  引用  指向  派生类型
   
     Animal    o       =    new Tiger();//向上造型
   
   - 能点出来什么，看引用的类型-----------这是规定，记住就OK了
   
     o.只能点出来Animal里的来，Tiger里特有的点不出来
   
   代码演示：
   
   ```java
   package ooday04;
   //向上造型的演示
   public class UploadDemo {
       public static void main(String[] args) {
           Aoo o1 = new Aoo();
           o1.a = 1;
           o1.show();
           //o1.b = 2;  //编译错误，超类不能访问派生类的
           //o1.test(); //编译错误，超类不能访问派生类的
   
           Boo o2 = new Boo(); 
           o2.b = 1;
           o2.test();
           o2.a = 2;  //正确，派生类可以访问超类的
           o2.show(); //正确，派生类可以访问超类的
   
           Aoo o3 = new Boo(); //向上造型
           o3.a = 1;
           o3.show();
           //o3.b = 2;  //编译错误，能点出来什么，看引用的类型
           //o3.test(); //编译错误，能点出来什么，看引用的类型
       }
   }
   
   class Aoo{
       int a;
       void show(){
       }
   }
   class Boo extends Aoo{
       int b;
       void test(){
       }
   }
   ```
   
2. 方法的重写(override/overriding)：重新写

   - 发生在父子类中，方法名相同，参数列表相同

     ```java
     class Person{
         String name;
         int age;
         String address;
         Person(String name,int age,String address){
             this.name = name;
             this.age = age;
             this.address = address;
         }
         void sayHi(){
             System.out.println("大家好，我叫"+name+"，今年"+age+"岁了，家住"+address);
         }
     }
     class Student extends Person{
         String stuId; //学号
         Student(String name,int age,String address,String stuId){
             super(name,age,address);
             this.stuId = stuId;
         }
         void sayHi(){
             System.out.println("大家好，我叫"+name+"，今年"+age+"岁了，家住"+address+"，学号为:"+stuId);
         }
     }
     class Teacher extends Person{
         double salary; //工资
         Teacher(String name,int age,String address,double salary){
             super(name,age,address);
             this.salary = salary;
         }
         void sayHi(){
             System.out.println("大家好，我叫"+name+"，今年"+age+"岁了，家住"+address+"，工资为:"+salary);
         }
     }
     class Doctor extends Person{
         String level; //职称
         Doctor(String name,int age,String address,String level){
             super(name,age,address);
             this.level = level;
         }
     }
     ```

   - 重写方法被调用时，看对象的类型------这是规定，记住就OK了

     ```java
     package ooday04;
     //测试类
     public class Test {
         public static void main(String[] args) {
             //向上造型的好处:提高代码复用性
             //----将所有对象填充到一个数组中，只需要1个for即可
             Person[] ps = new Person[5];
             ps[0] = new Student("zhangsan",25,"LF","111"); //向上造型
             ps[1] = new Student("lisi",26,"JMS","222");
             ps[2] = new Teacher("wangwu",36,"SD",80000.0);
             ps[3] = new Teacher("zhaoliu",32,"SX",5000.0);
             ps[4] = new Doctor("sunqi",45,"LF","主任医师");
             for(int i=0;i<ps.length;i++){ //遍历Person数组
                 ps[i].sayHi(); //每个人跟大家问好
             }
     
             //重写方法被调用时，看对象的类型------这是规定
             Student zs = new Student("zhangsan",25,"LF","111");
             zs.sayHi(); //调用的是Student类的sayHi()方法
             Person p   = new Student("zhangsan",25,"LF","111");
             p.sayHi();  //调用的是Student类的sayHi()方法
         }
     }
     ```

   - 遵循"两同两小一大"原则：-----------------------了解

     - 两同：
       - 方法名相同
       - 参数列表相同
     - 两小：
       - 派生类方法的返回值类型小于或等于超类方法的
         - void和基本类型时，必须相同
         - 引用类型时，小于或等于
       - 派生类方法抛出的异常小于或等于超类方法的--------API讲
     - 一大：
       - 派生类方法的访问权限大于或等于超类方法的---------明天上午讲

     ```java
     package ooday04;
     //重写的演示
     public class OverrideDemo {
         public static void main(String[] args) {
     
         }
     }
     
     //超类大，派生类小
     class Coo{
         void show(){}
         double test(){ return 0.0; }
         Doo say(){ return null; }
         Coo sayHi(){ return null; }
     }
     class Doo extends Coo{
         //int show(){ return 1; } //编译错误，void时必须相同
         //int test(){ return 0; } //编译错误，基本类型时必须相同
         //Coo say(){ return null; } //编译错误，引用类型时必须小于或等于
         Doo sayHi(){ return null; }
     }
     ```

3. 重写与重载的区别：重点

   - 重写(override/overriding)：
     - 发生在父子类中，方法名相同，参数列表相同
   - 重载(overload/overloading)：
     - 发生在同一类中，方法名相同，参数列表不同



```java
练习:--------写在ooday05包中，需要上传作业
1.创建Person类，包含:
  1)成员变量:name,age,address
  2)构造方法:Person(3个参数){ 赋值 }
  3)方法:sayHi(){ 输出3个数据 }
2.创建学生类Student，继承Person，包含:
  1)成员变量:学号stuId(String)
  2)构造方法:Student(4个参数){ super调超类3参构造、赋值stuId }
  3)方法:重写sayHi(){ 输出4个数据 }
3.创建老师类Teacher，继承Person，包含:
  1)成员变量:工资salary(double)
  2)构造方法:Teacher(4个参数){ super调超类3参构造、赋值salary }
  3)方法:重写sayHi(){ 输出4个数据 }
4.创建医生类Doctor，继承Person，包含:
  1)成员变量:职称level(String)
  2)构造方法:Doctor(4个参数){ super调超类3参构造、赋值level }
5.创建测试类Test，main中:
  1)创建Person数组ps,包含5个元素,给元素赋值,遍历输出名字并问好
```



练习：-------------------在昨天晚课基础之上最少重做2遍

1. 将侦察潜艇、鱼雷潜艇、水雷潜艇统一组合为SeaObject数组，并测试

   将鱼雷、水雷统一组合为SeaObject数组，并测试

2. 在7个类中重写move()移动

3. 画窗口：



职业素质课--------------------------时间管理-----------6:30开始



