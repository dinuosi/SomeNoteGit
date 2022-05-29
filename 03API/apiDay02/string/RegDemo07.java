package string;

public class RegDemo07 {
    public static void main(String[] args) {
        /*
         * 测试 用户名规则：6个单词字符组成
         * - \ 在java字符串中需要进行转义为 \\
         */
        //正则表达式: 可不可以写成： ......
        String reg = "\\w{6}";
        System.out.println(reg);
        //被检查的字符串
        String s1 = "Jerry1"; //可以通过检查
        String s2 = "Tom-12"; //不可以通过检查
        String s3 = "Andy";   //不可以通过检查
        System.out.println(s1.matches(reg));
        System.out.println(s2.matches(reg));
        System.out.println(s3.matches(reg));
    }
}
