package day03;
//运算符的演示
public class OperDemo {
    public static void main(String[] args) {
        /*
          6.条件运算符:
            1)语法:
               boolean?数1:数2
            2)执行过程:
              整个表达式是有值的，它的值要么是?号后的数1，要么是:号后的数2
              计算boolean的值：
                若为true，则整个表达式的值为?号后的数1
                若为false，则整个表达式的值为:号后的数2
         */
        /*
        int num = 5;
        int flag = num>0?1:-1;
        System.out.println(flag); //1

        int a=8,b=55;
        int max = a>b?a:b;
        System.out.println("max="+max);
        */




        /*
          5.字符串连接运算符:
            1)+:
              1.1)若两边为数字，则做加法运算
              1.2)若两边出现了字符串，则做字符串连接
              1.3)任何类型与字符串相连，结果都会变为字符串类型----同化作用
         */
        /*
        System.out.println(10+20+30+""); //60---------String
        System.out.println(10+20+""+30); //3030-------String
        System.out.println(""+10+20+30); //102030-----String
        */
        /*
        int age = 38;
        System.out.println("age="); //age=
        System.out.println(age);    //38
        System.out.println("age="+age); //age=38
        System.out.println("我的年龄是"+age); //我的年龄是38
        System.out.println("我今年"+age+"岁了"); //我今年38岁了

        String name = "WKJ";
        System.out.println("name="+name); //name=WKJ
        System.out.println("大家好，我叫"+name); //大家好，我叫WKJ
        System.out.println("大家好，我叫"+name+"，今年"+age+"岁了"); //大家好，我叫WKJ，今年38岁了
        */









        /*
          4.赋值运算符:
            1)简单赋值运算符:=
            2)扩展赋值运算符:+=,-=,*=,/=,%=
              ----扩展赋值自带强转功能
         */
        /*
        int a = 5;
        a += 10; //相当于a=(int)(a+10)
        System.out.println(a); //15
        a *= 2; //相当于a=(int)(a*2)
        System.out.println(a); //30
        a /= 6; //相当于a=(int)(a/6)
        System.out.println(a); //5

        //小面试题:
        short s = 5;
        //s = s+10; //编译错误，需强转: s=(short)(s+10);
        s += 10; //相当于s=(short)(s+10)

         */














        /*
          3.逻辑运算符:
            1)&&:短路与(并且)，两边都为真则为真，见false则false
                 ---当第1个条件为false时，发生短路(后面的不执行了)
              ||:短路或(或者)，有真则为真，见true则true
                 ---当第1个条件为true时，发生短路(后面的不执行了)
               !:逻辑非(取反)，非真则假，非假则真
            2)逻辑运算是建立在关系运算的基础之上的，
              逻辑运算的结果也是boolean型
         */

        /*
        int a=5,b=10,c=5;
        boolean b3 = a>b && c++>2;
        System.out.println(b3); //false
        System.out.println(c);  //5，发生短路了

        boolean b4 = a<b || c++>2;
        System.out.println(b4); //true
        System.out.println(c);  //5，发生短路了
        */














        /*
        boolean b1 = b>=a && b<c;
        System.out.println(b1);          //true&&false=false
        System.out.println(b<=c && b>a); //false&&true=false
        System.out.println(a==b && c>b); //false&&false=false
        System.out.println(b!=c && a<b); //true&&true=true
        int age = 25;
        System.out.println(age>=18 && age<=50); //看age是否在18到50之间
        */
        /*
        System.out.println(b>=a || b<c); //true||false=true
        System.out.println(b<=c || b>a); //false||true=true
        System.out.println(b!=c || a<b); //true||true=true
        System.out.println(a==b || c>b); //false||false=false
        int score = 89;
        System.out.println(score<0 || score>100); //看score是否不合法

        boolean b2 = !(a<b);
        System.out.println(b2);     //!true=false
        System.out.println(!(a>b)); //!false=true
        */


















        /*
          2.关系运算符:
            1)>(大于)，<(小于)
              >=(大于或等于)，<=(小于或等于)
              ==(等于)，!=(不等于)
            2)关系运算的结果为boolean型，
              关系成立则为true，关系不成立则为false
         */
        /*
        int a=5,b=10,c=5;
        boolean b1 = a>b;
        System.out.println(b1);   //false
        System.out.println(c<b);  //true
        System.out.println(a>=c); //true
        System.out.println(a<=b); //true
        System.out.println(a==c); //true
        System.out.println(a!=c); //false

        System.out.println(a+c>b);  //false
        System.out.println(a%2==0); //false
        System.out.println(c++>5);  //false-------c自增1变为6
        System.out.println(c++>5);  //true--------c自增1变为7
        */


        /*
          1.算术运算符:+,-,*,/,%,++,--
            1)%:取模/取余，余数为0即为整除
            2)++/--:自增1/自减1，可在变量前也可在变量后
              2.1)单独使用时，在前在后都一样
              2.2)被使用时，在前在后不一样
                    a++的值为a--------(a--的值为a)
                    ++a的值为a+1------(--a的值为a-1)
         */
        /*
        int a=5,b=5;
        int c = a--; //a--的值为5，所以c的值为5
        int d = --b; //--b的值为4，所以d的值为4
        System.out.println(a); //4
        System.out.println(b); //4
        System.out.println(c); //5
        System.out.println(d); //4
        */
        /*
        int a=5,b=5;
        a--; //相当于a=a-1
        --b; //相当于b=b-1
        System.out.println(a); //4
        System.out.println(b); //4
        */














        /*
        int a=5,b=5;
        int c = a++; //1)保存a++的值5  2)a自增1变为6  3)将第1步保存的值5赋值给c--底层运算过程
                     //---粗暴记法:a++的值为5，c就是5
        int d = ++b; //1)保存++b的值6  2)b自增1变为6  3)将第1步保存的值6赋值给d--底层运算过程
                     //---粗暴记法:++b的值为6，d就是6
        System.out.println(a); //6
        System.out.println(b); //6
        System.out.println(c); //5
        System.out.println(d); //6
        */
        /*
        int a=5,b=5;
        a++; //相当于a=a+1
        ++b; //相当于b=b+1
        System.out.println(a); //6
        System.out.println(b); //6
        */






        /*
        System.out.println(8%2); //0，商4余0----整除
        System.out.println(5%2); //1，商2余1
        System.out.println(2%8); //2，商0余2
        */


    }
}
















