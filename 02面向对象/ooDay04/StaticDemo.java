package ooday04;
//static的演示
public class StaticDemo {
    public static void main(String[] args) {
        Eoo o1 = new Eoo();
        o1.show();
        Eoo o2 = new Eoo();
        o2.show();
        Eoo o3 = new Eoo();
        o3.show();
        System.out.println(Eoo.b); //常常通过类名点来访问

        Goo.plus(4,6);

        Hoo o4 = new Hoo();
        Hoo o5 = new Hoo();
        Hoo o6 = new Hoo();
    }
}

//演示静态块
class Hoo{
    static {
        System.out.println("静态块");
    }
    Hoo(){
        System.out.println("构造方法");
    }
}

//演示静态方法何时用
class Goo{
    int a; //对象的属性
    //方法中用到了对象的属性a，意味着show()的操作与对象是有关的，不能做成静态方法
    void show(){
        System.out.println(a);
    }
    //方法中没有用到对象的属性和行为，意味着plus()的操作与对象是无关的，可以做成静态方法
    static void plus(int num1,int num2){
        int num = num1+num2;
        System.out.println(num);
    }
}

//演示静态方法
class Foo{
    int a; //实例变量(由对象来访问)
    static int b; //静态变量(由类名来访问)

    void show(){ //有隐式this
        System.out.println(this.a);
        System.out.println(Foo.b);
    }
    static void test(){
        //静态方法中没有隐式this传递
        //没有this就意味着没有对象
        //而实例变量a是必须由对象来访问的
        //所以下面的语句发生编译错误
        //System.out.println(a); //编译错误

        System.out.println(Eoo.b);
    }
}











class Eoo{ //演示静态变量
    int a;
    static int b;
    Eoo(){
        a++;
        b++;
    }
    void show(){
        System.out.println("a="+a+",b="+b);
    }
}




















