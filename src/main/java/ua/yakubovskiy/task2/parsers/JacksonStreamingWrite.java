package ua.yakubovskiy.task2.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.yakubovskiy.task2.entity.TrafficViolation;
import java.io.*;
import java.util.List;

public class JacksonStreamingWrite {

    public void write(List<TrafficViolation> listTrafficViolations){

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            jsonMapper.writeValue(new File("stream_traffic.json"), listTrafficViolations);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
