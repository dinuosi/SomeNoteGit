package file;

import java.io.File;
import java.io.IOException;

/**
 * 使用File新建一个文件
 */
public class CreateNewFileDemo {
    public static void main(String[] args) throws IOException {
        //在当前目录下新建一个文件:test.txt
//        File file = new File("./test.txt");
        //相对路径中"./"可以忽略不写，默认就是从"./"开始
        File file = new File("test.txt");
        //boolean exists() 判断当前File表示的文件或目录是否真实存在，存在则返回true.
        if(file.exists()){
            System.out.println("该文件已存在!");
        }else{
            file.createNewFile();//create创造 new新的 file文件
            System.out.println("该文件已创建!");
        }

    }
}
