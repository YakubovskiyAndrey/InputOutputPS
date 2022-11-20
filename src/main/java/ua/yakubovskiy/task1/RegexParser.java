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
                strings.append(testHtml).append("\n");
                if(testHtml.contains("/")){
                    Matcher matcher = Pattern.compile("([\\w:\\-]+)(\\s*=\\s*(\"(.*?)\"))")
                            .matcher(strings.toString());
                    String name = "";
                    String surname = "";
                    String surnameTag = "";
                    String stringsStr = strings.toString();
                    while (matcher.find()) {
                        if("name".equals(matcher.group(1))) {
                            name = matcher.group(4);
                        } else if ("surname".equals(matcher.group(1))) {
                            surname = matcher.group(4);
                            surnameTag = matcher.group(0);
                        }
                        if(!name.isEmpty() && !surname.isEmpty()){
                            stringsStr = stringsStr.replaceAll(surnameTag, "");
                            stringsStr = stringsStr.replaceAll(name, name+" "+surname);
                            name = "";
                            surname = "";
                        }
                    }
                    stringsStr = stringsStr.replaceAll("[\\t ]{2,}", " ");
                    stringsStr = stringsStr.replaceAll("\\s*$", "");
                    /*if(stringsStr.trim().startsWith("<"))
                        stringsStr = stringsStr.replaceAll("\\n", "\n  ");*/
                    stringBuilder.append(stringsStr).append("\n");
                    strings.setLength(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(!stringBuilder.isEmpty()) writeOutput();
    }

    private void writeOutput(){
        System.out.println(stringBuilder);
       /* try (FileOutputStream fileOutputStream = new FileOutputStream(urlOutput);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream))) {
            writer.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

}
