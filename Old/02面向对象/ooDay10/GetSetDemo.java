package ooday10;
//get/set����ʾ
public class GetSetDemo {
    public static void main(String[] args) {
        Student zs = new Student();
        zs.setName("zhangsan");
        zs.setAge(25);
        System.out.println(zs.getName());
        System.out.println(zs.getAge());
    }
}

class Student{
    private String name;
    private int age;
    public String getName(){ //get��ȡֵ
        return this.name;
    }
    public void setName(String name){ //set����ֵ
        this.name = name; //zs.name="zhangsan"
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age; //zs.age=25
    }

}














