package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * 实现简易记事本工具
 * 程序启动后，要求将控制台输入的每一行字符串都写入到文件note.txt中。
 * 当在控制台单独输入exit时，程序退出。
 */
public class Test2 {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请开始输入内容，单独输入exit退出。");
        FileOutputStream fos = new FileOutputStream("note.txt");

        while(true){
            String line = scanner.nextLine();
//            if("exit".equals(line)){
            //String提供了一个忽略大小写比较字符串内容的方法:equalsIgnoreCase
            if("exit".equalsIgnoreCase(line)){
//            if(line.equals("exit")){
                break;
            }
            fos.write(line.getBytes(StandardCharsets.UTF_8));
        }

//        String line;
//        while(!"exit".equals(line = scanner.nextLine())){
//            fos.write(line.getBytes(StandardCharsets.UTF_8));
//        }

        fos.close();
    }
}
