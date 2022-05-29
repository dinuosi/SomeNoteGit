package string;

public class SplitDemo13 {
    public static void main(String[] args) {
        /*
         * split demo
         */
        String str = "boo:and:foo";
        String[] arr = str.split(":");
        for (int i = 0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
        //劈开时候，会自动舍弃后面的空字符串
        // "boo:and:foo"
        //   oo      oo
        // "b" "" ":and:f"
        arr = str.split("o");
        for (int i = 0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }
}
