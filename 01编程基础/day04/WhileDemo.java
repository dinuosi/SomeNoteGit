package day04;
//while结构的演示
public class WhileDemo {
    public static void main(String[] args) {
        System.out.println(Math.random());



        /*
        //2)输出9的乘法表:
        int num = 1;     //3*9=27
        while(num<=9){
            System.out.println(num+"*9="+num*9);
            num++;  //num+=2;
        }
        System.out.println("继续执行...");
         */
        /*
        //1)输出5次"行动是成功的阶梯":
        int times = 0;  //1)循环变量的初始化
        while(times<5){ //2)循环的条件
            System.out.println("行动是成功的阶梯");
            times++;    //3)循环变量的改变
        }
        System.out.println("继续执行...");
         */
        /*
          执行过程:----带数
                        times=0
            true  输出  times=1
            true  输出  times=2
            true  输出  times=3
            true  输出  times=4
            true  输出  times=5
            false while循环结束
            输出继续执行...
         */













        /*
          1.while结构:
            1)语法:
               while(boolean){
                 语句块-------------反复执行的代码
               }
            2)执行过程:
               判断boolean的值，若为true则执行语句块，
               再判断boolean的值，若为true则再执行语句块，
               再判断boolean的值，若为true则再执行语句块，
               如此反复，直到boolean的值为false时，while循环结束
         */
    }
}
















