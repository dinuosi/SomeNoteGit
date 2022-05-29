package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 从文件中读取字符串
 */
public class ReadStringDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("demo.txt");
        /*
            利用available表示文件的长度。注:该方法实际是预估目前能通过输入流读取到多少
            字节。不能记为流只能读取到这么多就再也读取不到数据了!!
         */
        byte[] data = new byte[fis.available()];
        //一次性读取文件中所有的字节到数组中(原因是字节数组长度与文件长度正好一致)
        fis.read(data);
        //String的构造方法支持将给定的字节数组按照指定的字符集还原为字符串
        String str = new String(data, StandardCharsets.UTF_8);
        System.out.println(str);
        fis.close();
    }
}






