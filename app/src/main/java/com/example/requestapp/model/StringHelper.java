package com.example.requestapp.model;

public class StringHelper {

    public static boolean loginDateIsEmpty(String name, String password) {
        return name.isEmpty() || password.isEmpty();
    }

    public static boolean lenghPassword(String password) {
        return password.length() > 5;
    }

    public static boolean lenghDescryption(String descryption) {
        return descryption.length() < 150&&descryption.length()>1;
    }

    public static boolean taskDateIsEmpty(String type, String descryption) {
        return type.isEmpty() || descryption.isEmpty();
    }
    public static String getNickName(String email){
        return email.split("@")[0];
    }
    public static String parseIntToString(int number){
        return number+"";
    }
}
