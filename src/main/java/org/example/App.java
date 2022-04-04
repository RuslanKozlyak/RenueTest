package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws IOException {

        int colNumber = 1;
        int batchSize = 1000;

        if (args.length > 1) {
            System.out.println("Вы ввели слишком много аргументов! Введите только номер колонки для поиска!");
        } else if (args.length == 1) {
            colNumber = Integer.parseInt(args[0]) - 1;
        }

        System.out.println("Введите строку для поиска:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String query = reader.readLine();

        IView consoleView = new ConsoleView();
        DataSearcher searcher = new CsvDataSearcher("airports.csv", batchSize, consoleView);
        searcher.search(query, colNumber);
    }
}
