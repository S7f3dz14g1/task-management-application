package com.example.requestapp.model;

import java.util.UUID;

public class KeyHelper {

    public static String GenerateKey(){
        return UUID.randomUUID().toString();
    }
}
