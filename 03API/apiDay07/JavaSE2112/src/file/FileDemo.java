package file;

import java.io.File;

/**
 * file 文件
 * java.io.File类
 * 该类的每一个实例可以表示硬盘上的一个文件或目录(实际表示的是一个抽象路径)
 * 通过File我们可以:
 * 1:访问其表示的文件或目录的属性信息(名字，大小等)
 * 2:创建，删除文件或目录
 * 3:访问一个目录中的子项
 * 但是不能访问文件数据
 */
public class FileDemo {
    public static void main(String[] args) {
        //访问当前项目目录下的demo.txt文件
//        File file = new File("C:/Users/TEDU/IdeaProjects/JavaSE2112/demo.txt");
        /*
            实际开发中我们几乎不会使用绝对路径去定位文件或目录，都是使用相对路径的。
            因为它有更好的跨平台性。
            例如:"./"表示当前目录，具体是哪里要看当前程序的运行环境而定，比如我们现在在
                 IDEA里执行程序时，IDEA规定当前目录就是当前程序所在的项目目录。
         */
        File file = new File("./demo.txt");
        //获取名字
        String name = file.getName();
        System.out.println("名字:"+name);
        //获取长度(单位是字节)。注:返回的是long值，因为int最大值无法表示一个文件的最大容量的字节数
        long length = file.length();
        System.out.println("长度:"+length+"字节");

        boolean cr = file.canRead();//read 读
        boolean cw = file.canWrite();//write 写
        System.out.println("可读:"+cr);
        System.out.println("可写:"+cw);

        boolean ih = file.isHidden();//hidden 隐藏
        System.out.println("是否隐藏:"+ih);
    }
}
