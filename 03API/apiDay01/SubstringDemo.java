package apiday01;

/**
 * String substring(int start,int end)
 * 截取当前字符串中指定范围内的字符串(含头不含尾--包含start，但不包含end)
 */
public class SubstringDemo {
    public static void main(String[] args) {
        /*
        //            01234567890
        String str = "www.tedu.cn";
        String name = str.substring(4,8); //截取第4个到第7个----下标
        System.out.println(name); //tedu
        name = str.substring(4); //从第4个一直截取到字符串末尾----下标
        System.out.println(name); //tedu.cn
        */

        String name = getName("www.tedu.com.cn");
        System.out.println(name); //tedu
        String str = getName("http://www.google.com");
        System.out.println(str); //google

    }

    /**
     * 获取给定网址中的域名
     * @param line 网址
     * @return 返回域名
     */
    public static String getName(String line){
        //012345678901234
        //www.tedu.com.cn  第一个点到第二个点之间的字符串
        int start = line.indexOf(".")+1; //4，加1目的是为了找到点后的第一个字符的位置
        int end = line.indexOf(".",start); //8，从start往后找第一个.的位置
        return line.substring(start,end);
    }

}


















