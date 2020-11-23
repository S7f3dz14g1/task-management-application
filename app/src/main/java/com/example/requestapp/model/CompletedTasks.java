package com.example.requestapp.model;

import java.util.ArrayList;
import java.util.List;

public class CompletedTasks {

    private List<Task> taslsList;

    public List< Task > getTasls() {
        return taslsList;
    }

    public int sizeList(){
        return taslsList.size();
    }

    public void setTasls(List< Task > tasls) {
        this.taslsList = tasls;
    }

    public CompletedTasks(){
        taslsList=new ArrayList<>();
    }
}
