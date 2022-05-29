package binary;

public class Demo02 {
    public static void main(String[] args) {
        /*
         * 2进制与16进制
         * - Java7 提供了2进制字面量前缀 0b
         *   可以在数字中添加下划线，不影响数值
         * - 2进制直接书写非常繁琐
         * - 16进制缩写2进制就非常方便
         *   从2进制的最低位开始， 每4位数缩写为1位16进制数
         * - 0x是16进制的前缀
         * - 计算内部没有10进制，没有16进制，只有2进制！
         */
        int n = 0b11_0010;//32+16+2=50
        System.out.println(n);
        n = 0b0001_1001_1111_0001_0100_0011_1111_0101;
        //    1    9    f    1    4    3    f    5
        System.out.println(Integer.toBinaryString(n));
        n = 0x19f143f5;
        System.out.println(Integer.toBinaryString(n));
        long l = 0b101111111111111111111111111111111111111111L;
    }
}
