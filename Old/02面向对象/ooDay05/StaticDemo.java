package ooday05;
//static的演示
public class StaticDemo {
    public static void main(String[] args) {
        Loo o1 = new Loo();
        o1.show();
        Loo o2 = new Loo();
        o2.show();
        Loo o3 = new Loo();
        o3.show();
        System.out.println(Loo.b); //常常通过类名点来访问

        Noo.test(3,6); //常常通过类名点来访问

        Poo o4 = new Poo();
        Poo o5 = new Poo();
        Poo o6 = new Poo();
    }
}

//演示静态块
class Poo{
    static{
        System.out.println("静态块");
    }
    Poo(){
        System.out.println("构造方法");
    }
}













//演示静态方法
class Noo{
    int a;
    //因为需要操作对象的数据a，意味着与对象有关，所以不能设计为静态方法
    void show(){
        System.out.println(a);
    }
    //因为不需要操作对象的数据和行为，意味着与对象无关，所以适合设计为静态方法
    static void test(int num1,int num2){
        int num = num1+num2;
        System.out.println(num);
    }
}

//演示静态方法
class Moo{
    int a; //实例变量(对象点来访问)
    static int b; //静态变量(类名点来访问)

    void show(){ //有隐式this
        System.out.println(this.a);
        System.out.println(Moo.b);
    }
    static void test(){ //没有隐式this
        //静态方法中没有隐式的this传递
        //没有this就意味着没有对象
        //而实例变量a必须通过对象点来访问
        //所以如下语句发生编译错误
        //System.out.println(a); //编译错误
        System.out.println(Moo.b);
    }
}


















//演示静态变量
class Loo{
    int a;
    static int b;
    Loo(){
        a++;
        b++;
    }
    void show(){
        System.out.println("a="+a+"，b="+b);
    }
}













