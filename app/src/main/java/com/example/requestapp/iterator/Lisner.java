package com.example.requestapp.iterator;


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

}
