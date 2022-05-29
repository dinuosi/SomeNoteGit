package file;

import java.io.File;

/**
 * 编写一个程序，要求实现1+2+3+4+....100并输出结果。
 * 代码中不能出现for，while关键字
 *
 * 编写程序计算:
 * 一个人买汽水，1块钱1瓶汽水。3个瓶盖可以换一瓶汽水，2个空瓶可以换一瓶汽水。不考虑赊账问题
 * 问20块钱可以最终得到多少瓶汽水。
 *
 * 删除一个目录
 */
public class Test {
    public static void main(String[] args) {
        File dir = new File("./a");
        delete(dir);
    }

    /**
     * 将给定的File对象表示的文件或目录删除
     * @param file
     */
    public static void delete(File file){
        if(file.isDirectory()) {
            //清空目录
            File[] subs = file.listFiles();
            for (int i = 0; i < subs.length; i++) {
                File sub = subs[i];//从目录中获取一个子项
                //将该子项删除
                delete(sub);//递归调用
            }
        }
        file.delete();
    }
}
