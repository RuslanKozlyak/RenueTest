package org.example;

import java.util.List;

public class ConsoleView implements IView {
    public void show(List<String[]> rows, long searchTime, int colNumber) {
        qSort(rows, 0, rows.size() - 1, colNumber);
        System.out.println("По запросу найдены следующие аэропорты:");

        for (String[] row : rows) {
            String result = "";
            for (String field : row) {
                result += " " + field;
            }
            System.out.println(result);
        }
        System.out.println("\nКоличество найденных строк: " + rows.size() + " Время, затраченное на поиск: " + searchTime);
    }

    static void qSort(List<String[]> A, int low, int high, int colNumber) {
        int i = low;
        int j = high;
        String x = A.get((low + high) >> 1)[colNumber];

        do {
            while (A.get(i)[colNumber].compareTo(x) < 0) ++i;
            while (A.get(j)[colNumber].compareTo(x) > 0) --j;
            if (i <= j) {
                String[] temp = A.get(i);
                A.set(i, A.get(j));
                A.set(j, temp);
                i++;
                j--;
            }
        } while (i < j);
        if (low < j) qSort(A, low, j, colNumber);
        if (i < high) qSort(A, i, high, colNumber);
    }

}
