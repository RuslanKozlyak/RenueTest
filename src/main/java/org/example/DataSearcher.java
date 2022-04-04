package org.example;

public abstract class DataSearcher {
    protected String _path;
    protected int _batchSize;
    protected IView _view;

    public DataSearcher(String path, int batchSize, IView view) {
        _path = path;
        _batchSize = batchSize;
        _view = view;
    }

    public void search(String query, int colNumber) {
        System.out.println("Поиск начался");
    }
}
