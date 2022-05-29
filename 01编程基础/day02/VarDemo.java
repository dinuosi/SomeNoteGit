package day02;
//变量的演示
public class VarDemo {
    public static void main(String[] args) {
        //1)变量的声明:---相当于在银行开帐户
        int a; //声明一个整型的变量，名为a
        int b,c,d; //声明三个整型的变量，名为b,c,d
        //int a; //编译错误，变量不能同名

        //2)变量的初始化:---相当于给帐户存钱
        int e = 250; //声明整型变量e并赋值为250
        int f;   //声明整型变量f
        f = 250; //给变量f赋值为250
        f = 360; //修改变量f的值为360
        int g=5,h=10; //声明两个整型变量g和h，并分别赋值为5和10

        //3)变量的使用:----使用的是帐户里面的钱
        int i = 5;
        int j = i+10; //取出i的值5，加10后，再赋值给整型变量j
        System.out.println(j);   //输出变量j的值15
        System.out.println("j"); //输出j，双引号中的原样输出
        i = i+10; //在i本身基础之上增10
        System.out.println(i); //15
        int k = 5000; //帐户余额
        k = k-2000; //取款2000
        System.out.println(k); //输出3000

        //System.out.println(m); //编译错误，变量m未声明
        int m;
        //System.out.println(m); //编译错误，变量m未初始化

        //4)变量的命名:
        int a_5$,_3c,$_;
        //int a*b; //编译错误，不能包含*号等特殊符号
        //int 1a; //编译错误，不能以数字开头
        int aa = 5;
        //System.out.println(aA); //编译错误，严格区分大小写
        //int class; //编译错误，不能使用关键字

        int 年龄; //允许，但不建议
        int age; //建议"英文见名知意"
        int score,myScore,myJavaScore; //建议"小驼峰命名法"

    }
}














