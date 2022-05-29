package ooday08;
//多态的演示
public class MultiTypeDemo {
    public static void main(String[] args) {
        Aoo o = new Boo();
        Boo o1 = (Boo)o;     //引用o所指向的对象，就是Boo类型
        Inter o2 = (Inter)o; //引用o所指向的对象，实现了Inter接口
        //Coo o3 = (Coo)o; //运行时ClassCastException类型转换异常
        if(o instanceof Coo){ //false
            Coo o4 = (Coo)o;
        }else{
            System.out.println("o不是Coo类型");
        }
        /*
        System.out.println(o instanceof Boo);   //true
        System.out.println(o instanceof Inter); //true
        System.out.println(o instanceof Coo);   //false
         */
    }
}

interface Inter{ }
class Aoo{ }
class Boo extends Aoo implements Inter{ }
class Coo extends Aoo{ }

























