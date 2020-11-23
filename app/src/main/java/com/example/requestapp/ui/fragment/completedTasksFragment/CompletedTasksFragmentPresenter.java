package com.example.requestapp.ui.fragment.completedTasksFragment;

import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.CompletedTasks;
import com.example.requestapp.model.Task;

import java.util.ArrayList;
import java.util.List;


public class CompletedTasksFragmentPresenter extends Lisner implements CompletedTasksFragmentContract.Presenter {

    private CompletedTasksFragmentContract.View view;
    private Database database;
    private CompletedTasks completedTasks;

    public CompletedTasksFragmentPresenter(CompletedTasksFragmentContract.View view) {
        this.view = view;
        database = new Database();
        database.setLisner(this);
        completedTasks=new CompletedTasks();
        database.getListCompletedTask();
    }


    @Override
    public void completingTheList() {
        //dodanie do isty ze wszystkimi zakończonymi zadanimi
        List< Task > comletedTasksList=new ArrayList<>();
       // setCompletedTasksList(database.getUserNick());
      //  view.setTasksList();

    }

    @Override
    public void setCommpletetTaskList(List< Task > taskList) {
        completedTasks.setTasls(taskList);
        if(!completedTasks.getTasls().isEmpty())
            view.setTasksList(taskList);
        else
            view.showMessage("Lista jest pusta");
    }
}
