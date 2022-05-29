# 面向对象第八天：

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

## 潜艇游戏第七天：

1. 潜艇入场：

   - 潜艇对象是由窗口产生的，所以在World类中设计nextSubmarine()生成潜艇对象

   - 潜艇入场为定时发生的，所以在run中调用submarineEnterAction()潜艇入场

     在submarineEnterAction()中：

     ​	每400毫秒，获取潜艇对象obj，submarines扩容 ，将obj装到末尾

     > 注：在run()中调用submarineEnterAction()下，一定要调用repaint()来重画

2. 雷入场：

   - 雷对象是由潜艇产生的，所以在SeaObject类中设计nextThunder()生成雷对象

   - 雷入场为定时发生的，所以在run中调用thunderEnterAction()雷入场

     在thunderEnterAction()中：

     ​	每1000毫秒，遍历潜艇数组，获取潜艇发出来的雷obj，判断若有雷：

     ​		thunders扩容，将obj装到末尾

3. 海洋对象移动：

   - 海洋对象移动为共有行为，所以在超类SeaObject中设计抽象move()，派生类重写

   - 海洋对象移动为定时发生的，所以在run中调用moveAction()海洋对象移动

     在moveAction()中：

     ​	遍历所有潜艇潜艇move()，遍历所有雷雷move()，遍历所有深水炸弹炸弹move()

## 潜艇游戏第八天：

1. 深水炸弹入场：

   - 深水炸弹是由战舰发射出来的，所以在Battleship中设计nextBomb()生成深水炸弹

   - 深水炸弹入场为事件触发的，所以在侦听器中重写keyReleased()按键弹起事件：

     在keyReleased()中：

     ​	判断若按下的是空格键：获取深水炸弹obj，bombs扩容，将obj装到末尾

2. 战舰移动：

   - 战舰移动为战舰的行为，所以在Battleship中设计moveLeft()左移、moveRight()右移

   - 在侦听器的重写keyReleased()中：

     ​	判断若按下的是左移键，则调用moveLeft()

     ​	判断若按下的是右移键，则调用moveRight()

3. 删除越界的海洋对象

   - 在SeaObject中设计isOutOfBounds()判断潜艇是否越界

     在Bomb中重写isOutOfBounds()判断深水炸弹是否越界

     在Torpedo中重写isOutOfBounds()判断鱼雷是否越界

     在Mine中重写isOutOfBounds()判断水雷是否越界

   - 删除越界的海洋对象为定时发生的，所以在run中调用outOfBoundsAction()删除越界

     在outOfBoundsAction()中：

     ​	遍历所有潜艇，判断若潜艇越界了，则将越界潜艇替换为最后一个元素，缩容

     ​	遍历所有雷，判断若雷越界了，则将越界雷替换为最后一个元素，缩容

     ​    遍历所有深水炸弹，判断若深水炸弹越界了，则将越界炸弹替换为最后一个元素，缩容

4. 设计EnemyScore得分接口，侦察潜艇和鱼雷潜艇实现

   设计EnemyLife得命接口，水雷潜艇实现



## 回顾：

1. 成员内部类：了解

   ​	类中套类，内部类对外不具备可见性，内部类对象只能在外部类中创建

   ​	内部类中可以直接访问外部类的成员(包括私有的)，外部类名.this----外部类对象

2. 匿名内部类：

   ​	若想创建一个类(派生类)的对象，并且对象只被创建一次，此时该类不必命名，称为匿名内部类

   ​	匿名内部类中不能修改外面变量的值，因为在此处变量会默认为final的

   ​	



## 笔记：

1. 接口：

   - 是一种数据类型(引用类型)
   - 由interface定义的
   - 只能包含常量和抽象方法(访问权限都默认为public的)
   - 接口不能被实例化(new对象)
   - 接口是需要被实现/继承的，实现/派生类：必须重写所有抽象方法
   - 一个类可以实现多个接口，用逗号分隔。若又继承又实现时，应先继承后实现
   - 接口可以继承接口

   ```java
   //接口的演示
   public class InterfaceDemo {
       public static void main(String[] args) {
           //Inter5 o = new Inter5(); //编译错误，接口不能被实例化
           Inter5 o1 = new Doo(); //向上造型
           Inter4 o2 = new Doo(); //向上造型
       }
   }
   
   //演示接口的语法
   interface Inter{
       public static final int NUM = 5;
       public abstract void show();
       int COUNT = 6; //默认public static final
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
       public void show(){} //重写接口中的抽象方法时，必须加public权限
       public void test(){}
   }
   
   //演示接口的多实现，又继承又实现
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



补充：

1. 设计规则：

   - 将所有派生类所共有的属性和行为，抽到超类中

   - 若派生类的行为都一样，则设计为普通方法

     若派生类的行为都不一样，则设计为抽象方法

   - 将部分派生类所共有的属性和行为，抽到接口中

     接口是对继承的单根性的扩展----------------实现多继承

2. 深水炸弹与潜艇的碰撞，若撞上了：---------------明天讲
   - 炸弹消失、潜艇消失---------------明天讲
   - 若撞上的是侦察潜艇，则玩家得10分

​               若撞上的是鱼雷潜艇，则玩家得40分

​               若撞上的是水雷潜艇，则战舰得1条命

​               若撞上的是核潜艇，则战舰得3条命，玩家得80分

​	分析代码如下：

```java
abstract class SeaObject{
    int width,height,x,y,speed;
    abstract void move();
    boolean isLive(){...}
}
interface EnemyScore{ //得分接口
    int getScore(); //得分
}
interface EnemyLife{ //得命接口-----留着的目的是为了以后的扩展
    int getLife(); //得命
}
class ObserverSubmarine extends SeaObject implements EnemyScore{ //侦察潜艇 
    public int getScore(){ return 10; }
}
class TorpedoSubmarine extends SeaObject implements EnemyScore{ //鱼雷潜艇 
    public int getScore(){ return 40; }
}
class MineSubmarine extends SeaObject implements EnemyLife{ //水雷潜艇
    public int getLife(){ return 1; }
}
class HeSubmarine extends SeaObject implements EnemyLife,EnemyScore{ //核潜艇
    public int getLife(){ return 3; }
    public int getScore(){ return 80; }
}
class Battleship extends SeaObject{ //战舰
}
class Torpedo extends SeaObject{ //鱼雷    
}
class Mine extends SeaObject{ //水雷    
}
class Bomb extends SeaObject{ //深水炸弹
}
```

3. 如何调错：-----------------------靠经验积累，调得多了就会调了

   - 快速定位错误方法：
     - 将调用方法全都注释掉，然后一个一个放开，放开哪个方法后出错，问题就在哪个方法上
   - 打桩：
     - System.out.println(数据);

   

练习：----------------将今天的代码在昨天基础之上最少写两遍

1. 深水炸弹入场
2. 战舰移动
3. 删除越界的海洋对象
4. 设计两个接口

