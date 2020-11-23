package com.example.requestapp.ui.fragment.profileFragment;

import android.net.Uri;

import com.example.requestapp.model.CompletedTasks;
import com.example.requestapp.model.Task;
import com.example.requestapp.utils.Config;
import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.AvailableTasks;
import com.example.requestapp.model.KeyHelper;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragmentPresenter extends Lisner implements ProfileFragmentContract.Presenter {

    private ProfileFragmentContract.View view;
    private Database database;
    private AvailableTasks availableTasks;
    private CompletedTasks completedTasks;
    private String nickName;

    public ProfileFragmentPresenter(ProfileFragmentContract.View view) {
        this.view = view;
        database = new Database();
        database.setLisner(this);
        availableTasks = new AvailableTasks();
        completedTasks=new CompletedTasks();
        nickName = database.getUserNick();
    }

    @Override
    public void onImageClicked(Uri uri) {
        database.uploadImage(uri, KeyHelper.GenerateKey());
    }

    @Override
    public void updatesData() {
        view.onProcessStart();
        updateListTasks(nickName);
        updateUserImage(nickName);
        updateLastCompletedTask();
    }

    private void updateLastCompletedTask() {
        database.getListCompletedTask();
    }

    private void updateUserImage(String nickName) {
        database.getKeyUserImage(nickName);
    }

    @Override
    public void onSignOutClicked() {
        database.signOut();
        view.showSignOutMessageToast();
    }

    private void updateListTasks(String nickName) {
        database.getListAvilableTask(nickName, Config.LOW);
        database.getListAvilableTask(nickName, Config.MEDIUM);
        database.getListAvilableTask(nickName, Config.HIGH);
    }

    private void setSizeAvalableTasks(String nickName) {
        view.upDateTextHighPiority(availableTasks.sizeList(Config.HIGH) + "");
        view.upDateTextLowPiority(availableTasks.sizeList(Config.LOW) + "");
        view.upDateTextMediumPiority(availableTasks.sizeList(Config.MEDIUM) + "");
        view.updateNickName(nickName);
    }

    @Override
    public void setList(String type, List< String > listTasks) {
        availableTasks.setList(type, listTasks);
        setSizeAvalableTasks(nickName);
    }

    @Override
    public void getKeyImageUser(String key) {
        if(key==null)
            view.setDefaultImage();
        else{
            database.getUserImage(key);
        }
        view.onProcessEnd();
    }

    @Override
    public void setUriImageUSer(Uri uri) {
        view.upDateImage(uri);
    }

    @Override
    public void setCommpletetTaskList(List< Task > taskList) {
        completedTasks.setTasls(taskList);
        List<Task> task=new ArrayList<>();
        if(taskList.size()!=0)
            task.add(completedTasks.getTasls().get(completedTasks.sizeList()-1));
        view.setLastCompletedTask(task);
    }
}
