package ua.yakubovskiy.task1;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {

    private final String urlInput;

    private final String urlOutput;

    public RegexParser(String urlInput, String urlOutput) {
        this.urlInput = urlInput;
        this.urlOutput = urlOutput;
    }

    public void parse(){
        try (FileInputStream inputStream = new FileInputStream(urlInput);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
             FileOutputStream fileOutputStream = new FileOutputStream(urlOutput);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
            while (reader.ready()) {
                String testHtml = reader.readLine();
                Pattern attValueDoubleQuoteOnly = Pattern.compile("([\\w:\\-]+)(\\s*=\\s*(\"(.*?)\"))");
                Matcher m = attValueDoubleQuoteOnly.matcher(testHtml);

               /* writer.write(reader.readLine());
                writer.newLine();*/
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
