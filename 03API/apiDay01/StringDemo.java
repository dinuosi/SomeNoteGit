package apiday01;

/**
 * String的演示
 */
public class StringDemo {
    public static void main(String[] args) {
        //如下代码:常量池中会有2个存储，一个是123abc的地址，一个是123的地址
        //        一个新的对象，它的值也是123abc
        String s1 = "123abc";
        //编译器在编译时，若发现一个计算表达式可以在编译期间确定结果，
        //则直接运算好并将结果保存到表达式中 相当于String s2 = "123abc";
        String s2 = "123"+"abc";
        System.out.println(s1==s2); //true，s1与s2共用常量池中的

        String s3 = "123";
        //当字符串拼接产生的内容与常量池是某内容相同时，也不会重用常量池的对象
        String s4 = s3+"abc"; //创建一个新的对象存储123abc
        System.out.println(s4==s1); //false









        /*
          使用字面量创建字符串时:
          1.JVM会检查常量池中是否有该对象:
            1)若没有，则创建该字符串对象并存入常量池
            2)若有，则直接将该对象返回而不再创建一个新的字符串对象
         */

        /*
        String s1 = "123abc"; //常量池还没有，因此创建该字符串对象，并存入常量池
        String s2 = "123abc"; //常量池已有了，直接重用对象
        String s3 = "123abc"; //常量池已有了，直接重用对象
        //引用类型==，比较地址是否相同
        System.out.println(s1==s2); //true
        System.out.println(s1==s3); //true
        System.out.println(s2==s3); //true
        */




        /*
          常见面试题:
            String s = new String("hello");
            问:创建了几个对象?
            答:2个
              第一个:字面量"hello"
                    ---java会创建一个String对象表示字面量"hello"，并将其存入常量池
              第二个:new String()
                    ---new String()时会再创建一个字符串，并引用hello字符串的内容
         */
        /*
        String s1 = new String("hello"); //s1装的是new String()对象的地址
        String s2 = "hello"; //s2装的是字面量"hello"的地址
        System.out.println("s1:"+s1); //hello
        System.out.println("s2:"+s2); //hello
        System.out.println(s1==s2); //false，因为s1与s2的地址不同

        //字符串实际开发中比较相等的需求都是比较字符串的内容
        //因此我们应该使用字符串提供的equals()方法来比较两个字符串的内容
        System.out.println(s1.equals(s2)); //true，因为s1与s2的内容相同
        */


        /*
        String s1 = "123abc";
        String s2 = "123abc";
        System.out.println(s1==s2); //true，s1与s2地址相同

        s1 = s1+"!"; //创建新对象并把地址赋值给s1
        System.out.println(s1==s2); //false，s1为新的对象的地址，与s2不同了
        */


















    }
}












