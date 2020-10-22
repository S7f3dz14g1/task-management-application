package com.example.requestapp.base;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class BasePresenter {

    void onCreate(final Bundle savedInstanceState){
    }

    void onResume(){

    }
    void onPause(){

    }
    void onSaveInstanceState(@NonNull Bundle outState){

    }
    void onDestroy(){

    }
    void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){

    }
    void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){

    }
}
