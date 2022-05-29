package io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * 对象流
 * java.io.ObjectOutputStream和ObjectInputStream
 * 对象流是一对高级流，在流连接中完成对象与字节的转换，即:对象序列化与反序列化操作
 * 有了它们我们可以轻松读写任何java对象
 */
public class OOSDemo {
    public static void main(String[] args) throws IOException {
        String name = "苍老师";
        int age = 18;
        String gender = "女";
        String[] otherInfo = {"是一名演员","来自霓虹","爱好书法","广大男性同胞的启蒙老师"};
        Person p = new Person(name,age,gender,otherInfo);
        System.out.println(p);//输出p.toString()返回值
        //将该Person对象写入文件person.obj中
        FileOutputStream fos = new FileOutputStream("./person.obj");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        /*
            对象输出流提供的独有方法:writeObject(Object obj)
            该方法会进行对象序列化，并将序列化后的字节通过其连接的流写出。

            序列化时要求该对象必须实现可序列化接口，否则会抛出异常:
            java.io.NotSerializableException

            Serializable:可序列化
         */
        oos.writeObject(p);
        System.out.println("写出完毕!");

        oos.close();
    }
}


