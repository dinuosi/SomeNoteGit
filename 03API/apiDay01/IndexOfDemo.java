package apiday01;
/*
 int indexOf(String str):
 检索给定字符串在当前字符串的开始位置
 int lastIndexOf(String str):
 检索给定字符串在当前字符串中最后一次出现的位置
 */
public class IndexOfDemo {
    public static void main(String[] args) {
        //            0123456789012345
        String str = "thinking in java";
        int index = str.indexOf("in"); //检索in在字符串str中出现的开始位置
        System.out.println(index); //2

        index = str.indexOf("IN"); //当前字符串不包含给定内容IN，所以返回-1
        System.out.println(index); //-1

        index = str.indexOf("in",3); //从第4个字符开始找in第一次出现的位置
        System.out.println(index); //5

        index = str.lastIndexOf("in"); //找in最后一次出现的位置
        System.out.println(index); //9
    }
}














