# 面向对象第四天：

## 潜艇游戏第一天：

1. 设计6个类，设计World类并测试

## 潜艇游戏第二天：

1. 给6个类添加构造方法，并测试

## 潜艇游戏第三天：

1. 设计SeaObject超类，6个类继承SeaObject
2. 给SeaObject设计两个构造方法，6个派生类分别调用
3. 设计潜艇数组、水雷数组、深水炸弹数组，并测试

## 潜艇游戏第四天：

1. 在6个类中重写move()移动
2. 给类中成员添加访问控制修饰符
3. 设计Images图片类



## 回顾：

1. 引用类型数组：

   ```java
   Bomb[] bs = new Bomb[3];
   ...
   for(int i=0;i<bs.length;i++){
       System.out.println(bs[i].x+","+bs[i].y);
       bs[i].move();
   }
   ```

2. 继承：

   ​	代码复用，extends

   ​    超类：共有的      派生类：特有的

   ​    派生类可以访问派生类自己的+超类的，超类只能访问超类自己的

   ​    单一继承，传递性

   ​    构造派生类之前必须先构造超类

   ​    -----super()调用构造方法必须位于派生类构造方法的第一行

   ​    派生类构造中若自己不调超类构造，则默认super()调超类无参构造

   ​    若自己调用了，则不再默认提供

3. super：指代当前对象的超类对象

   super.成员变量名---------------------------访问超类的成员变量

   super.方法名()-------------------------------调用超类的方法--------一会讲

   super()-----------------------------------------调用超类的构造方法

4. 向上造型：-------------------------------------提高复用性

   - 超类型的引用指向派生类的对象
   - 能点出来什么，是看引用的类型



## 笔记：

1. 方法的重写(override/overriding)：重新写、覆盖

   - 发生在父子类中，方法名相同，参数列表相同
   - 重写方法被调用时，看对象的类型------------这是规定，记住就OK

   >当派生类觉得超类的行为不够好时，可以重写

   ```java
   我继承了一个中餐馆
   class Aoo{
       void do(){
           做中餐
       }
   }
   A:我还是想做中餐------------不需要重写
       class Boo extends Aoo{
       }
   B:我想改做西餐--------------需要重写
       class Boo extends Aoo{
           void do(){
           	做西餐
       	}
       }
   C:我想在中餐基础之上加西餐-----需要重写(先super中餐，再加入西餐)
       class Boo extends Aoo{
           void do(){
           	super.do();
               做西餐
       	}
       }
   ```

2. 重写与重载的区别：重点(常见的面试题)

   - 重写：发生在父子类中，方法名相同，参数列表相同

     > 一般用于在派生类中修改超类的方法

   - 重载：发生在同一类中，方法名相同，参数列表不同

     > 是完全不同的方法，只是方法名相同而已

3. package和import：

   - package：声明包

     - 作用：避免类的命名冲突
     - 同包中的类不能同名，不同包中的类可以同名
     - 类的全称：包名.类名，包名常常有层次结构
     - 建议：包名所有字母都小写

     > 说明：package声明包必须位于第一行

   - import：导入类

     - 同包中的类可以直接访问，不同包的类不能直接访问，若想访问：
       - 先import导入类，再访问类----------建议
       - 类的全称-----------------------------------太繁琐，不建议

     > 说明：import导入类必须位于声明包的下一行

4. 访问控制修饰符：

   > 封装的意义：隐藏一些东西，暴露一些东西，来保护数据的安全

   - public：公开的，任何类
   - private：私有的，本类
   - protected：受保护的，本类、派生类、同包类
   - 默认的：什么也不写，本类、同包类

   > 1. 类的访问权限只能是public或默认的
   >
   > 2) 类中成员的访问权限如上四种都可以

   ```java
   //封装的意义
   class Card{
       private String cardId;
       private String cardPwd;
       private double balance;
       
       public boolean payMoney(double money){ //支付金额
           if(balance>=money){
               balance-=money;
               return true;
           }else{
               return false;
           }
       }
       
       public boolean checkPwd(String pwd){ //检测密码
           if(pwd与cardPwd相同){
               return true;
           }else{
               return false;
           }
       }
   }
   ```

   

   ```java
   //访问权限范围:
   package ooday04;
   //演示访问控制修饰符
   public class Aoo {
       public int a;     //任何类
       protected int b;  //本类、派生类、同包类
       int c;            //本类、同包类
       private int d;    //本类
   
       void show(){
           a = 1;
           b = 2;
           c = 3;
           d = 4;
       }
   }
   
   class Boo{ //---------------演示private
       void show(){
           Aoo o = new Aoo();
           o.a = 1;
           o.b = 2;
           o.c = 3;
           //o.d = 4; //编译错误
       }
   }
   
   package ooday04_vis;
   import ooday04.Aoo;
   public class Coo { //演示同包的概念
       void show(){
           Aoo o = new Aoo();
           o.a = 1;
           //o.b = 2; //编译错误
           //o.c = 3; //编译错误
           //o.d = 4; //编译错误
       }
   }
   
   class Doo extends Aoo{ //演示protected
       void show(){
           a = 1;
           b = 2;
           //c = 3; //编译错误
           //d = 4; //编译错误
       }
   }
   ```

