package string;

public class RegDemo11 {
    public static void main(String[] args) {
        /*
         * 检查IP地址是否符合规则
         */
        //定义正则规则
        //String reg = "\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}";
        String reg = "\\d{1,3}(\\.\\d{1,3})(\\.\\d{1,3})(\\.\\d{1,3})";
        //String reg = "(\\d{1,3}\\.){3}\\d{1,3}"; //测试分组
        //定义被检查的字符串
        String ip1 = "192.168.2.70";
        String ip2 = "10.0.0.20";
        String ip3 = "8.8.8.8";
        //定义错误的被检查字符串
        String ip4 = "192点168点2点70";
        String ip5 = "192-168-2-70";
        //检查
        System.out.println(ip1.matches(reg));
        System.out.println(ip2.matches(reg));
        System.out.println(ip3.matches(reg));
        System.out.println(ip4.matches(reg));
        System.out.println(ip5.matches(reg));
    }
}
