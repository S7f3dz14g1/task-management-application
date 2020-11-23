package com.example.requestapp.ui.fragment.editTaskFragment;

import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.AvailableTasks;
import com.example.requestapp.model.StringHelper;
import com.example.requestapp.model.Task;
import com.example.requestapp.utils.Config;

import java.util.List;

public class EditTaskFragmentPresenter extends Lisner implements EditTaskFragmentContract.Presenter {

    private EditTaskFragmentContract.View view;
    private Database database;
    private int sizeCompletedList;
    private String descryption;
    private AvailableTasks availableTasks;
    private int index;


    public EditTaskFragmentPresenter(EditTaskFragmentContract.View view){
        this.view=view;
        database=new Database();
        database.setLisner(this);
        database.getSizeCompletedList();
        availableTasks=new AvailableTasks();
        database.getListAvilableTask(database.getUserNick(), Config.LOW);
        database.getListAvilableTask(database.getUserNick(),Config.MEDIUM);
        database.getListAvilableTask(database.getUserNick(),Config.HIGH);

    }

    @Override
    public void onClickedEdit(Task task,String descryption) {
        if(this.descryption==null||this.descryption.equals(descryption)){
            System.out.println(task.getDescryption());
            view.changeNameButton("Save");
            index=availableTasks.getList(task.getType()).indexOf(task.getDescryption());
            view.lockButtonSave();
            this.descryption=availableTasks.getList(task.getType()).get(index);
            view.lockEditTextDescryption();
        }else if(!StringHelper.lenghDescryption(descryption)){
            view.showMessage("Błędna długiść");
        }else {
            view.showMessage("Udana edycja");
            view.lockEditTextDescryption();
            view.unlockButtonSave();
            view.changeNameButton("Edit");
            task.setDescryption(descryption);
            database.addAvailableTask(task,index);
            this.descryption=null;
        }
    }

    @Override
    public void onClickedFinish(Task task) {
        task.setNick(database.getUserNick());
        database.moveAvailableTaskToCompletedTask(task,sizeCompletedList+"");
        view.closeFragment();
    }

    @Override
    public void setList(String type, List< String > listTasks) {
        availableTasks.setList(type,listTasks);
    }

    @Override
    public void setSizeCompletedList(int sizeCompletedList) {
        this.sizeCompletedList = sizeCompletedList;
    }
}
