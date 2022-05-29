package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 实现文件复制
 */
public class CopyDemo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream("ppt.pptx");
        FileOutputStream fos = new FileOutputStream("ppt_cp.pptx");
        int d;//记录每次读取到的字节
        /*
            1.mp4文件数据:
            11001100 00110011 11110000 00001111 10101010 01010101
                     ^^^^^^^^

            1_cp.mp4文件
            11001100 00110011

                                         vvvvvvvv
            d:00000000 00000000 00000000 00110011
         */
        long start = System.currentTimeMillis();
        while( (d = fis.read()) != -1 ){
            fos.write(d);
        }
        long end = System.currentTimeMillis();
        //下面的代码不对，这样会出现奇数位置上的字节读取用于判断，却只将偶数位置的字节写入复制文件
//        while( (fis.read()) != -1 ){
//            fos.write(fis.read());
//        }
        System.out.println("复制完毕!,耗时:"+(end-start)+"ms");
        fis.close();
        fos.close();
    }
}
