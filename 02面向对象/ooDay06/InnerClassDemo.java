package ooday06;
//成员内部类的演示
public class InnerClassDemo {
    public static void main(String[] args) {
        Mama m = new Mama();
        //Baby b = new Baby(); //编译错误，内部类对外不具备可见性
    }
}

class Mama{ //外部类
    private String name;
    Baby b = new Baby(); //内部类对象通常在外部类中创建
    class Baby{ //内部类
        void show(){
            System.out.println(name);
            System.out.println(Mama.this.name); //Mama.this指代它的外部类对象
        }
    }
}

























