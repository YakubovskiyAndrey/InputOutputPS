package ua.yakubovskiy.task2;

import ua.yakubovskiy.task2.entity.TrafficViolation;
import ua.yakubovskiy.task2.parsers.JacksonStreamingWrite;
import ua.yakubovskiy.task2.parsers.StaxParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

    public void read() {
        List<TrafficViolation> trafficViolationsList = new ArrayList<>();

        //I create an executor for the required number of threads
        ExecutorService executor = Executors.newFixedThreadPool(6);
        List<CompletableFuture<List<TrafficViolation>>> futureList = new ArrayList<>();

        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(resourceFolder);
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (bufferedReader.ready()) {
                String path = bufferedReader.readLine().trim();

                //I create a CompletableFuture and call parse asynchronously for each source file
                futureList.add(CompletableFuture.supplyAsync(() ->
                        staxParser.parse(resourceFolder + "/" + path), executor));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        executor.shutdown();

        //I get the result of each CompletableFuture and form the final data array
        futureList.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList())
                .forEach(trafficViolationsList::addAll);

        if(!trafficViolationsList.isEmpty()) jacksonWriter.write(sortViolations(trafficViolationsList), urlOutput);
    }

    private List<TrafficViolation> sortViolations(List<TrafficViolation> trafficViolationsList){
        return trafficViolationsList.stream()
                .collect(Collectors.toMap(TrafficViolation::getType, TrafficViolation::getFineAmount,
                        Double::sum,
                        LinkedHashMap::new))
                .entrySet()
                .stream()
                .map(entry -> new TrafficViolation(entry.getKey(),
                        BigDecimal.valueOf(entry.getValue())
                                .setScale(2, RoundingMode.HALF_UP)
                                .doubleValue()))
                .sorted(Comparator.comparingDouble(TrafficViolation::getFineAmount).reversed())
                .collect(Collectors.toList());
    }
}
