# 面向对象第九天：

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



```java
//检测碰撞
public boolean isHit(SeaObject other){
    this:一个对象
    other:另一个对象
}

假设: s表示潜艇  b表示深水炸弹  t表示雷  ship表示战舰
调用: s.isHit(b);    //-----this指潜艇，other指深水炸弹
     b.isHit(s);    //-----this指深水炸弹，other指潜艇
     t.isHit(ship); //-----this指雷，other指战舰
     ship.isHit(t); //-----this指战舰，other指雷
```

![碰撞检测图](C:\Users\Administrator\Desktop\ooDay09\碰撞检测图.png)

如下代码可以体会出接口的好处：

```java
int score = 0; //玩家的得分
public void bombBangAction(){ //每10毫秒走一次
    for(int i=0;i<bombs.length;i++){ //遍历所有深水炸弹
        Bomb b = bombs[i]; //获取每个深水炸弹
        for(int j=0;j<submarines.length;j++){ //遍历所有潜艇
            SeaObject s = submarines[j]; //获取每个潜艇
            if(b.isLive() && s.isLive() && s.isHit(b)){ //若活着并且还撞上了
                s.goDead(); //潜艇去死
                b.goDead(); //深水炸弹去死

//--------------复用性好、扩展性好、维护性好--------------高质量代码
//假设被撞对象s是ObserveSubmarine-------调用的是ObserveSubmarine的getScore()---10
//假设被撞对象s是TorpedoSubmarine-------调用的是TorpedoSubmarine的getScore()---40
//假设被撞对象s是HeSubmarine------------调用的是HeSubmarine的getScore()--------80
if(s instanceof EnemyScore){ //-------适用于所有实现EnemyScore接口的---代码复用
    EnemyScore es = (EnemyScore)s;
    score += es.getScore();
}
//假设被撞对象s是MineSubmarine----------调用的是MineSubmarine的getLife()-------1
//假设被撞对象s是HeSubmarine------------调用的是HeSubmarine的getLife()---------3
if(s instanceof EnemyLife){ //--------适用于所有实现EnemyLife接口的----代码复用
    EnemyLife el = (EnemyLife)s;
    int num = el.getLife();
    ship.addLife(num);
}
   
                //----------------复用性差、扩展性差、维护性差-------垃圾代码
               	if(s instanceof ObserveSubmarine){ //-----只能适用于侦察潜艇类型
                    ObserveSubmarine os = (ObserveSubmarine)s;
                    score += os.getScore();
                }
                if(s instanceof TorpedoSubmarine){ //-----只能适用于鱼雷潜艇类型
                    TorpedoSubmarine ts = (TorpedoSubmarine)s;
                    score += ts.getScore();
                }
                if(s instanceof MineSubmarine){ //--------只能适用于水雷潜艇类型
                    MineSubmarine ms = (MineSubmarine)s;
                    int num = ms.getLife();
                 	ship.addLife(num);   
                }
                if(s instanceof HeSubmarine){ //----------只能适用于核潜艇类型
                    HeSubmarine hs = (HeSubmarine)s;
                    score += hs.getScore();
                    int num = hs.getLife();
                    ship.addLife(num);
                }
            }
        }
    }
}
```



## 回顾：

1. 接口：

   ​	引用数据类型，由interface定义，只能包含常量和抽象方法，不能被实例化，

   ​	接口是需要被实现的，实现类：必须重写所有抽象方法

   ​	一个类可以实现多个接口，用逗号分隔，若又继承又实现时，应先继承后实现

   ​	接口可以继承接口



## 笔记：

1. 多态：

   - 意义：

     - 同一类型的引用在指向不同的对象时，有不同的实现-----所有抽象方法都是多态的

       ----行为的多态：cut()、move()、getImage()......

     - 同一个对象被造型为不同的类型时，有不同的功能--------所有对象都是多态的(明天详细讲)

       ----对象的多态：我、你、水......

   - 向上造型/自动类型转换：-------------------------好处：代码复用

     - 超类型的引用指向派生类的对象
     - 能点出来什么，看引用的类型
     - 能造型成为的数据类型有：超类+所实现的接口

   - 强制类型转换，成功的条件只有如下两种：

     - 引用所指向的对象，就是该类型
     - 引用所指向的对象，实现了该接口或继承了该类

   - 强转时若不符合如上条件，则发生ClassCastException类型转换异常

     规则：在强转之前先通过instanceof来判断引用的对象是否是该类型

   ```java
   //多态的演示
   public class Test {
       public static void main(String[] args) {
           Aoo o = new Boo(); //向上造型
           Boo o1 = (Boo)o;     //引用o所指向的对象，就是Boo类型
           Inter o2 = (Inter)o; //引用o所指向的对象，实现了Inter接口
           //Coo o3 = (Coo)o; //运行时会发生ClassCastException类型转换异常
           if(o instanceof Coo){ //false
               Coo o4 = (Coo)o;
           }else{
               System.out.println("o不是Coo类型");
           }
       }
   }
   
   interface Inter{
   }
   class Aoo{
   }
   class Boo extends Aoo implements Inter{
   }
   class Coo extends Aoo{
   }
   ```

   

练习：---------在昨天晚课基础之上最少重做两次

1. 深水炸弹与潜艇的碰撞
2. 画分和画命



补充：

1. 接口可以继承多个接口：

   ```java
   interface Inter1{
   }
   interface Inter2{
   }
   interface Inter3 extends Inter1,Inter2{
   }
   ```

2. 异常：

   ```java
   1)ArrayIndexOutOfBoundsException数组下标越界异常
     -----下标为0到数组长度-1，超范围则发生如上异常
   2)NullPointerException空指针异常
     -----当引用的值为null时，若做点操作则发生如上异常
   3)ClassCastException类型转换异常
     -----强转时若不符合强转条件，则发生如上异常，建议先instanceof判断再强转
   ```

   







