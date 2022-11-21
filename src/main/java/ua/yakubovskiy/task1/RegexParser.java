package ua.yakubovskiy.task1;

import java.io.FileInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexParser {

    private final String urlInput;

    private final String urlOutput;

    private final Pattern pattern = Pattern.compile("([\\w:\\-]+)(\\s*=\\s*(\"(.*?)\"))");

    private final StringBuilder stringBuilder = new StringBuilder();

    public RegexParser(String urlInput, String urlOutput) {
        this.urlInput = urlInput;
        this.urlOutput = urlOutput;
    }

    public void read(){
        StringBuilder builder = new StringBuilder();
        try (FileInputStream inputStream = new FileInputStream(getFileFromResources(urlInput));
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            while (reader.ready())
                parse(reader.readLine(), builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!stringBuilder.isEmpty()) write();
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
            stringBuilder.append(tag.replaceAll("\\s*$", "")).append("\n");
            builder.setLength(0);
        }
    }

    private void write(){
        try (FileOutputStream fileOutputStream = new FileOutputStream(urlOutput);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = this.getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) throw new IllegalArgumentException("file is not found!");
        return new File(resource.getFile());
    }

}
