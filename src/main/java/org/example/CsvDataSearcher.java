package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvDataSearcher extends DataSearcher {
    public CsvDataSearcher(String path, int batchSize, IView view) {
        super(path, batchSize, view);
    }

    @Override
    public void search(String query, int colNumber) {
        List<String[]> result = new ArrayList<>();
        long totalSearchTime = 0;
        long totalBuildTime = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(_path))) {
            long buildTreeTime = System.currentTimeMillis();
            boolean end_of_file = false;

            while (!end_of_file) {
                String line;
                AvlTree tree = new AvlTree(colNumber);

                for (int i = 0; i < _batchSize; i++) {
                    if ((line = br.readLine()) == null) {
                        end_of_file = true;
                        break;
                    }
                    String[] values = line.split(",");
                    tree.insert(values);
                }

                String[] answer;
                long searchTime = System.currentTimeMillis();
                while ((answer = tree.search(query)) != null) {
                    tree.remove(answer);
                    result.add(answer);
                }
                totalSearchTime += System.currentTimeMillis() - searchTime;
            }
            totalBuildTime += System.currentTimeMillis() - buildTreeTime;

            _view.show(result, totalSearchTime,colNumber);
            System.out.println("Время на построение дерева: " + totalBuildTime);
            System.out.println("Суммарное время: " + (totalBuildTime + totalSearchTime));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (OutOfMemoryError e) {
            System.out.println("Батч с указанным размером не помещается в память! Укажите меньший размер!");
        }
    }
}
