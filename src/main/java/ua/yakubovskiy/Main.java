package ua.yakubovskiy;

import ua.yakubovskiy.task1.RegexParser;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        task1();
    }

    public static void task1(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("print urlInputXML");
        String urlInputXML = scanner.nextLine();
        System.out.println("print urlOutputXML");
        String urlOutputXML = scanner.nextLine();

        RegexParser parser = new RegexParser(urlInputXML, urlOutputXML);
        parser.read();
    }
}