# 面向对象第一天：

## 潜艇游戏第一天：

1. 创建6个类，创建World类并测试



## 回顾：

1. 数组：

   - 复制：

     - System.arraycopy(a,1,b,0,4);

     - int[] b = Arrays.copyOf(a,6);

       a = Arrays.copyOf(a,a.length+1);

   - 排序：

     - Arrays.sort(arr);

2. 方法：函数、过程

   ​	封装一段特定的业务逻辑功能、只干一件事、可以被反复调用、

   ​	减少代码重复，有利于代码的复用、有利于代码的维护

3. 方法的定义：

   ​	修饰词  返回值类型  方法名(参数列表) {

   ​        方法体

   ​    }

4. 方法的调用：

   - 无返回值：方法名(有参传参);
   - 有返回值：数据类型  变量 = 方法名(有参传参);

5. return：

   - return 值;  //1)结束方法  2)返回结果给调用方
   - return;       //1)结束方法



## 笔记：

1. 什么是类？什么是对象？

   - 现实生活中是由很多很多对象组成的，基于对象抽出了类

   - 对象：软件中真实存在的单个个体/东西

     类：类别/类型，代表一类个体

   - 类是对象的模子，对象是类的具体的实例

   - 类中可以包含：

     - 对象的属性/特征-----------------------成员变量
     - 对象的行为/动作-----------------------方法

   - 一个类可以创建多个对象

2. 如何创建类？如何创建对象？如何访问成员？

   ```java
   public class Student { //Student类就是我们自己造的一种引用类型
       //成员变量
       String name;
       int age;
       String address;
       //方法
       void study(){
           System.out.println(name+"在学习...");
       }
       void sayHi(){
           System.out.println("大家好，我叫"+name+"，今年"+age+"岁了，家住"+address);
       }
   }
   
   public class StudentTest {
       public static void main(String[] args){
           //创建一个学生对象
           Student zs = new Student();
           //给成员变量赋值
           zs.name = "zhangsan";
           zs.age = 25;
           zs.address = "河北廊坊";
           //调用方法
           zs.study();
           zs.sayHi();
   
           Student ls = new Student();
           ls.name = "lisi";
           ls.age = 24;
           ls.address = "黑龙江佳木斯";
           ls.study();
           ls.sayHi();
   
           //1)创建了一个学生对象
           //2)给所有成员变量赋默认值
           Student ww = new Student();
           ww.study();
           ww.sayHi();
   
       }
   }
   ```

3. 方法的重载(overload/overloading)：---------------更加方便用户的访问

   - 发生在同一类中，方法名相同，参数列表不同
   - 编译器在编译时会根据方法的签名自动绑定方法

   ```java
   //重载的演示
   public class OverloadDemo {
       public static void main(String[] args) {
           Aoo o = new Aoo();
           o.show(); //编译器根据方法的签名自动绑定方法
           o.show("zhangsan");
           o.show(25);
           o.show("zhangsan",25);
           o.show(25,"zhangsan");
       }
   }
   
   class Aoo{
       void show(){}
       void show(String name){}
       void show(int age){}
       void show(String name,int age){}
       void show(int age,String name){}
       //int show(){ return 1;} //编译错误，重载与返回值类型无关
       //void show(String address){} //编译错误，重载与参数名称无关
   }
   ```



## 精华笔记：

1. 什么是类？什么是对象？

   - 现实生活中是由很多很多对象组成的，基于对象抽出了类

   - 对象：软件中真实存在的单个个体/东西

     类：类别/类型，代表一类个体

   - 类是对象的模子，对象是类的具体的实例

   - 类中可以包含：

     - 对象的属性/特征-----------------------成员变量
     - 对象的行为/动作-----------------------方法

   - 一个类可以创建多个对象

2. 如何创建类？如何创建对象？如何访问成员？

3. 方法的重载(overload/overloading)：---------------更加方便用户的访问

   - 发生在同一类中，方法名相同，参数列表不同
   - 编译器在编译时会根据方法的签名自动绑定方法




潜艇游戏需求：

1. 所参与的角色：
   - 战舰、深水炸弹、侦察潜艇、鱼雷潜艇、水雷潜艇、水雷
2. 角色间的关系：
   - 战舰发射深水炸弹
   - 深水炸弹可以打潜艇(侦察潜艇、鱼雷潜艇、水雷潜艇)，若打中：
     - 潜艇消失、深水炸弹消失
     - 得东西：
       - 打掉侦察潜艇，玩家得10分
       - 打掉鱼雷潜艇，玩家得40分
       - 打掉水雷潜艇，战舰得1条命
   - 水雷潜艇可以发射水雷
   - 水雷可以击打战舰，若击中：
     - 水雷消失
     - 战舰减1条命(命数为0时游戏结束)



补充：

1. 高质量的代码：---------------------以后的目标、拿年薪

   - 复用性好、扩展性好、维护性好、可移植性好\健壮性好、可读性好、效率好......

2. 默认值规则：

   ```java
   byte,short,int,long,char-------------0
   float,double-------------------------0.0
   boolean------------------------------false
   引用类型------------------------------null  
   ```

3. ```java
   //若想访问对象，需要通过引用zs
               引用
   数据类型  引用类型变量   指向       对象
   Student     zs         =    new Student(); 
   ```

4. 方法的签名：方法名+参数列表



