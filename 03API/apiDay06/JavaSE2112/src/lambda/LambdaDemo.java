package lambda;

import java.io.File;
import java.io.FileFilter;

/**
 * JDK8之后推出了一个新的特性:lambda表达式
 * lambda表达式可以用更精简的方式创建匿名内部类。
 * 语法:
 * (参数列表)->{
 *     方法体
 * }
 * 不是所有匿名内部类创建的情景都可以替换为lambda.前提要求是实现的接口只能有一个抽象方法时才可以
 * 使用lambda表达式。
 */
public class LambdaDemo {
    public static void main(String[] args) {
        FileFilter filter = new FileFilter() {
            public boolean accept(File file) {
                return file.getName().contains("o");
            }
        };
        //忽略了接口名和方法名
        FileFilter filter1 = (File file)->{
                return file.getName().contains("o");
        };
        //参数类型可以忽略。如果方法只有一个参数时，那么"()"也可以忽略。
        FileFilter filter2 = file->{
            return file.getName().contains("o");
        };
        //如果方法体只有一句代码，那么可以将方法体"{}"忽略，如果含有return则return要一同忽略
        FileFilter filter3 = file->file.getName().contains("o");


        File dir = new File(".");
//        File[] subs = dir.listFiles(new FileFilter() {
//            public boolean accept(File file) {
//                return file.getName().contains("o");
//            }
//        });
        /*
            lambda表达式也是编译器认可的，最终会被编译器改回成内部类创建的形式
         */
        File[] subs = dir.listFiles(f->f.getName().contains("o"));

    }
}





