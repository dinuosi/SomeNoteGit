package ooday03;
//引用类型数组的演示
public class RefArrayDemo {
    public static void main(String[] args) {
        Student[] stus = new Student[3];     //创建Student数组对象
        stus[0] = new Student("zhangsan",25,"LF"); //创建Student对象(与基本类型数组第1点区别)
        stus[1] = new Student("lisi",26,"JMS");
        stus[2] = new Student("wangwu",24,"SD");
        System.out.println(stus[0].name); //输出第1个学生的名字(与基本类型数组第2点区别)
        stus[1].age = 28; //给第2个学生的年龄修改为28
        stus[2].sayHi(); //第3个学生跟大家问好

        for(int i=0;i<stus.length;i++){ //遍历Student数组
            System.out.println(stus[i].name); //输出每个学生的名字
            stus[i].sayHi(); //每个学生跟大家问好
        }
    }
}



















