package org.example.aspectj;


public class Test {
    @Log
    public void test(){
        System.out.println("1111");
    }

    public static void main(String[] args) {
        new Test().test();
    }
}
