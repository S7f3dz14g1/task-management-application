package com.example.requestapp.ui.fragment.completedTasksFragment;

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
import com.example.requestapp.model.StringHelper;
import com.example.requestapp.model.Task;
import com.example.requestapp.ui.fragment.finishTasktFragment.FinishTaskFragment;
import com.example.requestapp.utils.Config;

import java.util.List;

public class CompletedTasksFragment extends Fragment implements CompletedTasksFragmentContract.View, OnItemClickListener {

    private CompletedTasksFragmentContract.Presenter presenter;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed_tasks, container, false);
        initComponents(view);
        setPresenter();
        presenter.completingTheList();

        return view;
    }

    private void setPresenter() {
        presenter=new CompletedTasksFragmentPresenter(this);
    }

    private void initComponents(View view) {
        recyclerView=view.findViewById(R.id.lowList_A);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void setTasksList(List< Task > tasksList) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RecycleViewAdapter(getContext(), tasksList, this));
    }

    @Override
    public void onItemClick(int position, String background, String descryption) {
        Bundle bundle = new Bundle();
        setStringBundle(bundle, position, background, descryption);
        FinishTaskFragment finishTaskFragment = new FinishTaskFragment();
        finishTaskFragment.setArguments(bundle);
        finishTaskFragment.show(getActivity().getSupportFragmentManager(), Config.TAG_CREATE_DIALOG_FINSH);

}

    private void setStringBundle(Bundle bundle, int position, String background, String descryption) {
        bundle.putString(Config.POSSITION, StringHelper.parseIntToString(position));
        bundle.putString(Config.TYPE, background);
        bundle.putString(Config.DESCRYPTION, descryption);
    }

}
