package wrapper;

/**
 * 包装类出现的原因是为了解决基本类型不能直接参与面向对象开发的情况。使基本类型可以以"对象"的
 * 形式存在。
 */
public class IntegerDemo {
    public static void main(String[] args) {
        //基本类型转换为包装类
//        int i = 127;
        int i = 128;
//        Integer i1 = new Integer(i);
//        Integer i2 = new Integer(i);
        Integer i1 = Integer.valueOf(i);
        Integer i2 = Integer.valueOf(i);
        System.out.println(i1 == i2);//new则是false,valueOf在一字节之内是true
        System.out.println(i1.equals(i2));//true

        int d = i1.intValue();
        System.out.println(d);//127
        double dou = i1.doubleValue();
        System.out.println(dou);//127.0
        byte b = i1.byteValue();
        System.out.println(b);//127

    }
}









