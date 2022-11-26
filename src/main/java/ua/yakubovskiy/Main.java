package ua.yakubovskiy;

import ua.yakubovskiy.task1.RegexParser;
import ua.yakubovskiy.task2.entity.TrafficViolation;
import ua.yakubovskiy.task2.parsers.JacksonStreamingWrite;
import ua.yakubovskiy.task2.parsers.StaxParser;
import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Stream;

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
       /* StaxParser parser = new StaxParser();
        List<TrafficViolation> trafficViolationList;*/
        try (InputStream in = ClassLoader.getSystemResourceAsStream("task2");
             BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            while (br.ready()) System.out.println(br.readLine());
           /* trafficViolationList = parser.parse(paths);
            if(trafficViolationList != null) {
                JacksonStreamingWrite jacksonStreamingWrite = new JacksonStreamingWrite();
                jacksonStreamingWrite.write(trafficViolationList);
            }*/
        /*} catch (XMLStreamException e) {
            e.printStackTrace();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}