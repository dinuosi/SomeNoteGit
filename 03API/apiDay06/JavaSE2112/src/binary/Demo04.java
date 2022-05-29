package binary;

public class Demo04 {
    public static void main(String[] args) {
        /*
         * 负数的编码
         * 实验： 输出-200到0的2进制编码（补码）
         *    随机选取20个数，手动计算10进制值
         *    利用Java程序验算计算结果。
         */
        for(int i=-200; i<0; i++){
            System.out.println(Integer.toBinaryString(i));
        }
    }
}
