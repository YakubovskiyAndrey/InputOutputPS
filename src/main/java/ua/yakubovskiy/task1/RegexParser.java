package ua.yakubovskiy.task1;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {

    private final String urlInput;

    private final String urlOutput;

    private final StringBuilder stringBuilder = new StringBuilder();

    public RegexParser(String urlInput, String urlOutput) {
        this.urlInput = urlInput;
        this.urlOutput = urlOutput;
    }

    public void parse(){
        StringBuilder strings = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream(urlInput);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (reader.ready()) {
                String testHtml = reader.readLine();
                strings.append(testHtml);
                if(testHtml.contains("/>")){
                    Pattern attValueDoubleQuoteOnly = Pattern.compile("([\\w:\\-]+)(\\s*=\\s*(\"(.*?)\"))");
                    Matcher m = attValueDoubleQuoteOnly.matcher(strings.toString());
                    String name = "";
                    String surname = "";
                    while (m.find()) {
                        if(m.group(0).startsWith("n")) {
                            name = m.group(0);
                        } else if (m.group(0).startsWith("s")) {
                            surname = m.group(0);
                        }
                    }
                    System.out.println(name+" "+surname);
                    strings.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
