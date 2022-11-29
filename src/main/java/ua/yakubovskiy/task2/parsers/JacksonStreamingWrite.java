package ua.yakubovskiy.task2.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import ua.yakubovskiy.task2.entity.TrafficViolation;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JacksonStreamingWrite {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    public void write(List<TrafficViolation> listTrafficViolations, String urlOutput){
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try{
            jsonMapper.writeValue(new File(urlOutput), listTrafficViolations);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
