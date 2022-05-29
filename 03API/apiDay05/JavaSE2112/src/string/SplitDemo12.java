package string;

public class SplitDemo12 {
    public static void main(String[] args) {
        /*
         * 测试Split
         */
        String str = "1, Tom,110,   tom@tedu.cn";
        //将一个字符串 劈开split
        String[] arr = str.split(",\\s*");
        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
