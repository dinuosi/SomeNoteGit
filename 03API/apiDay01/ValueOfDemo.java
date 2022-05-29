package apiday01;

/**
 * static String valueOf(数据类型 a):
 * 将其它数据类型转换为String
 */
public class ValueOfDemo {
    public static void main(String[] args) {
        int a = 123;
        String s1 = String.valueOf(a); //将int型变量a转换为String类型并赋值给s1
        System.out.println("s1:"+s1); //123

        double dou = 123.456;
        String s2 = String.valueOf(dou); //将double型变量dou转换为String类型并赋值给s2
        System.out.println("s2:"+s2); //123.456

        String s3 = a + ""; //任何内容与字符串连接结果都是字符串，效率低(下周一才能体会)
        System.out.println(s3); //123
    }
}


















