package com.example.requestapp.ui.fragment.availableTasksFragment;


import com.example.requestapp.utils.Config;
import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.AvailableTasks;
import com.example.requestapp.model.Task;


import java.util.ArrayList;
import java.util.List;

public class AvailableTasksFragmentPresenter extends Lisner implements AvailableTasksFragmentContract.Presenter {

    private AvailableTasksFragmentContract.View view;
    private Database database;
    private AvailableTasks availableTasks;


    public AvailableTasksFragmentPresenter(AvailableTasksFragmentContract.View view) {
        this.view = view;
        database = new Database();
        database.setLisner(this);
        availableTasks = new AvailableTasks();
        setListTasks();//to zamienić na metodę w przenterze :D
    }

    private void setListTasks() {
        database.getListAvilableTask(database.getUserNick(), Config.LOW);
        database.getListAvilableTask(database.getUserNick(), Config.MEDIUM);
        database.getListAvilableTask(database.getUserNick(), Config.HIGH);
    }

    private List< Task > getTaskList(String type, List< String > desryptions) {
        List< Task > tasks = new ArrayList<>();
        for (String d : desryptions) {
            tasks.add(new Task(type, d));
        }
        return tasks;
    }

    @Override
    public void setList(String type, List< String > listTasks) {
        availableTasks.setList(type, listTasks);
        setViewList();
    }

    private void setViewList() {
        if (!availableTasks.getList(Config.LOW).isEmpty())
            view.setListLow(getTaskList(Config.LOW, availableTasks.getList(Config.LOW)));
        if (!availableTasks.getList(Config.MEDIUM).isEmpty())
            view.setListMedium(getTaskList(Config.MEDIUM, availableTasks.getList(Config.MEDIUM)));
        if (!availableTasks.getList(Config.HIGH).isEmpty())
            view.setListHigh(getTaskList(Config.HIGH, availableTasks.getList(Config.HIGH)));
    }

}
