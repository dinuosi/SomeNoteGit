```java
1.成员内部类: 应用率低，了解
  1)类中套类，外面的称为外部类，里面的称为内部类
  2)内部类通常只服务于外部类，对外不具备可见性
  3)内部类对象通常是在外部类中创建的
  4)内部类中可以直接访问外部类的成员(包括私有的)
    内部类中有个隐式的引用指向了创建它的外部类对象  语法: 外部类名.this
2.匿名内部类:--------大大的简化代码
  1)若想创建一个类(派生类)的对象，并且对象只创建一次，
    此时该类不必命名，称之为匿名内部类
  2)匿名内部类中不能修改外面的变量，因为此处该变量默认是final的
  3)每一个类都会有独立的.class字节码文件

案例:
1.潜艇入场：
  1)潜艇对象是由窗口产生的，所以在World类中设计nextSubmarine()生成潜艇对象
  2)潜艇入场为定时发生的，所以在run()中调用submarineEnterAction()实现潜艇入场
    在submarineEnterAction()中：
      每400毫秒，获取潜艇对象obj，submarines扩容，将obj添加到最后一个元素上
      注意:在run中调用submarineEnterAction()方法后，需调用repaint()方法重画
2.水雷入场：
  1)水雷是由水雷潜艇产生的，所以在MineSubmarine中设计shootMine()生成水雷对象
  2)水雷入场为定时发生的，所以在run()中调用mineEnterAction()实现水雷入场
    在mineEnterAction()中：
      每1000毫秒，......
3.海洋对象移动：
  1)对象移动为共有的行为，所以在超类SeaObject中设计抽象move()实现移动，派生类重写move()移动
  2)对象移动为定时发生的，所以在run()中调用moveAction()实现对象移动
    在moveAction()中：
      遍历潜艇数组潜艇动，遍历雷数组雷动，遍历深水炸弹数组深水炸弹动
1.类和对象、方法的重载
2.构造方法、this、引用类型数组(上)
3.引用类型数组(下)、继承、super、向上造型
4.方法的重写、重写与重载的区别、package、import、
  访问控制修饰符(public、protected、默认的、private)、static
5.final、static final常量、abstract抽象方法、abstract抽象类
6.成员内部类、匿名内部类

对象的属性/特定----实例变量
类的属性/特定------静态变量
超类中的方法不包含业务，需要被派生类重写，设计为抽象方法
abstract class Student{
  int b; //实例变量
  final int a = 5; //不可变变量
  static int c; //静态变量
  void show(){} //实例方法
  final void test(){} //不能被重写的方法
  static void say(){} //静态方法
  abstract void sayHi(); //抽象方法
}

内存:
1)堆:new出来的对象(包括实例变量)
2)栈:局部变量(包括方法的参数)
3)方法区:.class字节码文件(包括静态变量、所有方法)

Person p1 = new Person();
Student s1 = new Student();
Person p1 = new Student(); //向上造型
//Student s2 = new Person(); //编译错误
```