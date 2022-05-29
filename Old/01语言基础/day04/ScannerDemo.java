package day04;
import java.util.Scanner; //1.我想用扫描仪
//Scanner的演示
public class ScannerDemo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in); //2.新建了个扫描仪scan
        System.out.println("请输入年龄:");
        int age = scan.nextInt(); //3.用扫描仪扫描用户输入的整数
        System.out.println("请输入商品价格:");
        double price = scan.nextDouble(); //3.用扫描仪扫描用户输入的小数
        System.out.println("年龄为:"+age+"，商品价格为:"+price);

        //创建ScoreLevel类，接收用户输入的成绩score(double)，并输出
        //创建CommandBySwitch类，接收用户输入的命令command(int)，并输出

    }
}





























