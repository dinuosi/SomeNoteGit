package string;

public class RegDemo05 {
    public static void main(String[] args) {
        /*
         * 测试正则表达式
         */
        //定义正则表达式
        String rule = "HelloWorld";
        //定义被检测的字符串
        String s1 = "HelloKitty";
        String s2 = "HelloWorld";
        //检测 s1 是否符合规则
        boolean b1 = s1.matches(rule);
        //检测 s2 是否符合规则
        boolean b2 = s2.matches(rule);
        System.out.println(b1);
        System.out.println(b2);
    }
}
