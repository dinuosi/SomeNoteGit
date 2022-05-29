package string;

public class RegDemo13 {
    public static void main(String[] args) {
        /*
         * 劈开IP地址
         */
        String ip = "192.168.5.25";
        String[] arr = ip.split("\\.");
        for (int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
