package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 使用文件输入流从文件中读取字节
 */
public class FISDemo {
    public static void main(String[] args) throws IOException {
        //从文件test.dat中读取数据
        FileInputStream fis = new FileInputStream("./test.dat");
        /*
            int read()
            读取1个字节，并将该字节的2进制保存在返回的int值的"低八位"上。
            如果返回的int值为整数"-1"时，则说明读取到了流的末尾。对于读取文件而言相当于是
            读取到了文件的末尾:EOF  end of file 文件的结尾
                                 结尾

            test.dat中的文件数据:
            00000001 00000010
            ^^^^^^^^

            int d = fis.read();
            d:00000000 00000000 00000000 00000001
              前24位2进制补0               读取的字节
         */
        int d = fis.read();
        System.out.println(d);//1
        /*
            00000001 00000010
                     ^^^^^^^^
            d = fis.read();
            d:00000000 00000000 00000000 00000010
              前24位2进制补0               读取的字节
         */
        d = fis.read();//2
        System.out.println(d);
        /*
            00000001 00000010 文件末尾
                              ^^^^^^^^
            d = fis.read();
            d:11111111 11111111 11111111 11111111
         */
        d = fis.read();//-1
        System.out.println(d);

        fis.close();
    }
}




