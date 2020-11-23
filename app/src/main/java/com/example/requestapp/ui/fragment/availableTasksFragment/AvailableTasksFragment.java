package com.example.requestapp.ui.fragment.availableTasksFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.requestapp.R;
import com.example.requestapp.model.StringHelper;
import com.example.requestapp.utils.Config;
import com.example.requestapp.adapter.OnItemClickListener;
import com.example.requestapp.adapter.RecycleViewAdapter;
import com.example.requestapp.model.Task;
import com.example.requestapp.ui.fragment.editTaskFragment.EditTaskFragment;

import java.util.List;

public class AvailableTasksFragment extends Fragment implements AvailableTasksFragmentContract.View, OnItemClickListener {

    private AvailableTasksFragmentContract.Presenter presenter;
    private RecyclerView recyclerViewLow, recyclerViewMedium, recyclerViewHigh;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_available_tasks, container, false);
        initComponents(view);
        setPresenter();
        return view;
    }

    private void setPresenter() {
        presenter = new AvailableTasksFragmentPresenter(this);
    }

    private void initComponents(View view) {
        recyclerViewLow = view.findViewById(R.id.lowList_A);
        recyclerViewMedium = view.findViewById(R.id.mediumList_A);
        recyclerViewHigh = view.findViewById(R.id.highList_A);
    }

    @Override
    public void setListMedium(List< Task > tasksList) {
        setList(recyclerViewMedium, tasksList);
    }

    @Override
    public void setListHigh(List< Task > tasksList) {
        setList(recyclerViewHigh, tasksList);
    }

    @Override
    public void setListLow(List< Task > tasksList) {
        setList(recyclerViewLow, tasksList);
    }

    private void setList(RecyclerView recycler, List< Task > tasksList) {
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recycler.setAdapter(new RecycleViewAdapter(getContext(), tasksList, this));
    }

    @Override
    public void onItemClick(int position, String background, String descryption) {
        Bundle bundle = new Bundle();
        setStringBundle(bundle, position, background, descryption);
        EditTaskFragment editTaskFragment = new EditTaskFragment();
        editTaskFragment.setArguments(bundle);
        editTaskFragment.show(getActivity().getSupportFragmentManager(), Config.TAG_CREATE_DIALOG_EDIT);
    }

    private void setStringBundle(Bundle bundle, int position, String background, String descryption) {
        bundle.putString(Config.POSSITION, StringHelper.parseIntToString(position));
        bundle.putString(Config.TYPE, background);
        bundle.putString(Config.DESCRYPTION, descryption);
    }
}
