package ua.yakubovskiy.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {

    private final String urlInput;

    private final String urlOutput;

    private final Pattern pattern = Pattern.compile("([\\w:\\-]+)(\\s*=\\s*(\"(.*?)\"))");

    private final StringBuilder resultStrings = new StringBuilder();

    public RegexParser(String urlInput, String urlOutput) {
        this.urlInput = urlInput;
        this.urlOutput = urlOutput;
    }

    public void read(){
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                             Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(urlInput))))) {
            while (reader.ready())
                parse(reader.readLine(), builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!resultStrings.isEmpty()) write();
    }

    private void parse(String lineXML, StringBuilder builder){
        builder.append(lineXML).append("\n");
        if(lineXML.contains("/")){
            Matcher matcher = pattern.matcher(builder.toString());
            String name = "";
            String surname = "";
            String surnameTag = "";
            String tag = builder.toString();
            while (matcher.find()) {
                if("name".equals(matcher.group(1))) {
                    name = matcher.group(4);
                } else if ("surname".equals(matcher.group(1))) {
                    surname = matcher.group(4);
                    surnameTag = matcher.group(0);
                }
                if(!name.isEmpty() && !surname.isEmpty()){
                    tag = tag.replaceAll(" " + surnameTag, "");
                    tag = tag.replaceAll(name, name+" "+surname);
                    name = "";
                    surname = "";
                }
            }
            resultStrings.append(tag.replaceAll("\\s*$", "")).append("\n");
            builder.setLength(0);
        }
    }

    private void write(){
        try (FileOutputStream fileOutputStream = new FileOutputStream(urlOutput);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
            writer.write(resultStrings.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
