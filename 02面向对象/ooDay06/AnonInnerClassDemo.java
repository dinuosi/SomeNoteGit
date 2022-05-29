package ooday06;
//匿名内部类的演示
public class AnonInnerClassDemo {
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
                //num = 66; //编译错误，匿名内部类中不能修饰外面变量的值，因为在此处默认为final的
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










