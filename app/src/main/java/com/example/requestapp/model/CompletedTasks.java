package com.example.requestapp.model;

import java.util.ArrayList;
import java.util.List;

public class CompletedTasks {

    private List<Task> tasls;

    public List< Task > getTasls() {
        return tasls;
    }

    public int sizeList(){
        return tasls.size();
    }
    public void setTasls(List< Task > tasls) {
        this.tasls = tasls;
    }

    public CompletedTasks(){
        tasls=new ArrayList<>();
    }
}
