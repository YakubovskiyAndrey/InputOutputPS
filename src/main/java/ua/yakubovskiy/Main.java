package ua.yakubovskiy;

import ua.yakubovskiy.task1.RegexParser;

public class Main {

    public static void main(String[] args) {
        task1();
    }

    public static void task1(){
        RegexParser parser = new RegexParser("task1/input.xml", "output.xml");
        parser.read();
    }

}