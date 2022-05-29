package string;

import java.util.Scanner;

public class ReplaceAllDemo14 {
    public static void main(String[] args) {
        /*
         * 替换, 敏感词替换
         */
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入：");
        String str = scanner.nextLine();
        String s = str.replaceAll("我[去艹草靠]", "***");
        System.out.println(s);
    }
}
