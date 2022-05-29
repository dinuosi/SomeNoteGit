package io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 文件输出流的追加写模式
 */
public class FosDemo2 {
    public static void main(String[] args) throws IOException {
        /*
            文件流有两种创建模式:覆盖模式和追加模式。
            构造器:
            FileOutputStream(String path)
            FileOutputStream(File file)
            以上两种为覆盖模式的构造器，这种情况下创建文件输出流时如果连接的文件已经存在
            则会吧文件之前的数据全部清除。

            构造器:
            FileOutputStream(String path,boolean append)
            FileOutputStream(File file,boolean append)
            如果第二个参数传入true，则文件流为追加模式，此时连接的文件已经存在会保留文件
            之前的所有数据。通过当前流每次写入的内容都会陆陆续续追加到文件后面。
         */
        FileOutputStream fos = new FileOutputStream("fos.txt",true);
        String line = "你叉叉，穷哈哈~哈利波特骑着扫帚飞，sorry,sorry~";
        fos.write(line.getBytes(StandardCharsets.UTF_8));

        System.out.println("写出完毕!");
        fos.close();
    }
}




