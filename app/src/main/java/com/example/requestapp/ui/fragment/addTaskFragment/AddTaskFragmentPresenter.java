package com.example.requestapp.ui.fragment.addTaskFragment;

import com.example.requestapp.Utils.Config;
import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.AvailableTasks;
import com.example.requestapp.model.Task;

import java.util.List;

public class AddTaskFragmentPresenter extends Lisner implements AddTaskFragmentContract.Presenter {

    private AddTaskFragment view;
    private Database database;
    private AvailableTasks availableTasks;
    private String nickName;

    public AddTaskFragmentPresenter(AddTaskFragment view){
        this.view=view;
        database=new Database();
        availableTasks=new AvailableTasks();
        database.setLisner(this);
        database.getListTask(nickName, Config.LOW);
        database.getListTask(nickName, Config.HIGH);
        database.getListTask(nickName, Config.MEDIUM);
    }

    @Override
    public void onAddTaskClicked(Task task) {
        if(isEmpty(task.getDescryption(),task.getType())){
            view.showMessage("Fill in the fields ");
        }else if(task.getDescryption().length()>150) {
            view.showMessage("Description should be shorter than 150 chars");
        }else {
            updateListTasks(task);//gdzeiś indziej
            database.addTask(task,availableTasks.sizeList(task.getType()));
            view.showMessage("Add new task");
        }
    }

    public boolean isEmpty(String type,String description){
        return (type.isEmpty() || description.isEmpty());
    }

    @Override
    public void setList(String type,List<String> listTasks) {
        availableTasks.setList(type,listTasks);
    }

    @Override
    public int getSizeList(String type) {
        return availableTasks.getList(type).size();
    }


    private void updateListTasks(Task task){
        database.getListTask(task.getNick(),task.getType());
    }
}
