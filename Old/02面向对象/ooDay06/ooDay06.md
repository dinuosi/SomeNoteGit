# 面向对象第六天：



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

## 潜艇游戏第五天：

1. 给类中成员添加访问控制修饰符
2. 设计Images图片类

## 潜艇游戏第六天：

1. 设计窗口的宽和高为常量，适当地方做修改

2. 画海洋图、画对象：

   ```java
   1)想画对象需要去获取对象的图片，每个对象都能获取图片，
     意味着获取图片行为为共有的行为，所以设计在SeaObject中，
     每个对象获取图片的行为都是不一样的，所以设计为抽象方法
     ---在SeaObject中设计getImage()获取对象的图片
   2)在派生类中重写getImage()获取对象图片
   3)因为只有活着的对象才需要画到窗口中，所以需要设计对象的状态，
     每个对象都有状态，意味着状态为共有属性，所以设计在SeaObject中，
     状态一般都设计为常量，同时设计state变量表示当前状态
     ---在SeaObject中设计LIVE、DEAD状态常量，state变量
     状态有了还需要判断状态，每个对象都得判断状态，
     意味着判断状为行为为共有的行为，所以设计在SeaObject中，
     每个对象判断状态的行为都是一样的，所以设计为普通方法
     ---在SeaObject中设计isLive()、isDead()判断状态
   4)数据都有了就可以开画了，每个对象都能画，
     意味着画对象的行为为共有行为，所以设计在SeaObject中，
     每个对象画的行为都是一样的，所以设计为普通方法
     ---在SeaObject中设计paintImage()画对象
   5)画对象的行为做好了，在窗口World中调用即可:
     5.1)准备对象
     5.2)重写paint()画方法:调用paintImage()方法
   ```
   



## 回顾：

1. package和import

2. 访问控制修饰符----------------------保护数据的安全

   - public：公开的，任何类
   - private：私有的，本类
   - protected：受保护的，本类、派生类、同包类
   - 默认的：什么也不写，本类、同包类

3. final：最终的、不可改变的

   ​	       变量不能被改变、方法不能被重写、类不能被继承

4. static：静态的

   - 静态变量：

     ​	static、类、方法区中、一份、

     ​    所有对象所共享的数据(图片、音频、视频等)

   - 静态方法：

     ​	static、类、方法区中、一份、

     ​    没有隐式this，不能直接访问实例成员

     ​    方法的操作与对象无关

   - 静态块：

     ​	static、类、在类被加载期间自动执行、一次

     ​    初始化/加载静态资源(图片、音频、视频等)

   

## 笔记：

1. static final常量： 应用率高

   - 必须声明同时初始化
   - 通过类名点来访问，不能被改变
   - 建议：所有字母都大写，多个单词用_分隔
   - 编译器在编译时会将常量直接替换为具体的值，效率高
   - 何时用：数据永远不变，并且经常使用

   ```java
   public class StaticFinalDemo {
       public static void main(String[] args) {
           System.out.println(Aoo.PI); //通过类名点来访问
           //Aoo.PI = 3.1415926; //编译错误，常量不能被改变
   
           //1)加载Boo.class到方法区中
           //2)静态变量num存储到方法区中
           //3)到方法区中获取num的值并输出
           System.out.println(Boo.num);
   
           //编译器在编译时会将常量直接替换为具体的值，效率高
           //相当于System.out.println(5);
           System.out.println(Boo.COUNT);
   
       }
   }
   
   class Boo{
       public static int num = 5;   //静态变量
       public static final int COUNT = 5; //常量
   }
   
   class Aoo{
       public static final double PI = 3.14159;
       //public static final int NUM; //编译错误，常量必须声明同时初始化
   }
   ```

2. 抽象方法：

   - 由abstract修饰
   - 只有方法的定义，没有具体的实现(连{}都没有)

3. 抽象类：

   - 由abstract修饰

   - 包含抽象方法的类必须是抽象类

   - 抽象类不能被实例化(new对象)

   - 抽象类是需要被继承的，派生类：

     - 重写所有抽象方法-----------------变不完整为完整
     - 也声明为抽象类--------------------一般不这么用

   - 抽象类的意义：

     - 封装共有的属性和行为----------------代码复用

     - 为所有派生类提供统一的类型-------向上造型

     - 可以包含抽象方法，为所有派生类提供统一的入口(能点出来)，

       派生类的行为不同，但入口是一致的，抽象方法可以达到强制必须重写目的



补充：

1. 设计规则：

   - 将派生类所共有的属性和行为，抽到超类中----------抽共性

   - 派生类的行为都一样，设计为普通方法

     派生类的行为都不一样，设计为抽象方法

   - ...-----------------周五讲

2. 抽象方法的问题：

   - 问:抽象方法的意义是什么?
     答:存在的意义仅在于当向上造型时，通过超类的引用能点出那个方法来
          ----保证能点出来
   - 问:设计为普通方法也能保证能点出来，为什么要设计为抽象方法呢?
     答:设计为普通方法时，派生类可以重写也可以不重写
          而设计为抽象方法，意味着可以强制派生类必须重写
          ----强制必须重写



练习：-------------------在昨天晚课的基础之上最少做两遍

1. 设计窗口的宽和高为常量，适当地方做修改
2. 画海洋图、画对象：































