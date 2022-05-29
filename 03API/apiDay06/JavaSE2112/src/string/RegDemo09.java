package string;

public class RegDemo09 {
    public static void main(String[] args) {
        /*
         * 测试一下 检查 字符串是否符合规则：
         *   Hello\s+World
         */
        //定义正则表达式
        String reg = "Hello\\s+World";
        //定义被检查的字符串
        String s1 = "HelloWorld";//不能通过检查
        String s2 = "Hello World"; //能通过检查
        String s3 = "Hello  World"; //能通过检查
        String s4 = "Hello   World"; //能通过检查
        String s5 = "Hello World!"; //不能通过检查
        //检查结果
        System.out.println(s1.matches(reg));
        System.out.println(s2.matches(reg));
        System.out.println(s3.matches(reg));
        System.out.println(s4.matches(reg));
        System.out.println(s5.matches(reg));
    }
}
