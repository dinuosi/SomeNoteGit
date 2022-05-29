package ooday09;
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
        //练习-----------------10:54继续
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









