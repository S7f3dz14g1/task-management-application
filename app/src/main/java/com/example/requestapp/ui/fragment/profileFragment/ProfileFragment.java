package com.example.requestapp.ui.fragment.profileFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.requestapp.R;
import com.example.requestapp.adapter.RecycleViewAdapter;
import com.example.requestapp.model.Task;

import java.util.List;

public class ProfileFragment extends Fragment implements ProfileFragmentContract.View {

    private ImageView image;
    private TextView lowTW, mediumTW, highTW, nickNameTW;
    private RecyclerView recyclerView;


    private ProfileFragmentContract.Presenter presenter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pofile, container, false);
        initComponents(view);
        setPresenter();
        presenter.updatesData();
        setOnClickLisners();
        return view;
    }

    private void setOnClickLisners() {
        image.setOnClickListener(imageSetOnClickLisner);
    }

    private View.OnClickListener imageSetOnClickLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.onImageClicked();
        }
    };

    private void setPresenter() {
        presenter = new ProfileFragmentPresenter(this);
    }

    private void initComponents(View view) {
        image = view.findViewById(R.id.imageUser);
        lowTW = view.findViewById(R.id.sizeLowTask);
        mediumTW = view.findViewById(R.id.sizeMediumTask);
        highTW = view.findViewById(R.id.sizeHighTask);
        nickNameTW = view.findViewById(R.id.nickName_TV);
        recyclerView = view.findViewById(R.id.listOneTasksRV);
    }

    @Override
    public void updateNickName(String nick) {
        nickNameTW.setText(nick);
    }

    @Override
    public void upDateImage() {
        //dopisać
    }

    @Override
    public void upDateTextHighPiority(String number) {
        highTW.setText(number);
    }

    @Override
    public void upDateTextLowPiority(String number) {
        lowTW.setText(number);
    }

    @Override
    public void upDateTextMediumPiority(String number) {
        mediumTW.setText(number);
    }

    @Override
    public void showMessage(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_LONG);
    }

    @Override
    public void updateList(List< Task > task) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RecycleViewAdapter(getContext(), task));
    }
}
