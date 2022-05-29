package io;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 缓冲流写出数据的缓冲区问题
 */
public class BOS_flushDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos = new FileOutputStream("bos.txt");
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        //缓冲流内部默认有一个8K字节数组，写出的数据会先被存入数组中，直到数组装满才会写出
        bos.write("super idol的笑容都没你的甜。".getBytes(StandardCharsets.UTF_8));
        System.out.println("写出完毕!");
        /*
            flush方法的作用是让缓冲输出流将其缓冲区中已经缓存的数据立即写出
         */
        bos.flush();//flush 冲水

        bos.close();
    }
}




