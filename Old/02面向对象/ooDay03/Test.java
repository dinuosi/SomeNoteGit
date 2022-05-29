package ooday04;
//测试类
public class Test {
    public static void main(String[] args) {
        Student[] stus = new Student[3];
        stus[0] = new Student("aaa",25,"LF","111");
        stus[1] = new Student("bbb",26,"JMS","222");
        stus[2] = new Student("ccc",24,"SX","333");
        for(int i=0;i<stus.length;i++){
            System.out.println(stus[i].name);
            stus[i].sayHi();
        }

        Teacher[] tes = new Teacher[3];
        tes[0] = new Teacher("ddd",35,"LF",10000);
        tes[1] = new Teacher("eee",46,"JMS",20000);
        tes[2] = new Teacher("fff",54,"SX",30000);
        for(int i=0;i<tes.length;i++){
            System.out.println(tes[i].name);
            tes[i].sayHi();
        }

        Doctor[] docs = new Doctor[2];
        docs[0] = new Doctor("ggg",35,"LF","主治医师");
        docs[1] = new Doctor("hhh",56,"JMS","主任医师");
        for(int i=0;i<docs.length;i++){
            System.out.println(docs[i].name);
            docs[i].sayHi();
        }
    }
}

class Person{
    String name;
    int age;
    String address;
    Person(String name,int age,String address){
        this.name = name;
        this.age = age;
        this.address = address;
    }
    void sayHi(){
        System.out.println("大家好，我叫"+name+"，今年"+age+"岁了，家住"+address);
    }
}
class Student extends Person{
    String stuId; //学号
    Student(String name,int age,String address,String stuId){
        super(name,age,address);
        this.stuId = stuId;
    }
}
class Teacher extends Person{
    double salary; //工资
    Teacher(String name,int age,String address,double salary){
        super(name,age,address);
        this.salary = salary;
    }
}
class Doctor extends Person{
    String level; //职称
    Doctor(String name,int age,String address,String level){
        super(name,age,address);
        this.level = level;
    }
}


















