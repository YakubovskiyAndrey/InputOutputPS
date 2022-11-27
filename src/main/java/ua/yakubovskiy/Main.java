package ua.yakubovskiy;

import ua.yakubovskiy.task1.RegexParser;
import ua.yakubovskiy.task2.ViolationsReport;

public class Main {

    public static void main(String[] args) {
        task1();
        task2();
    }

    public static void task1(){
        RegexParser parser = new RegexParser("task1/input.xml", "output.xml");
        parser.read();
    }

    public static void task2(){
        ViolationsReport violationsReport = new ViolationsReport("task2", "stream_traffic.json");
        violationsReport.read();
    }

}