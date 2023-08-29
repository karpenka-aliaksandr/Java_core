package com.example.main;

import com.example.other.Decorator;
import com.example.other.Strings; 

/**
 * Hello world!
 */
public final class App {
    private App() {
    }

    /**
     * Says hello to the world.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        Strings str = new Strings();
        Decorator dec = new Decorator();
        System.out.println(dec.RedString(str.HelloWorld()));
    }

   
}
