package ua.yakubovskiy.task2;

import ua.yakubovskiy.task2.entity.TrafficViolation;
import ua.yakubovskiy.task2.parsers.JacksonStreamingWrite;
import ua.yakubovskiy.task2.parsers.StaxParser;
import javax.xml.stream.XMLStreamException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class ViolationsReport {

    private final String resourceFolder;

    private final String urlOutput;

    private final StaxParser staxParser = new StaxParser();

    private final JacksonStreamingWrite jacksonWriter = new JacksonStreamingWrite();

    public ViolationsReport(String resourceFolder, String urlOutput) {
        this.resourceFolder = resourceFolder;
        this.urlOutput = urlOutput;
    }

    public void read(){
        List<TrafficViolation> trafficViolationsList = new ArrayList<>();
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(resourceFolder);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (bufferedReader.ready()) {
                staxParser.parse(resourceFolder + "/" + bufferedReader.readLine().trim(), trafficViolationsList);
            }
            if(!trafficViolationsList.isEmpty()) {
                jacksonWriter.write(sortViolations(trafficViolationsList), urlOutput);
            }
        } catch (XMLStreamException | IOException e) {
            e.printStackTrace();
        }
    }

    private List<TrafficViolation> sortViolations(List<TrafficViolation> trafficViolationsList){
        return trafficViolationsList.stream()
                .collect(Collectors.toMap(TrafficViolation::getType, TrafficViolation::getFineAmount,
                        Double::sum,
                        LinkedHashMap::new))
                .entrySet()
                .stream()
                .map(entry -> new TrafficViolation(0, entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingDouble(TrafficViolation::getFineAmount).reversed())
                .collect(Collectors.toList());
    }

}
