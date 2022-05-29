package io;

public class Test {
    public static void main(String[] args) {
        long max = Long.MAX_VALUE;
        max = max/1000/60/60/24/365;
        System.out.println("公元:"+(1970+max));
    }
}
