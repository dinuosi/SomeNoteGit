package day03;
//if结构的演示
public class IfDemo {
    public static void main(String[] args) {
        int num = 6; //带数(6,5)
        if(num%2==0){
            System.out.println(num+"是偶数");
        }
        System.out.println("继续执行...");

        double price = 300.0; //消费金额
        if(price>=500){ //满500   带数(600.0,300.0)
            price*=0.8; //打8折
        }
        System.out.println("最终结算金额为:"+price);
















        /*
          1.if结构: 1条路
            1)语法:
               if(boolean){
                 语句块-----------基于条件执行的语句
               }
            2)执行过程:
               判断boolean的值:
                 若为true，则执行语句块(整个结束)
                 若为false，则if直接结束
         */
    }
}
















