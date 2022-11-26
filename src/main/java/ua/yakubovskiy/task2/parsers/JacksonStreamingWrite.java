package ua.yakubovskiy.task2.parsers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import ua.yakubovskiy.task2.entity.TrafficViolation;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JacksonStreamingWrite {

    public void write(List<TrafficViolation> listTrafficViolations){

        try (JsonGenerator jsonGenerator = new JsonFactory()
                .createGenerator(new FileOutputStream("stream_traffic.json"))){

            jsonGenerator.setPrettyPrinter(new DefaultPrettyPrinter());

            for (TrafficViolation violation: listTrafficViolations){
                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", violation.getId());
                jsonGenerator.writeStringField("firstName", violation.getFirstName());
                jsonGenerator.writeNumberField("fineAmount", violation.getFineAmount());
                jsonGenerator.writeEndObject();
            }
            jsonGenerator.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
