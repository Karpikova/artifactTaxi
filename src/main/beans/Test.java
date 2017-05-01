package main.beans;

import java.util.Set;
import java.util.TreeSet;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 29.04.2017
 * 
 * Karpikova
 */
public class Test {

    public static void main(String[] args) {
            A a = new B();
            a.m(1);
          }
}

class A {
    public void m(int
                          n) {
        System.out.println("class A, method m : " + n);
    }
}

class B extends A {
    public void m(byte n) {
        System.out.println("class B, method m : ");
    }
}