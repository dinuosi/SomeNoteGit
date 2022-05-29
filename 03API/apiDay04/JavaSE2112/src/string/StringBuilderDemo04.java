package string;

public class StringBuilderDemo04 {
    public static void main(String[] args) {
        /*
         * 测试StringBuilder API方法
         */
        StringBuilder buf = new StringBuilder();
        buf.append("A")
                .append("A") // 追加方法
                .append("A")
                .append("B")
                .insert(1, "C") //插入方法
                .delete(2,4); //删除方法
        //一般情况下 StringBuilder 都需要转换为String
        // StringBuilder 提供了toString方法，可以将StringBuilder转换为String
        String str = buf.toString();
        System.out.println(str);
        System.out.println(buf);
    }
}
