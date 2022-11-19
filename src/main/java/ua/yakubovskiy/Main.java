package ua.yakubovskiy;

import ua.yakubovskiy.task1.RegexParser;

import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

       /* String testHtml = "<persons> <person name=\"Іван\" " +
                "surname = \"Котляревський\" " +
                "birthDate=\"09.09.1769\" />";
        Pattern attValueDoubleQuoteOnly = Pattern.compile("([\\w:\\-]+)(\\s*=\\s*(\"(.*?)\"))");

        Matcher m = attValueDoubleQuoteOnly.matcher(testHtml);

        String name = "";
        String surname = "";
        while (m.find()) {
            if(m.group(0).startsWith("n")) {
                name = m.group(0);
            } else if (m.group(0).startsWith("s")) {
                surname = m.group(0);
            }
        }
        System.out.println(name+" "+surname);*/


       task1();
    }

    public static void task1(){
       Scanner scanner = new Scanner(System.in);
        System.out.println("print urlInputXML");
        String urlInputXML = scanner.nextLine();
        System.out.println("print urlOutputXML");
        String urlOutputXML = scanner.nextLine();

        RegexParser parser = new RegexParser(urlInputXML, urlOutputXML);
        parser.parse();
    }
}