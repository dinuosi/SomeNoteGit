package day05;
//数组的演示
public class ArrayDemo {
    public static void main(String[] args) {
        //4)数组的遍历/迭代:从头到尾挨个走一遍
        int[] arr = new int[10];
        for(int i=0;i<arr.length;i++){ //遍历arr数组
            arr[i] = (int)(Math.random()*100); //给每个元素赋值为0到99的随机数
            System.out.println(arr[i]); //输出每个元素的值
        }












        /*
        //3)数组的访问:访问的是数组中的元素
        int[] arr = new int[3];
        arr[0] = 100; //给第1个元素赋值为100
        arr[1] = 200; //给第2个元素赋值为200
        arr[2] = 300; //给第3个元素赋值为300
        //arr[3] = 400; //运行时发生数组下标越界异常
        System.out.println(arr.length); //3，输出arr的长度
        System.out.println(arr[arr.length-1]); //300，输出最后一个元素的值
        */

        /*
        //1)数组的定义:
        int[] arr = new int[10]; //声明整型数组arr，包含10个元素，每个元素都是int型，默认值为0

        //2)数组的初始化:初始化的是数组中的元素
        int[] arr1 = new int[3]; //0,0,0
        int[] arr2 = {2,5,8}; //2,5,8
        int[] arr3 = new int[]{2,5,8}; //2,5,8
        int[] arr4;
        //arr4 = {2,5,8}; //编译错误，此方式只能声明同时初始化
        arr4 = new int[]{2,5,8}; //正确
        */
    }
}




