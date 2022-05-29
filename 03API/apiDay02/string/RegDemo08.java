package string;

public class RegDemo08 {
    public static void main(String[] args) {
        /*
         * 测试： 网站的密码是单词字符， 最少8个， 多了不限: \w{8,}
         */
        //定义 网站密码的规则
        String reg = "\\w{8,}";
        //定义 被检查的字符串
        String s1 = "asdfghjkl"; //可以通过检查的字符串
        String s2 = "abcde";     //不能通过检查的字符串，太短了
        String s3 = "asdfghjkl;";//不能通过检查的字符串，包括 ";"
        //... 更多的测试
        System.out.println(s1.matches(reg));
        System.out.println(s2.matches(reg));
        System.out.println(s3.matches(reg));
    }
}
