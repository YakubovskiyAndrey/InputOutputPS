package ua.yakubovskiy.task2.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.yakubovskiy.task2.entity.TrafficViolation;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonStreamingWrite {

    public void write(List<TrafficViolation> listTrafficViolations, String urlOutput){

        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            jsonMapper.writeValue(new File(urlOutput), listTrafficViolations);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
