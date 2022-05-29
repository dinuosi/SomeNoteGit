package binary;

public class Demo01 {
    public static void main(String[] args) {
        /*
         * 如何查看整数的2进制存储情况
         * - java 编译时候，将数字编译为2进制数字
         * - 运行期间变量中存储的是2进制数
         * - 输出变量时候，Java利用API方法，将2进制转换为10进制字符串
         *   利用valueOf方法转换！
         * - Integer.toBinaryString(n) 将整数n在内存中2进制情况显示出来
         */
        int n = 50; //n=110010
        System.out.println(n); //利用valueOf转换2进制为10进制字符串输出
        System.out.println(Integer.toBinaryString(n));

        System.out.println(Integer.toBinaryString(104));
        /*
         * 输出0~200的2进制， 手工计算20个数的10进制值，编程验证
         */
        for(int i=0; i<200; i++){
            System.out.println(Integer.toBinaryString(i));
        }
    }
}
