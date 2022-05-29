package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  JAVA IO
 *  IO指的是Input和Output 即:输入与输出
 *  输入:是从外界进到程序里的方向，是一个"获取数据"的过程，所以是用来"读取"的
 *  输出:是从程序到外界的方向，是一个"发送出去数据"的过程，所以是用来"写出"的
 *
 *  java将IO比喻为"流",英文:stream
 *  可以理解为是建立在程序与连接的另一端之间的"管道"，并向着同一方向顺序移动的过程。
 *
 *  java.io.InputStream和OutputStream是所有字节输入流和输出流的超类。所有的字节流都继承
 *  自他们。
 *  InputStream中定义了所有字节输入流都要具备的读取字节的方法。
 *  OutputStream中定义了所有字节输出流都要具备的写出字节的方法。
 *
 *  读写不同的设备java为我们准备了专门连接该设备的输入与输出流。他们都继承了InputStream和
 *  OutputStream，因此对我们来说读写操作是一样的，我们只需要在对不同设备进行读写时创建对应
 *  的流即可。
 *
 *  文件流:
 *  java.io.FileInputStream和FileOutputStream
 *  文件流是用来连接程序与文件之间的"管道",负责读写文件数据的流。注:他们继承自InputStream和
 *  OutputStream。
  */
public class FOSDemo {
    public static void main(String[] args) throws IOException {
        //向文件test.dat中写入数据
        /*
            文件字节输出流常用的构造方法:
            FileOutputStream(String path)
            FileOutputStream(File file)
         */
        FileOutputStream fos = new FileOutputStream("./test.dat");

//        File file = new File("./test.dat");
//        FileOutputStream fos = new FileOutputStream(file);
        /*
            void write(int d)
            写出1个字节，写出的是给定的int值对应的2进制的"低八位"

                                       vvvvvvvv
            00000000 00000000 00000000 00000001
            test.dat中的数据:
            00000001
         */
        fos.write(1);
        /*
                                       vvvvvvvv
            00000000 00000000 00000000 00000010

             test.dat中的数据:
             11111111 00000010
         */
        fos.write(2);

        fos.close();//流在使用完毕后要调用close关闭。
    }
}
