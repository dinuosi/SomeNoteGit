package file;

import java.io.File;

/**
 * 创建一个目录
 */
public class MkDirDemo {
    public static void main(String[] args) {
        //当前目录下新建一个目录:demo
//        File file = new File("./demo");
        File file = new File("./a/b/c/d/e/f");
        if(file.exists()){
            System.out.println("该目录已存在!");
        }else{
            /*
                mkdir()创建目录时要求该目录所在的目录必须存在，否则无法创建
                mkdirs()创建目录时会将所有不存在的父目录都创建出来。(推荐)
             */
//            file.mkdir();//make 做，造
            file.mkdirs();//make 做，造
            System.out.println("目录已创建!");
        }
    }
}








