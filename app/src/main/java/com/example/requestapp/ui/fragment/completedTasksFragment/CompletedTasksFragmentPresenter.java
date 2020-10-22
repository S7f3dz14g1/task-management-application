package com.example.requestapp.ui.fragment.completedTasksFragment;

import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;

public class CompletedTasksFragmentPresenter extends Lisner implements CompletedTasksFragmentContract.Presenter {

    private  CompletedTasksFragmentContract.View view;
    private Database database;

    public CompletedTasksFragmentPresenter(CompletedTasksFragmentContract.View view) {
        this.view = view;
        database=new Database();
        database.setLisner(this);
    }

    @Override
    public void completingTheList() {
        //dodanie do isty ze wszystkimi zakończonymi zadanimi
    }
}
