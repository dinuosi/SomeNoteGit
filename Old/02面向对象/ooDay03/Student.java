package ooday03;
//学生类
public class Student {
    String name;
    int age;
    String address;
    //构造方法
    Student(String name, int age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }

    void study(){
        System.out.println(name+"在学习...");
    }
    void sayHi(){
        System.out.println(name+","+age+","+address);
    }
}
















