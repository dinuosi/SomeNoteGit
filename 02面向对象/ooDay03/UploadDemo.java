package ooday03;
//向上造型的演示
public class UploadDemo {
    public static void main(String[] args) {
        Eoo o1 = new Eoo();
        o1.a = 1;
        o1.show();
        //o1.b = 2;  //编译错误
        //o1.test(); //编译错误，超类不能访问派生类的

        Foo o2 = new Foo();
        o2.b = 1;
        o2.test();
        o2.a = 2;  //正确
        o2.show(); //正确，派生类可以访问超类的

        Eoo o3 = new Foo(); //向上造型
        o3.a = 1;
        o3.show();
        //o3.b = 2;  //编译错误
        //o3.test(); //编译错误，能点出来什么，看引用的类型
    }
}

class Eoo{
    int a;
    void show(){
    }
}
class Foo extends Eoo{
    int b;
    void test(){
    }
}




