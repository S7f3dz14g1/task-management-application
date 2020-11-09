package com.example.requestapp.ui.fragment.editTaskFragment;

import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;

public class EditTaskFragmentPresenter extends Lisner implements EditTaskFragmentContract.Presenter {

    private EditTaskFragmentContract.View view;
    private Database database;


    public EditTaskFragmentPresenter(EditTaskFragmentContract.View view){
        this.view=view;
        database=new Database();
        database.setLisner(this);
    }

    @Override
    public void onClickedEdit() {
        view.lockEdits();
        view.changeNameButton("Save");
        //sprawdzenie czy ilość słów nie jest większa niż 250 słów
        //jeśli nie jest to nie wyskoczy komuniat i odblokuje się przycisk
        //jeśli jest za dużo to wyświetli komunikat i

    }

    @Override
    public void onClickedFinish() {
        //usówa element z listy  aktualnych
        //przenosi task do zakończonych
        //wyświetla komunikat
        //zablokowanie wszystkich przycisków
    }
}
