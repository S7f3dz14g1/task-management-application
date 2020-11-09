package com.example.requestapp.model;

public class StringHelper {

    public static boolean loginDateIsEmpty(String name,String password){
        return name.isEmpty()||password.isEmpty();
    }
    public static boolean lenghPassword(String password){
        return password.length()>5;
    }
}
