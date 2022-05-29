package binary;

public class Demo03 {
    public static void main(String[] args) {
        /*
         * 补码
         * max 最大
         * value 值
         * Integer 整数
         */
        int n = -7;
        System.out.println(Integer.toBinaryString(n));
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        System.out.println(max);
        System.out.println(min);
        System.out.println(Integer.toBinaryString(max));
        System.out.println(Integer.toBinaryString(min));
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Long.toBinaryString(-1L));
    }
}