5. static：静态的

   - 静态变量：

     - 由static修饰
     - 属于类，存储在方法区中，只有一份
     - 常常通过类名点来访问
     - 何时用：所有对象所共享的数据(图片、音频、视频等)

     ```java
     public class StaticDemo {
         public static void main(String[] args) {
             Eoo o1 = new Eoo();
             o1.show();
             Eoo o2 = new Eoo();
             o2.show();
             Eoo o3 = new Eoo();
             o3.show();
             System.out.println(Eoo.b); //常常通过类名点来访问
         }
     }
     
     class Eoo{ //演示静态变量
         int a;
         static int b;
         Eoo(){
             a++;
             b++;
         }
         void show(){
             System.out.println("a="+a+",b="+b);
         }
     }
     ```

   - 静态方法：

     - 由static修饰
     - 属于类，存储在方法区中，只有一份
     - 常常通过类名点来访问
     - 静态方法没有隐式this传递，所以不能直接访问实例成员
     - 何时用：方法的操作与对象无关

     ```java
     //static的演示
     public class StaticDemo {
         public static void main(String[] args) {
             Goo.plus(4,6);
         }
     }
     
     //演示静态方法
     class Foo{
         int a; //实例变量(由对象来访问)
         static int b; //静态变量(由类名来访问)
     
         void show(){ //有隐式this
             System.out.println(this.a);
             System.out.println(Foo.b);
         }
         static void test(){
             //静态方法中没有隐式this传递
             //没有this就意味着没有对象
             //而实例变量a是必须由对象来访问的
             //所以下面的语句发生编译错误
             //System.out.println(a); //编译错误
     
             System.out.println(Eoo.b);
         }
     }
     
     //演示静态方法何时用
     class Goo{
         int a; //对象的属性
         //方法中用到了对象的属性a，意味着show()的操作与对象是有关的，不能做成静态方法
         void show(){
             System.out.println(a);
         }
         //方法中没有用到对象的属性和行为，意味着plus()的操作与对象是无关的，可以做成静态方法
         static void plus(int num1,int num2){
             int num = num1+num2;
             System.out.println(num);
         }
     }
     ```

   - 静态块：

     - 由static修饰
     - 属于类，在类被加载期间自动执行，一个类只被加载一次，所以静态块也只执行一次
     - 何时用：初始化/加载静态资源(图片、音频、视频等)

     ```java
     public class StaticDemo {
         public static void main(String[] args) {
             Hoo o4 = new Hoo();
             Hoo o5 = new Hoo();
             Hoo o6 = new Hoo();
         }
     }
     //演示静态块
     class Hoo{
         static {
             System.out.println("静态块");
         }
         Hoo(){
             System.out.println("构造方法");
         }
     }
     ```



## 精华笔记

1. 方法的重写(override/overriding)：重新写、覆盖

   - 发生在父子类中，方法名相同，参数列表相同
   - 重写方法被调用时，看对象的类型------------这是规定，记住就OK

   >当派生类觉得超类的行为不够好时，可以重写

2. 重写与重载的区别：重点(常见的面试题)

   - 重写：发生在父子类中，方法名相同，参数列表相同

     > 一般用于在派生类中修改超类的方法

   - 重载：发生在同一类中，方法名相同，参数列表不同

     > 是完全不同的方法，只是方法名相同而已

3. package和import：

   - package：声明包

     - 作用：避免类的命名冲突
     - 同包中的类不能同名，不同包中的类可以同名
     - 类的全称：包名.类名，包名常常有层次结构
     - 建议：包名所有字母都小写

     > 说明：package声明包必须位于第一行

   - import：导入类

     - 同包中的类可以直接访问，不同包的类不能直接访问，若想访问：
       - 先import导入类，再访问类----------建议
       - 类的全称-----------------------------------太繁琐，不建议

     > 说明：import导入类必须位于声明包的下一行

4. 访问控制修饰符：

   > 封装的意义：隐藏一些东西，暴露一些东西，来保护数据的安全

   - public：公开的，任何类
   - private：私有的，本类
   - protected：受保护的，本类、派生类、同包类
   - 默认的：什么也不写，本类、同包类

   > 1. 类的访问权限只能是public或默认的
   >
   > 2) 类中成员的访问权限如上四种都可以

5. static：静态的

   - 静态变量：
     - 由static修饰
     - 属于类，存储在方法区中，只有一份
     - 常常通过类名点来访问
     - 何时用：所有对象所共享的数据(图片、音频、视频等)
   - 静态方法：
     - 由static修饰
     - 属于类，存储在方法区中，只有一份
     - 常常通过类名点来访问
     - 静态方法没有隐式this传递，所以不能直接访问实例成员
     - 何时用：方法的操作与对象无关
   - 静态块：
     - 由static修饰
     - 属于类，在类被加载期间自动执行，一个类只被加载一次，所以静态块也只执行一次
     - 何时用：初始化/加载静态资源(图片、音频、视频等)





补充：

1. 成员变量分两种：

   - 实例变量：没有static修饰，属于对象的，存储在堆中，

     ​                    有几个对象就有几份，通过引用打点来访问

   - 静态变量：有static修饰，属于类的，存储在方法区中，

     ​                    只有一份，通过类名打点来访问

2. 内存管理：由JVM来管理的

   - 堆：new出来的对象(包括成员变量)
   - 栈：局部变量(包括方法的参数)
   - 方法区：.class字节码文件(包括静态变量、所有方法)

3. 在构造方法中给实例变量做初始化

   在静态块中给静态变量做初始化

   













