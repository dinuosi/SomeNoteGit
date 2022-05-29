# 面向对象第十天：

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

## 潜艇游戏第九天：

1. 深水炸弹与潜艇的碰撞：

   - 在SeaObject中设计isHit()检测碰撞、goDead()海洋对象去死

     在Battleship中设计addLife()战舰增命

   - 深水炸弹与潜艇的碰撞为定时发生的，所以在run中调用bombBangAction()实现深水炸弹与潜艇的碰撞

     在bombBangAction()中：

     ​	遍历深水炸弹得深水炸弹，遍历潜艇得潜艇，判断若都活着并且还撞上了：

     ​		潜艇去死、深水炸弹去死

     ​		判断若能得分，则强转为得分接口，玩家得分

     ​		判断若能得命，则强转为得命接口，获取命数，战舰增命

2. 画分和画命：

   - 在Battleship中设计getLife()获取命数
   - 在World类的paint()中：画分和画命---------不要求掌握

## 潜艇游戏第十天：

1. 雷与战舰的碰撞：

   - 在Battleship中设计subtractLife()减命

   - 雷与战舰的碰撞为定时发生的，所以在run中调用thunderBangAction()实现雷与战舰的碰撞

     在thunderBangAction()中:

     ​	遍历雷数组得到雷，判断与战舰若都活着并且撞上了：

     ​		雷去死、战舰减命

2. 检测游戏结束：

   - 借用Battleship中的getLife()获取命数

   - 检测游戏结束为定时发生的，所以在run中调用checkGameOverAction()检测游戏结束

     在checkGameOverAction()中：

     ​	判断若战舰的命数<=0，表示游戏结束了，则将state修改为GAME_OVER游戏结束状态

3. 画状态：

   - 在World中设计START、RUNNING、GAME_OVER状态常量，state变量表示当前状态

     在Images中设计start、gameover状态图片

     在World的paint()中：设计在不同状态下画不同的图片

   - 在按下空格键之后：

     - 启动状态时，变为运行状态
     - 运行状态时，发射深水炸弹
     - 游戏结束状态时，先清理现场，再变为启动状态 

   - 设计那一堆action为仅在运行状态时执行



## 回顾：

1. 多态：

   - 行为多态：所有抽象方法都是多态的

     对象多态：所有对象都是多态的

   - 向上造型：--------代码复用

     - 超类型的引用指向派生类的对象
     - 能点出来什么，看引用的类型
     - 能造型成为的数据类型有：超类+所实现的接口

   - 强制类型转换，成功的条件有如下两种：

     - 引用所指向的对象，就是该类型
     - 引用所指向的对象，实现了该接口或继承了该类

   - 强转时若不符合如上条件，则发生ClassCastException类型转换异常

     建议：在强转之前先通过instanceof判断引用指向的对象是否是该类型



## 笔记：

1. 内存管理：由JVM来管理

   - 堆：

     - 存储new出来的对象(包括实例变量)

     - 垃圾：没有任何引用所指向的对象

       垃圾回收器(GC)不定时到内存堆中清扫垃圾，回收过程中透明的(看不到的)，不一定一发现垃圾就立刻回收，通过调用System.gc()可以建议JVM尽快调度GC来回收

     - 实例变量的生命周期：

       ​	创建对象时存储在堆中，对象被回收时一并被回收

     - 内存泄漏：不再使用的对象还没有被及时的回收，严重的泄漏会导致系统的崩溃

       建议：不再使用的对象应及时将引用设置为null

   - 栈：

     - 存储正在调用的方法中的局部变量(包括方法的参数)

     - 调用方法时，会在栈中为该方法分配一块对应的栈帧，栈帧中存储局部变量(包括方法的参数)，方法调用结束时，栈帧被自动清除，局部变量一并被清除

     - 局部变量的生命周期：

       ​	调用方法时存储在栈中，方法调用结束时与栈帧一并被清除

   - 方法区：

     - 存储.class字节码文件(包括静态变量、所有方法)
     - 方法只有一份，通过this来区分具体的调用对象



面向对象三大特征：

1. 封装：

   - 类：封装的是对象的属性和行为
   - 方法：封装的是具体的业务逻辑功能实现
   - 访问控制修饰符：封装的是具体的访问权限

2. 继承：

   - 作用：代码复用

   - 超类：所有派生类所共有的属性和行为

     接口：部分派生类所共有的属性和行为

     派生类：派生类所特有的属性和行为

   - 单一继承、多接口实现，具有传递性

3. 多态：

   - 所有抽象方法都是多态的(通过方法的重写来体现)

     所有对象都是多态的(通过向上造型来体现)

   - 向上造型、强制类型转换、instanceof判断



练习：--------------------------在昨天晚课的基础之上最少重写两遍

1. 雷与战舰的碰撞
2. 检测游戏结束
3. 画状态



补充：

1. get/set

   ```java
   //get/set的演示
   public class GetSetDemo {
       public static void main(String[] args) {
           Student zs = new Student();
           zs.setName("zhangsan");
           zs.setAge(25);
           System.out.println(zs.getName());
           System.out.println(zs.getAge());
       }
   }
   
   class Student{
       private String name;
       private int age;
       public String getName(){ //get获取值
           return this.name;
       }
       public void setName(String name){ //set设置值
           this.name = name; //zs.name="zhangsan"
       }
       public int getAge(){
           return this.age;
       }
       public void setAge(int age){
           this.age = age; //zs.age=25
       }
   }
   ```

   



















