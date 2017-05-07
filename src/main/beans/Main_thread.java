package main.beans;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 02.05.2017
 * 
 * Karpikova
 */
public class Main_thread {
    public volatile int i;
    public static volatile int k;


    /*public static void main(String[] args) {
        int i = 7;
        i = -+(10 + 2 + i);   //2
        --i++;
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int val = i;
                k = 10;
                System.out.println(val);
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                int val = k;
                i = 5;
                System.out.println(val);
            }
        });
        thread.start();
        thread2.start();
    }*/

}
