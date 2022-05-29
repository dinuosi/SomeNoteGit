package ooday04;
//向上造型的演示
public class UploadDemo {
    public static void main(String[] args) {
        Aoo o1 = new Aoo();
        o1.a = 1;
        o1.show();
        //o1.b = 2;  //编译错误，超类不能访问派生类的
        //o1.test(); //编译错误，超类不能访问派生类的

        Boo o2 = new Boo();
        o2.b = 1;
        o2.test();
        o2.a = 2;  //正确，派生类可以访问超类的
        o2.show(); //正确，派生类可以访问超类的

        Aoo o3 = new Boo(); //向上造型
        o3.a = 1;
        o3.show();
        //o3.b = 2;  //编译错误，能点出来什么，看引用的类型
        //o3.test(); //编译错误，能点出来什么，看引用的类型
    }
}

class Aoo{
    int a;
    void show(){
    }
}
class Boo extends Aoo{
    int b;
    void test(){
    }
}





















