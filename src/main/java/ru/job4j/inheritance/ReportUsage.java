package ru.job4j.inheritance;

public class ReportUsage {
    public static void main(String[] args) {
        JSONReport jsonreport = new JSONReport();
        System.out.println(jsonreport.generate("name", "body"));
    }
}
