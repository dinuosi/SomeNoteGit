package string;

public class RegDemo06 {
    public static void main(String[] args) {
        /*
         * 测试正则表达式
         */
        //声明正则表达式
        String reg = "Hello[123456]";
        //被测试的字符串
        String s1 = "Hello1";
        String s2 = "Hello7";
        //测试
        System.out.println(s1.matches(reg));
        System.out.println(s2.matches(reg));
    }
}
