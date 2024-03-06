package org.example;

public class Base {
    public void process() {
        System.out.println("Hello world!");
    }

    public static void main(String[] args) throws InterruptedException {
        int flag = 0;
        while (flag < 2000) {
            new Base().process();
            Thread.sleep(1000);
            flag++;
        }
    }
}
