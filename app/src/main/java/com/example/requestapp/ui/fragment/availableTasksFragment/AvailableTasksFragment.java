package com.example.requestapp.ui.fragment.availableTasksFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.requestapp.R;
import com.example.requestapp.RecycleViewAdapter;
import com.example.requestapp.Utils.Config;
import com.example.requestapp.model.Task;

import java.util.List;

public class AvailableTasksFragment  extends Fragment implements AvalableTasksFragmentContract.View{

    private AvalableTasksFragmentContract.Presenter presenter;
    private RecyclerView recyclerViewLow,recyclerViewMedium,recyclerViewHigh;
    private ImageView settings;
    private PopupMenu popupMenu;
    String nick;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_available_tasks, container, false);

        initComponents(view);


        return view;
    }

    private void initComponents(View view) {

        recyclerViewLow=view.findViewById(R.id.lowList_A);
        recyclerViewMedium=view.findViewById(R.id.mediumList_A);
        recyclerViewHigh=view.findViewById(R.id.highList_A);
        settings=view.findViewById(R.id.settingTastk);

        nick=getActivity().getIntent().getStringExtra(Config.NICKNAME_NODE);
        presenter = new AvalableTasksFragmentPresenter(this);
        popupMenu =new PopupMenu(getContext(),view);
    }

    @Override
    public void setListMedium(List<Task> task) {
        recyclerViewMedium.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerViewMedium.setAdapter(new RecycleViewAdapter(getContext(), task));
    }

    @Override
    public void setListHigh(List<Task> task ) {
        recyclerViewHigh.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerViewHigh.setAdapter(new RecycleViewAdapter(getContext(), task));
    }

    @Override
    public void setListLow(List<Task> task ) {
        recyclerViewLow.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerViewLow.setAdapter(new RecycleViewAdapter(getContext(), task));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
    }


    public void onClickSettings(){
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSetting();
            }
        });
    }
    private void showSetting() {

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.sett_confirm_execution:

                        return true;
                    case R.id.sett_cedit:

                        return true;
                    default:

                        return false;
                }
            }
        });
        popupMenu.inflate(R.menu.setting_task);
        popupMenu.show();
    }
}
