package com.example.requestapp.model;

import com.example.requestapp.utils.Config;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvailableTasks {

    private Map< String, List< String > > avalableTasks;

    public AvailableTasks() {
        this.avalableTasks = new HashMap<>();
        avalableTasks.put(Config.LOW, new ArrayList< String >());
        avalableTasks.put(Config.MEDIUM, new ArrayList< String >());
        avalableTasks.put(Config.HIGH, new ArrayList< String >());
    }

    public List< String > getList(String type) {
        return avalableTasks.get(type);
    }

    public void setList(String type, List< String > list) {
        avalableTasks.put(type, list);
    }

    public int sizeList(String type) {
        return avalableTasks.get(type).size();
    }

}
