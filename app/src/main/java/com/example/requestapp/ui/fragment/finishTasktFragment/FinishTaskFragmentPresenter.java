package com.example.requestapp.ui.fragment.finishTasktFragment;

import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.AvailableTasks;
import com.example.requestapp.model.Task;
import com.example.requestapp.utils.Config;

import java.util.List;

public class FinishTaskFragmentPresenter extends Lisner implements FinishTaskFragmentContract.Presenter{

    private FinishTaskFragmentContract.View view;
    private Database database;
    private AvailableTasks availableTasks;


    public FinishTaskFragmentPresenter(FinishTaskFragmentContract.View view){
        this.view=view;
        database=new Database();
        availableTasks=new AvailableTasks();
        database.setLisner(this);
        database.getListAvilableTask(database.getUserNick(), Config.LOW);
        database.getListAvilableTask(database.getUserNick(),Config.MEDIUM);
        database.getListAvilableTask(database.getUserNick(),Config.HIGH);

    }
    @Override
    public void onClickedDoItAgain(String piority,String descryption) {

        database.addAvailableTask(new Task(database.getUserNick(),piority,descryption),availableTasks.sizeList(piority));
        view.showMessage("Ponownie dodano task :D");
        view.closeFragment();
    }

    @Override
    public void setList(String type, List< String > listTasks) {
        availableTasks.setList(type, listTasks);
    }
}
