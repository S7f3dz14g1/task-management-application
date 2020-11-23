package com.example.requestapp.iterator;


import android.net.Uri;

import com.example.requestapp.model.Task;

import java.util.List;

public abstract  class Lisner implements Adapter {

  @Override
  public void onSuccess() {

  }

  @Override
  public void onFailure() {

  }

 @Override
 public void setList(String type, List<String> listTasks) {

 }
 @Override
 public int getSizeList(String type){
   return 0;
 }

    @Override
    public void getKeyImageUser(String key) {
    }

    @Override
    public void setUriImageUSer(Uri uri) {
    }

    @Override
    public void setSizeCompletedList(int size) {

    }

    @Override
    public void setCommpletetTaskList(List< Task > taskList) {

    }
}
