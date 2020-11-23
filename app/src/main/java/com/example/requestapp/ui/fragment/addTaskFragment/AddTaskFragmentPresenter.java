package com.example.requestapp.ui.fragment.addTaskFragment;

import com.example.requestapp.utils.Config;
import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.AvailableTasks;
import com.example.requestapp.model.StringHelper;
import com.example.requestapp.model.Task;

import java.util.List;

public class AddTaskFragmentPresenter extends Lisner implements AddTaskFragmentContract.Presenter {

    private AddTaskFragmentContract.View view;
    private Database database;
    private AvailableTasks availableTasks;

    public AddTaskFragmentPresenter(AddTaskFragmentContract.View view) {
        this.view = view;
        database = new Database();
        availableTasks = new AvailableTasks();
        database.setLisner(this);
        setAvailableTasks();
    }

    @Override
    public void onAddTaskClicked(Task task) {
        if (StringHelper.taskDateIsEmpty(task.getDescryption(), task.getType())) {
            view.onAddedFailureToast(Config.MESSAGE_EMPTY_FIELDS);
        } else if (!StringHelper.lenghDescryption(task.getDescryption())) {
            view.onAddedFailureToast(Config.MESSAGE_TO_LONG_DESCRIPTION);
        } else {
            onAddedSuccessful(task);
        }
    }

    private void setAvailableTasks() {
        database.getListAvilableTask(database.getUserNick(), Config.LOW);
        database.getListAvilableTask(database.getUserNick(), Config.HIGH);
        database.getListAvilableTask(database.getUserNick(), Config.MEDIUM);
    }

    private void onAddedSuccessful(Task task) {
        task.setNick(database.getUserNick());
        updateListTasks(task);
        database.addAvailableTask(task, availableTasks.sizeList(task.getType()));
        view.onAddedSuccessfulToast(Config.TITLE_WINDOW_ADD_TASK);
    }

    @Override
    public void setList(String type, List< String > listTasks) {
        availableTasks.setList(type, listTasks);
    }

    @Override
    public int getSizeList(String type) {
        return availableTasks.getList(type).size();
    }

    private void updateListTasks(Task task) {
        database.getListAvilableTask(task.getNick(), task.getType());
    }
}
