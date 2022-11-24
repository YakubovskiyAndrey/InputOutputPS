package ua.yakubovskiy;

import ua.yakubovskiy.task1.RegexParser;
import ua.yakubovskiy.task2.parsers.StaxParser;
import javax.xml.stream.XMLStreamException;

public class Main {

    public static void main(String[] args) {

        //task1();
        task2();
    }

    public static void task1(){
        RegexParser parser = new RegexParser("task1/input.xml", "output.xml");
        parser.read();
    }

    public static void task2(){
        StaxParser parser = new StaxParser("task2/inputViolation.xml");
        try {
            parser.parse();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }

}