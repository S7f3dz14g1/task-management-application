package com.example.requestapp.ui.fragment.availableTasksFragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.requestapp.R;
import com.example.requestapp.adapter.OnItemClickListener;
import com.example.requestapp.adapter.RecycleViewAdapter;
import com.example.requestapp.model.Task;
import com.example.requestapp.ui.fragment.editTaskFragment.EditTaskFragment;

import java.util.List;

public class AvailableTasksFragment  extends Fragment implements AvalableTasksFragmentContract.View, OnItemClickListener{

    private AvalableTasksFragmentContract.Presenter presenter;
    private RecyclerView recyclerViewLow,recyclerViewMedium,recyclerViewHigh;


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

        presenter = new AvalableTasksFragmentPresenter(this);
    }

    @Override
    public void setListMedium(List<Task> task) {
        setList(recyclerViewMedium,task);
    }

    @Override
    public void setListHigh(List<Task> task ) {
        setList(recyclerViewHigh,task);
    }

    @Override
    public void setListLow(List<Task> task ) {
        setList(recyclerViewLow,task);
    }

    private void setList(RecyclerView recycler,List<Task> task){
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recycler.setAdapter(new RecycleViewAdapter(getContext(), task,this));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
    }


    @Override
    public void onItemClick(int position, String background,String descryption) {
        //przenieść to do prezentera
        Bundle bundle=new Bundle();
        bundle.putString("possition",position+"");
        bundle.putString("type",background+"");
        bundle.putString("descryption",descryption);


        EditTaskFragment editTaskFragment=  new EditTaskFragment();
        editTaskFragment.setArguments(bundle);
        editTaskFragment.show(getActivity().getSupportFragmentManager(),"create dialog");
    }
}
