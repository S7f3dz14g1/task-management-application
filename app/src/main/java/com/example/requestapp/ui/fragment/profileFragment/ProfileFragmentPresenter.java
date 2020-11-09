package com.example.requestapp.ui.fragment.profileFragment;

import com.example.requestapp.Utils.Config;
import com.example.requestapp.database.Database;
import com.example.requestapp.iterator.Lisner;
import com.example.requestapp.model.AvailableTasks;
import com.example.requestapp.model.Task;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragmentPresenter extends Lisner implements ProfileFragmentContract.Presenter {

    private ProfileFragmentContract.View view;
    private Database database;
    private AvailableTasks availableTasks;
    private String nickName;

    public ProfileFragmentPresenter(ProfileFragmentContract.View view) {
        this.view = view;
        database = new Database();
        database.setLisner(this);
        availableTasks = new AvailableTasks();
        nickName=database.getUserNick();
    }

    @Override
    public void onImageClicked() {
        //zmiana zdj
        //zapisanie nowego w bazie
    }

    @Override
    public void updatesData() {
        updateListTasks(nickName);

        //sprawdzenie czy jest zapisane zdj jeśli jest to ustawić z bazy jeśli nie ma to dodać standerode
    }

    private void updateListTasks(String nickName) {
        database.getListTask(nickName, Config.LOW);
        database.getListTask(nickName, Config.MEDIUM);
        database.getListTask(nickName, Config.HIGH);
    }

    private void setSizeAvalableTasks(String nickName) {
        view.upDateTextHighPiority(availableTasks.sizeList(Config.HIGH) + "");
        view.upDateTextLowPiority(availableTasks.sizeList(Config.LOW) + "");
        view.upDateTextMediumPiority(availableTasks.sizeList(Config.MEDIUM) + "");
        view.updateNickName(nickName);
        List< Task > list = new ArrayList<>();
//        list.add(new Task("Low",availableTasks.getList("Low").get(0)));
        // view.updateList(list);//zmienić na zakończnoe
    }

    @Override
    public void setList(String type, List< String > listTasks) {
        availableTasks.setList(type, listTasks);
        setSizeAvalableTasks(nickName);
    }
}
