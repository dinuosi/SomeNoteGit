package day05;
//for结构的演示
public class ForDemo {
    public static void main(String[] args) {
        //输出9的乘法表，只要不能被3整除的
        for(int i=1;i<=9;i++){
            if(i%3!=0){
                System.out.println(i+"*9="+i*9);
            }
        }

        /*
        //输出9的乘法表，跳过能被3整除的
        for(int i=1;i<=9;i++){
            if(i%3==0){
                continue; //跳过循环体中剩余语句而进入下一次循环
            }
            System.out.println(i+"*9="+i*9);
        }
         */
        /*
          执行过程:
            i=1  1*9=9
            i=2  2*9=18
            i=3
            i=4  4*9=36
            i=5  5*9=45
            i=6
            i=7  7*9=63
            i=8  8*9=72
            i=9
            i=10
         */













        /*
        for(int i=1;i<=9;i++){
            if(i==4){ //在某种特定条件下，提前结束循环
                break; //跳出循环
            }
            System.out.println(i+"*9="+i*9);
        }
        */
        /*
          执行过程:
            i=1  1*9=9
            i=2  2*9=18
            i=3  3*9=27
            i=4
         */








        /*
        int i = 1;
        for(;i<=9;i++){
            System.out.println(i+"*9="+i*9);
        }
        */

        /*
        for(int i=1;i<=9;){
            System.out.println(i+"*9="+i*9);
            i++;
        }
         */

        /*
        for(;;){ //没有条件的循环就是一个死循环
            System.out.println("欢迎大家来到达内");
        }
        */

        /*
        for(int i=1,j=5;i<=5;i+=2,j-=2){
        }
         */
        /*
          i=1,j=5
          i=3,j=3
          i=5,j=1
          i=7,j=-1
         */
















        /*
        //for中的循环变量i，它的作用域仅在当前for中
        for(int i=1;i<=9;i++){
            System.out.println(i+"*9="+i*9);
        }
        for(int i=9;i>=1;i--){
            System.out.println(i+"*9="+i*9);  //9:58继续
        }

        for(int times=0;times<5;times++){
            System.out.println("行动是成功的阶梯");
        }
        System.out.println("继续执行...");
        */
        /*
          执行过程:
            times=0  true  输出
            times=1  true  输出
            times=2  true  输出
            times=3  true  输出
            times=4  true  输出
            times=5  false for循环结束
            输出继续执行...
         */














        /*
          3.for结构:
            1)语法:
               //    1    2     3
               for(要素1;要素2;要素3){
                 语句块/循环体-------------反复执行的语句  4
               }
            2)执行过程:
               1243243243243243...2
         */
    }
}
















