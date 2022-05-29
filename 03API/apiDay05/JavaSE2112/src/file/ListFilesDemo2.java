package file;

import java.io.File;
import java.io.FileFilter;

/**
 * 获取一个目录中符合过滤条件的所有子项
 * 重载的listFiles方法:
 * File[] listFiles(FileFilter filter)
 * 该方法要求传入一个文件过滤器，然后仅将满足过滤器要求的所有子项进行返回。
 *
 * 注:
 * filter:过滤器
 * accept:接受
 * contains:包含
 */
public class ListFilesDemo2 {
    public static void main(String[] args) {
        File dir = new File(".");

        FileFilter fileFilter = new FileFilter() {
            public boolean accept(File file) {
                String name = file.getName();//获取该元素的名字
                System.out.println("正在过滤元素:"+name);
                return name.contains("o");
            }
        };

        File[] subs = dir.listFiles(fileFilter);
        System.out.println("共有子项:"+subs.length+"个");
        for(int i=0;i<subs.length;i++){
            System.out.println(subs[i].getName());
        }
    }
}



