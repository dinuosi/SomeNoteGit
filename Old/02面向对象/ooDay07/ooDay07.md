# 面向对象第七天：

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

     ​	

     

## 回顾：

1. static final常量：

   ​	必须声明同时初始化，类名点来访问，不能被改变，大写

   ​	编译器在编译时会将常量直接替换为具体的数，效率高

   ​	数据永远不变，并且经常使用

2. 抽象方法：

   ​	abstract修饰，只有方法的定义，没有具体的实现(连{}都没有)

3. 抽象类：

   ​	abstract修饰，包含抽象方法的类必须是抽象类，不能被实例化

   ​	需要被继承的，派生类：重写所有抽象方法(变不完整为完整)

   ​	封装共有的属性和行为，为派生类提供统一的类型，

   ​    可以包含抽象方法，为所有派生类提供统一的入口(能点出来)

   ​    派生类的具体行为不同，但入口是一致的，强制必须重写的目的



## 笔记：

1. 成员内部类：了解即可

   - 类中套类，外面的称为外部类，里面的称为内部类

   - 内部类通常只服务于外部类，对外不具备可见性

   - 内部类对象通常在外部类中创建

   - 内部类中可以直接访问外部类的成员(包括私有的)

     内部类中有个隐式的引用指向了创建它的外部类对象---外部类名.this

   ```java
   public class InnerClassDemo {
       public static void main(String[] args) {
           Mama m = new Mama();
           //Baby b = new Baby(); //编译错误，内部类对外不具备可见性
       }
   }
   
   class Mama{ //外部类
       private String name;
       void create(){
           Baby b = new Baby(); //正确
       }
       class Baby{ //内部类
           void show(){
               System.out.println(name);
               System.out.println(Mama.this.name); //外部类名.this
               //System.out.println(this.name); //编译错误，this表示当前对象，当前Baby中没有name属性
           }
       }
   }
   ```

2. 匿名内部类：---------------------大大简化代码

   - 若想创建一个类(派生类)的对象，并且对象只创建一次，此时该类不必命名，称为匿名内部类
   - 匿名内部类中不能修改外面变量的值，因为变量在此处默认为final的
   - 只要是类，都会有自己独立的.class字节码文件
   
   ```java
   //匿名内部类的演示
   public class NstInnerClassDemo {
       public static void main(String[] args) {
           //1)创建了Aoo的一个派生类，但是没有名字
           //2)为该派生类创建了一个对象，名为o1
           //3)大括号中的为派生类的类体
           Aoo o1 = new Aoo(){
   
           };
   
           //1)创建了Aoo的一个派生类，但是没有名字
           //2)为该派生类创建了一个对象，名为o2
           //3)大括号中的为派生类的类体
           Aoo o2 = new Aoo(){
   
           };
   
           int num = 5;
           num = 55;
           //1)创建了Boo的一个派生类，但是没有名字
           //2)为该派生类创建了一个对象，名为o3
           //3)大括号中的为派生类的类体
           Boo o3 = new Boo(){
               void show(){
                   System.out.println("showshow");
                   //num = 55; //编译错误，在此处默认num为final的
               }
           };
           o3.show();
       }
   }
   
   abstract class Boo{
       abstract void show();
   }
   
   abstract class Aoo{
   }
   ```





补充：

1. 做功能的套路：

   - 先写行为/方法：
     - 若为对象所特有的行为，就设计在特定的类中
     - 若为所有对象所共有的行为，就设计在超类中
   - 窗口调用：
     - 若为定时发生的，在定时器中调用
     - 若为事件触发的，在侦听器中调用----------------明天上午讲
   
2. this：指代当前对象

   super：指代当前对象的超类对象

   外部类名.this：指代当前对象的外部类对象
   
3. SeaObject说是所有类的超类，但其实后期用起来是把它当作潜艇来用的



练习：------------------------在昨天晚课的基础之上最少做两遍

1. 潜艇入场 
2. 雷入场
3. 海洋对象移动



















