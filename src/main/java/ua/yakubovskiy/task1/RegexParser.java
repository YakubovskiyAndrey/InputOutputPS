package ua.yakubovskiy.task1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class RegexParser {

    private final String urlInput;

    private final String urlOutput;

    public RegexParser(String urlInput, String urlOutput) {
        this.urlInput = urlInput;
        this.urlOutput = urlOutput;
    }

    public void parse(){
        try (FileInputStream inputStream = new FileInputStream(urlInput);
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (reader.ready()) {
                System.out.println(reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
