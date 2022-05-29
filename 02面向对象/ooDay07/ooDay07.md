# 面向对象第七天：

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

## 潜艇游戏第五天：

1. 将窗口的宽和高设计为常量，适当地方做修改

2. 画窗口：在World类中---共3步，不要求掌握，Ctrl C/V

   - import JFrame和JPanel
   - 设计World类继承JPanel
   - CV大法

3. 画海洋图、画对象

   ```java
   1)想画对象需要去获取对象的图片，每个对象都能获取图片，
     意味着获取图片行为为共有的行为，所以设计在SeaObject中，
     每个对象获取图片的行为都是不一样的，所以设计为抽象方法
     ----在SeaObject中设计getImage()获取对象的图片
   2)在6个派生类中重写getImage()获取对象的图片
     ----重写getImage()
   3)因为只有活着的对象才需要画到窗口中，所以需要设计对象的状态，
     每个对象都有状态，意味着状态为共有属性，所以设计在SeaObject中，
     状态一般设计为常量，同时设计state变量表示当前状态
     ----在SeaObject中设计LIVE、DEAD常量，state变量表示当前状态
     后期的业务中还需要判断状态，每个对象都得判断状态，
     意味着判断状态行为为共有的行为，所以设计在SeaObject中，
     每个对象判断状态的行为都是一样的，所以设计为普通方法
     ----在SeaObject中设计isLive()、isDead()判断对象的状态
   4)数据都有了就可以开画了，每个对象都能画，
     意味着画对象的行为为共有行为，所以设计在SeaObject中，
     每个对象画的行为都是一样的，所以设计为普通方法
     ----在SeaObject中设计paintImage()画对象
   5)画对象的行为做好了，在窗口World中调用即可:
     5.1)准备对象
     5.2)重写paint()画方法  
   ```

## 潜艇游戏第六天：

1. 潜艇入场：

   - 潜艇对象是由窗口产生的，所以在World类中设计nextSubmarine()生成潜艇对象

   - 潜艇入场为定时发生的，所以在run()中调用submarineEnterAction()实现潜艇入场

     在submarineEnterAction()中：

     ​	每400毫秒，获取潜艇对象obj，submarines扩容，将obj装到末尾

     > 注意: 在run中调用submarineEnterAction()后，调用repaint()重画

2. 水雷入场：

   - 水雷是由水雷潜艇发射出来的，所以在MineSubmarine中shootMine()生成水雷对象

   - 水雷入场为定时发生的，所以在run()中调用mineEnterAction()实现水雷入场

     在mineEnterAction()中：

     ​	每1秒钟，......(暂时搁置，周三时讲)

3. 海洋对象移动：

   - 海洋对象移动为共有的行为，所以在SeaObject中设计抽象方法move()实现海洋对象移动，在派生类重写move()

   - 海洋对象移动为定时发生的，所以在run()中调用moveAction()实现海洋对象移动

     在moveAction()中：

     ​	遍历所有潜艇潜艇move()，遍历所有水雷水雷move()，遍历所有深水炸弹深水炸弹move()

## 潜艇游戏第七天：

1. 深水炸弹入场：

   - 深水炸弹是战舰发射出来的，所以在Battleship中设计shoot()实现发射深水炸弹

   - 深水炸弹入场为事件触发的，所以在侦听器中重写keyReleased()键盘抬起事件，在keyReleased()中判断若抬起的是空格键：---不需要掌握

     ​	则获取炸弹对象obj，bombs扩容，obj装末尾

2. 战舰移动：

   - 战舰移动为战舰的行为，所以在Battleship中设计moveLeft()左移、moveRight()右移

   - 战舰移动为事件触发的，在重写keyReleased()中：

     ​	判断若抬起的是左键头，则调用moveLeft()左移

     ​	判断若抬起的是右键头，则调用moveRight()右移

3. 删除越界的海洋对象：

   - 在SeaObject中设计isOutOfBounds()检测潜艇是否越界

     在Bomb中重写isOutOfBounds()检测深水炸弹是否越界

     在Mine中重写isOutOfBounds()检测水雷是否越界

   - 删除越界的海洋对象为定时发生的，所以在run()在调用outOfBoundsAction()删除越界的海洋对象

     在outOfBoundsAction()中：

     ​	遍历所有潜艇/水雷/深水炸弹，判断若对象越界了：

     ​		则将越界对象替换为最后一个元素，缩容

