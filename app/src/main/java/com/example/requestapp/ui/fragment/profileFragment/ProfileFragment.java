package com.example.requestapp.ui.fragment.profileFragment;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.requestapp.R;
import com.example.requestapp.adapter.OnItemClickListener;
import com.example.requestapp.utils.Config;
import com.example.requestapp.adapter.RecycleViewAdapter;
import com.example.requestapp.model.Task;
import com.example.requestapp.ui.login.LoginActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.app.Activity.RESULT_OK;

public class ProfileFragment extends Fragment implements ProfileFragmentContract.View, OnItemClickListener {

    private ImageView image;
    private ImageButton signOut;
    private TextView lowTW, mediumTW, highTW, nickNameTW;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;


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
        signOut.setOnClickListener(signOutSetOnClickLisner);
    }

    private View.OnClickListener signOutSetOnClickLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            presenter.onSignOutClicked();
        }
    };
    private View.OnClickListener imageSetOnClickLisner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            setImage();
        }
    };

    private void setImage(){
        Intent intent=new Intent();
        intent.setType("image/");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK&&data.getData()!=null){
            image.setImageURI(data.getData());
            presenter.onImageClicked(data.getData());
        }
    }

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
        signOut = view.findViewById(R.id.signOutButton);
        progressBar=view.findViewById(R.id.progressBar4);
    }

    @Override
    public void updateNickName(String nick) {
        nickNameTW.setText(nick);
    }

    @Override
    public void upDateImage(Uri url) {
        Picasso.with(getContext()).load(url).into(image);
    }

    @Override
    public void setDefaultImage() {
        image.setImageResource(R.drawable.photo_placeholder);
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
    public void setLastCompletedTask(List< Task > task) {
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new RecycleViewAdapter(getContext(), task,this));
    }

    @Override
    public void showMessage(String string) {
        Toast.makeText(getContext(), string, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showSignOutMessageToast() {
        Toast.makeText(getContext(), Config.USER_LOGOUT, Toast.LENGTH_LONG).show();
        getActivity().finish();
        startActivity(new Intent(getContext(), LoginActivity.class));
    }

    @Override
    public void onProcessStart() {
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void onProcessEnd()  {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onItemClick(int position, String background, String descryption) {

    }
}
