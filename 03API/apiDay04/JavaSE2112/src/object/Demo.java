package object;

public class Demo {
    public static void main(String[] args) {
        /*
            第一个常被子类重新的Object中的方法是toString.
            重写它的原因通常是:
            1:输出对象信息更准确
            2:连接字符串的时候内容有意义
         */
        Point p = new Point(1,2);
        /*
            输出一个对象时，实际上输出的是该对象toString()方法返回的字符串。
            Object定义了toString方法。目的是将当前对象转换为字符串，而返回的字符串格式
            为:类名@地址
            在输出上来讲，这个字符串对开发帮助不大。因此要重写子类的toString方法。
         */
        System.out.println(p);
        //其他类型在与字符串连接时，会调用其toString转换为字符串后再进行连接
        String line = "这个对象是:" + p;
        System.out.println(line);

        String str = p.toString();
        System.out.println(str);

        /*
            注:equal 相等的意思
            另一个常被子类重写的方法equals.
            作用是判定两个对象的内容是否相同。
         */
        Point p2 = new Point(1,2);
        System.out.println(p == p2);//对于引用类型而言，就是比较是否为"同一个"对象
        /*
            子类不重写equals时，Object中的equals内部还是"=="比较，因此不重写无意义
         */
        System.out.println(p.equals(p2));

        /*
            java 提供的类大多都重写过了toString和equals方法。
            只有我们自己定义的类在使用时出现上述情况需要自行重写两个方法。
            例如:String，它的toString和equals方法已经重新过了。

         */
    }
}








