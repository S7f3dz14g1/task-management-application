package com.example.requestapp.iterator;

        import java.util.List;

public interface Adapter {

    void onSuccess();

    void onFailure();

    void setList(String type,List<String> listTasks);

    int getSizeList(String type);

}