4. 设计EnemyScore得分接口，侦察潜艇与鱼雷潜艇实现得分接口

   设计EnemyLife得命接口，水雷潜艇实现得命接口





## 回顾：

1. 成员内部类：

   ​	类中套类，内部类只能在外部类中创建，对外不具备可见性

   ​    内部类可以直接访问外部类的成员(包括私有的)

   ​    内部类中有个隐式的引用指向了创建它的外部类对象   外部类名.this

2. 匿名内部类：----------------大大简化代码

   ​     若想创建一个类(派生类)的对象，并且对象只被创建一次，此时该类不必命名，称为匿名内部类



## 笔记：

1. 接口：

   - 是一种引用数据类型

   - 由interface定义

   - 只能包含常量和抽象方法------默认权限是public

   - 接口不能被实例化

   - 接口是需要被实现/继承，实现/派生类：必须重写所有抽象方法

   - 一个类可以实现多个接口，用逗号分隔，

     若又继承又实现时，应先继承后实现

   - 接口可以继承接口

   - 接口的意义：

     - 封装部分派生类共有的属性和行为，实现多继承
     - 制定了一个标准，一种规范

   ```java
   public class InterfaceDemo {
       public static void main(String[] args) {
           //Inter o = new Inter(); //编译错误，接口不能被实例化
           Inter5 o1 = new Doo(); //向上造型
           Inter4 o2 = new Doo(); //向上造型
       }
   }
   
   //演示接口的定义
   interface Inter{
       public static final int NUM = 5;
       public abstract void show();
       int COUNT = 5; //默认public static final   //接口中的成员默认访问权限是public
       void test(); //默认public abstract
       //int number; //编译错误，常量必须声明同时初始化
       //void say(){} //编译错误，抽象方法不能有方法体
   }
   
   //演示接口的定义
   interface Inter{
       public static final int NUM = 5;
       public abstract void show();
       int COUNT = 5; //默认public static final   //接口中的成员默认访问权限是public
       void test(); //默认public abstract
       //int number; //编译错误，常量必须声明同时初始化
       //void say(){} //编译错误，抽象方法不能有方法体
   }
   
   //演示接口的实现
   interface Inter1{
       void show();
       void test();
   }
   class Aoo implements Inter1{
       public void show(){} //重写接口中的抽象方法时，访问权限必须设计为public的
       public void test(){}
   }
   
   //演示接口的多实现
   interface Inter2{
       void show();
   }
   interface Inter3{
       void test();
   }
   abstract class Boo{
       abstract void say();
   }
   class Coo extends Boo implements Inter2,Inter3{
       public void show(){}
       public void test(){}
       void say(){}
   }
   
   //演示接口继承接口
   interface Inter4{
       void show();
   }
   interface Inter5 extends Inter4{
       void test();
   }
   class Doo implements Inter5{
       public void test(){}
       public void show(){}
   }
   ```



## 精华笔记：

1. 接口：

   - 是一种引用数据类型

   - 由interface定义

   - 只能包含常量和抽象方法------默认权限是public

   - 接口不能被实例化

   - 接口是需要被实现/继承，实现/派生类：必须重写所有抽象方法

   - 一个类可以实现多个接口，用逗号分隔，

     若又继承又实现时，应先继承后实现

   - 接口可以继承接口

   - 接口的意义：

     - 封装部分派生类共有的属性和行为，实现多继承
     - 制定了一个标准，一种规范



## 补充：

1. 类和类------------------------继承extends

   接口和接口------------------继承extends

   类和接口---------------------实现implements

2. 设计规则：

   - 将所有派生类所共有的属性和行为，抽到超类中-------------抽共性

   - 派生类的行为都一样，则设计为普通方法

     派生类的行为不一样，则设计为抽象方法

   - 将部分派生类所共有的属性和行为，抽到接口中

     接口是对继承的单根性的扩展---------------实现多继承

     符合既是也是原则时，应使用接口

3. 可以向上造型为：超类+所实现的接口



