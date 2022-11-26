package ua.yakubovskiy;

import ua.yakubovskiy.task1.RegexParser;
import ua.yakubovskiy.task2.entity.TrafficViolation;
import ua.yakubovskiy.task2.parsers.JacksonStreamingWrite;
import ua.yakubovskiy.task2.parsers.StaxParser;
import javax.xml.stream.XMLStreamException;
import java.util.List;
import java.util.function.Consumer;

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
        List<TrafficViolation> trafficViolationList;
        try {
            trafficViolationList = parser.parse();
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
        if(trafficViolationList != null) {
            JacksonStreamingWrite jacksonStreamingWrite = new JacksonStreamingWrite();
            jacksonStreamingWrite.write(trafficViolationList);
        }
    }

}