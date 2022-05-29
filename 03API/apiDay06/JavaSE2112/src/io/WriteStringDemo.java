package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 向文件中写出字符串
 */
public class WriteStringDemo {
    public static void main(String[] args) throws IOException {
        //向文件demo.txt中写入文本数据
        FileOutputStream fos = new FileOutputStream("demo.txt");
        String line = "beep,beep,i'm sheep~beep,beep,i'm sheep~";
        /*
            String提供了将字符串转换为一组字节的方法
            使用的字符集通常用UTF-8
            java.nio.charset.StandardCharsets下的UTF_8
         */
        byte[] data = line.getBytes(StandardCharsets.UTF_8);
        fos.write(data);
        fos.write("比，比，爱慕希~比，比，爱慕希~".getBytes(StandardCharsets.UTF_8));
        System.out.println("写出完毕!");
        fos.close();

    }
}




