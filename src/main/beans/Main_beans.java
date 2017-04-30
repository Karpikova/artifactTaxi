package main.beans;

import javax.annotation.PostConstruct;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 27.04.2017
 * 
 * Karpikova
 */
@Profiling
public class Main_beans {
    public static void main(String[] args) {
        count();
    }

    @PostConstruct
    public void init(){

    }

    @Profiling
    public static void count(){
        int sum = 0;
        for (int i = 0; i<1000000; i++){
            sum = sum + i;
        }
        System.out.println("Sum = " + sum);
    }
}
