package com.example.requestapp.ui.fragment.completedTasksFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.requestapp.R;

public class CompletedTasksFragment extends Fragment implements CompletedTasksFragmentContract.View{

    private CompletedTasksFragmentContract.Presenter presenter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed_tasks, container, false);
        initComponents(view);
        presenter.completingTheList();
        //dodać do edytowania ?
        return view;
    }

    private void initComponents(View view) {
        recyclerView=view.findViewById(R.id.lowList_A);

        presenter=new CompletedTasksFragmentPresenter(this);

    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG);
    }
}
