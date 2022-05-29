package binary;

import java.util.ArrayList;

public class Demo06 {
    public static void main(String[] args) {
        /*
         * 掩码运算
         */
        int n = 0x179d5d9e;
        //4位掩码：0xf 0b1111 15
        //6位掩码：0x3f 0b111111 63
        //8位掩码：0xff 0b11111111 255
        int m = 0xff; //4位 6位 8位 16位
        int k = n & m;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(m));
        System.out.println(Integer.toBinaryString(k));

    }
}
