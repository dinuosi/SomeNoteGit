package ooday05;
//final的演示
public class FinalDemo {
    public static void main(String[] args) {

    }
}

//演示final修饰类
final class Doo{}
//class Eoo extends Doo{} //编译错误，final的类不能被继承
class Foo{}
final class Goo extends Foo{} //不能当老爸，但能当儿子

//演示final修饰方法
class Boo{
    final void show(){}
}
class Coo extends Boo{
    //void show(){} //编译错误，final修饰的方法不能被重写
}

//演示final修饰变量
class Aoo{
    final int num = 5;
    void show(){
        //num = 55; //编译错误，final的变量不能被改变
    }
}






















