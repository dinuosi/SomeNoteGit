package day03;
//if结构的演示
public class IfDemo {
    public static void main(String[] args) {
        /*
        //2)满500打8折:
        double price = 300.0; //消费金额  带数(600.0,300.0)
        if(price>=500){ //满500
            price *= 0.8; //打8折
        }
        System.out.println("最终结算金额为:"+price);
        */
        //3)判断年龄是否在18到50之间，若满足则输出:"满足条件"
        int age = 88;  //带数(25,5,88)
        if(age>=18 && age<=50){
            System.out.println("年龄满足条件");
        }
        System.out.println("继续执行...");










        /*
        //1)偶数的判断:
        int num = 5;  //带数(6,5)
        if(num%2==0){
            System.out.println(num+"是偶数");
        }
        System.out.println("继续执行...");
        */











        /*
          1.if结构: 1条路
            1)语法:
               if(boolean){
                 语句块
               }
            2)执行过程:
               判断boolean的值:
                 若为true，则执行语句块(整个if结束)
                 若为false，则if直接结束
         */
    }
}
















