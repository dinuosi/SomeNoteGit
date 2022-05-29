package day02;
//数据类型的演示
public class DataTypeDemo {
    public static void main(String[] args) {
        //类型间的转换:
        byte b1 = 5; //byte的范围为-128到127之间
        byte b2 = 6;
        byte b3 = (byte)(b1+b2);

        System.out.println(2+2);     //4
        System.out.println(2+'2');   //52，2加上'2'的码50
        System.out.println('2'+'2'); //100，'2'的码50，加上'2'的码50







        /*
        int a = 5;
        long b = a; //自动类型转换
        int c = (int)b; //强制类型转换

        long d = 5;   //自动类型转换
        double e = 5; //自动类型转换

        long f = 10000000000L;
        int g = (int)f; //强制类型转换
        System.out.println(g); //1410065408，强转有可能发生溢出
        double h = 25.987;
        int i = (int)h; //强制类型转换
        System.out.println(i); //25，强转有可能丢失精度
        */




        /*
        //5)char:字符型，2个字节
        char c1 = '女'; //字符女
        char c2 = 'f';  //字符f
        char c3 = '6';  //字符6
        char c4 = ' ';  //空格符
        //char c5 = 女; //编译错误，字符型直接量必须放在单引号中
        //char c6 = ''; //编译错误，必须有字符
        //char c7 = '女性'; //编译错误，只能有1个字符

        char c8 = 65; //0到65535之间
        System.out.println(c8); //println输出时会依据c8的数据类型显示
                                //若c8为char型，则显示字符
                                //若c8为int型，则显示数字
        char c9 = '\\';
        System.out.println(c9); //\
        */

















        /*
        //4)boolean:布尔型，1个字节
        boolean a = true;  //true为布尔型直接量-----真
        boolean b = false; //false为布尔型直接量----假
        //boolean c = 250; //编译错误，布尔型只能赋值为true或false
         */
















        /*
        //3)double:浮点型，8个字节，很大很大很大
        double a = 3.14; //3.14为浮点数直接量，默认double型
        float b = 3.14F; //3.14F为float型直接量
        double c = 1234000000000000000000000000000000.0;
        System.out.println(c); //1.234E33，科学计数法表示，相当于1.234*(10的33次幂)

        double d=3.0,e=2.9;
        System.out.println(d-e); //0.10000000000000009，有可能会发生舍入误差，精确场合不能使用
         */












        /*
        //2)long:长整型，8个字节，很大很大很大
        long a = 25L; //25L为长整型直接量
        //long b = 10000000000; //编译错误，100亿默认为int类型，但超出int范围了
        long c = 10000000000L; //100L为长整型直接量
        //long d = 3.14; //编译错误，长整型变量只能装整数

        //运算时若有可能溢出，建议在第1个数字后加L
        long e = 1000000000*2*10L;
        System.out.println(e); //200亿
        long f = 1000000000*3*10L;
        System.out.println(f); //不是300亿
        long g = 1000000000L*3*10;
        System.out.println(g); //300亿

         */









        /*
        //1)int:整型，4个字节，-21个多亿到21个多亿
        int a = 25; //25称为整数直接量，默认int类型
        //int b = 10000000000; //编译错误，100亿默认为int类型，但超出int范围了
        //int c = 3.14; //编译错误，整型变量中只能装整数
        System.out.println(5/2); //2
        System.out.println(2/5); //0
        System.out.println(5/2.0); //2.5
        int d = 2147483647; //int的最大值
        d = d+1;
        System.out.println(d); //-2147483648(int最小值)，发生溢出了，溢出是需要避免的
         */

    }
}

















