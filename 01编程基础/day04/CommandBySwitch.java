package day04;
import java.util.Scanner;
//命令解析程序
public class CommandBySwitch {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("请选择功能: 1.取款  2.存款  3.查询余额  0.退卡");
        int command = scan.nextInt();

        switch(command){
            case 1:
                System.out.println("取款操作...");
                break;
            case 2:
                System.out.println("存款操作...");
                break;
            case 3:
                System.out.println("查询余额操作...");
                break;
            case 0:
                System.out.println("退卡操作...");
                break;
            default:
                System.out.println("输入错误");
        }
    }
}















