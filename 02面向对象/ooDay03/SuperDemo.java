package ooday03;
//super的演示
public class SuperDemo {
    public static void main(String[] args) {
        Boo o = new Boo();
    }
}
class Coo{
    Coo(int a){
    }
}
class Doo extends Coo{
    Doo(){
        super(5); //调用超类的有参构造
    }
    /*
    //如下代码为默认的:
    Doo(){
        super();
    }
     */
}












class Aoo{
    Aoo(){
        System.out.println("超类构造");
    }
}
class Boo extends Aoo{
    Boo(){
        //super(); //默认的，调用超类的无参构造
        System.out.println("派生类构造");
    }
}
















