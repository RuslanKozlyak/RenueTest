package org.example;

import java.util.List;

public interface IView {
    void show(List<String[]> rows, long searchTime,int colNumber);
}
