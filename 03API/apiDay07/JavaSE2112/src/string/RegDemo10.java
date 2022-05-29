package string;

public class RegDemo10 {
    public static void main(String[] args) {
        /*
         * 测试 如何匹配 域名 www.tedu.cn
         * 必须使用正则表达式 www\.tedu\.cn
         * . 表示任意字符
         * 如果匹配点就需要 \.
         */
        //定义正则表达式
        String reg1 = "www.tedu.cn"; //错误的！
        String reg2 = "www\\.tedu\\.cn";//正确的
        //定义被检查的字符串
        String s1 = "www.tedu.cn";
        String s2 = "wwwAteduAcn";
        String s3 = "www-tedu-cn";
        //测试 reg1
        System.out.println(s1.matches(reg1));
        System.out.println(s2.matches(reg1));
        System.out.println(s3.matches(reg1));
        //测试reg2
        System.out.println(s1.matches(reg2));
        System.out.println(s2.matches(reg2));
        System.out.println(s3.matches(reg2));
    }
}
