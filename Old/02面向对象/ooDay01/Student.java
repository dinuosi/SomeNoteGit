package ooday01;
//学生类
public class Student { //类是我们自己创造的一种引用类型
    //成员变量
    String name = null;
    int age = 0;
    String address = null;

    //方法
    void study(){
        System.out.println(name+"在学习...");
    }
    void sayHi(){
        System.out.println("大家好，我叫"+name+"，今年"+age+"岁了，家住"+address);
    }
}
















