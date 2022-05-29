package wrapper;

/**
 * 包装类常见的功能
 */
public class IntegerDemo2 {
    public static void main(String[] args) {
        //查看int最大值与最小值?
        /*
            Byte,Short,Integer,Long,Double,Float
         */
        int imax = Integer.MAX_VALUE;
        int imin = Integer.MIN_VALUE;
        System.out.println(imax);
        System.out.println(imin);


        /*
            包装类提供了一个静态方法，可以将字符串转换为对应的基本类型，前提是该字符串
            正确描述了基本类型可以保存的值，否则会抛出异常:
            数字  格式   异常
            NumberFormatException
         */
        String line = "123";//需要注意:"123.123"是不能转换为整数的
        int d = Integer.parseInt(line);
        System.out.println(d);//123
        double dou = Double.parseDouble(line);
        System.out.println(dou);//123.123
    }
}






