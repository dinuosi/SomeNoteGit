package binary;

public class Demo05 {
    public static void main(String[] args) {
        /*
         * 验证补码的互补对称现象 -n=~n+1
         */
        System.out.println(54);
        System.out.println(Integer.toBinaryString(54));
        System.out.println(~54);
        System.out.println(Integer.toBinaryString(~54));
        System.out.println(~54+1);
        System.out.println(Integer.toBinaryString(~54+1));
    }
}
