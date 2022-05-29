package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 提高每次读写的数据量减少实际读写的次数可以提高读写效率
 * 一组字节一组字节的读写称为:块读写形式
 * 单字节读写是:随机读写形式
 *
 * 使用块读写复制可以显著提高复制效率
 */
public class CopyDemo2 {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("ppt.pptx");
        FileOutputStream fos = new FileOutputStream("ppt_cp2.pptx");
        /*
            超类:java.io.InputStream中定义了块读取字节的方法:
            int read(byte[] data)
            一次性读取给定的字节数组总长度的字节量，并存入到该数组中。返回值为实际读取到的
            字节量。如果返回值为整数"-1"表示流读取到了末尾

            ppt.pptx文件数据:
            11001100 00110011 11110000 00001111 10101010 01010101 11000011

            byte[] data = new byte[4];
            int len;

            第一次调用:
            len = fis.read(data);
            从文件中一次性读取4个字节
            读取ppt.pptx文件数据:
            11001100 00110011 11110000 00001111 10101010 01010101 11000011
            ^^^^^^^^ ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
            data:[11001100 00110011 11110000 00001111]
            len:4 表示实际读取到了4个字节


            第二次调用:
            len = fis.read(data);
            读取ppt.pptx文件数据:
            11001100 00110011 11110000 00001111 10101010 01010101 11000011
                                                ^^^^^^^^ ^^^^^^^^ ^^^^^^^^
                                                仅能再读取3个字节了
            data:[10101010 01010101 11000011 00001111]
                 |-----本次读取到的新数据-----| |旧数据 |
            len:3 表示实际读取到了3个字节


            第三次调用:
            len = fis.read(data);
            读取ppt.pptx文件数据:
            11001100 00110011 11110000 00001111 10101010 01010101 11000011 文件末尾
                                                                           ^^^^^^^^
            已经是文件末尾了，本次一个字节都没有读取到
            data:[10101010 01010101 11000011 00001111]
                 |--------------旧数据----------------|
            len:-1 表示文件末尾了!


            字节输出流的超类:java.io.OutputStream定义了块写操作
            void write(byte[] data)
            一次性将给定字节数组中所有字节写出

            void write(byte[] data,int offset,int len)
            将给定的字节数组从下标offset处开始的连续len个字节一次性写出
         */
        /*
            0 1 1位2进制称为1bit
            00000000 8位2进制称为1byte(1字节)
            1024byte  1kb
            1024kb    1mb
            1024mb    1gb
            1024gb    1tb
         */
        byte[] data = new byte[1024*10];//10kb
        int len;

        long start = System.currentTimeMillis();
        while((len = fis.read(data))!=-1){
//            fos.write(data);
            fos.write(data,0,len);
        }
        long end = System.currentTimeMillis();
        System.out.println("复制完毕!,耗时:"+(end-start)+"ms");
        fis.close();
        fos.close();

    }
}
