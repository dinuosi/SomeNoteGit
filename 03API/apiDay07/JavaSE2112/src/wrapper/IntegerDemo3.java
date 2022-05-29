package wrapper;

public class IntegerDemo3 {
    public static void main(String[] args) {
        int a = 123;
        //触发了编译器的自动装箱特性，编译器会补充代码将基本类型转换为包装类
        Integer i = a;
//        Integer i = Integer.valueOf(a);//编译器改好的样子

        //触发了自动拆箱特性，编译器会补充代码将包装类转换为基本类型
        a = i;
//        a = i.intValue();//编译器改好的样子

    }

}
