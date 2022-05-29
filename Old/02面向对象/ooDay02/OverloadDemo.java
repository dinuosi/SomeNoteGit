package ooday02;
//重载的演示
public class OverloadDemo {
    public static void main(String[] args) {
        Aoo o = new Aoo();
        o.show();
        o.show("zhangsan");
        o.show(25);
        o.show("zhangsan",25);
        o.show(25,"zhangsan");
    }
}

class Aoo{
    void show(){}
    void show(String name){}
    void show(int age){}
    void show(String name,int age){}
    void show(int age,String name){}
    //int show(){ return 1; } //编译错误，重载与返回值类型无关
    //void show(String address){} //编译错误，重载与参数名称无关
}



























