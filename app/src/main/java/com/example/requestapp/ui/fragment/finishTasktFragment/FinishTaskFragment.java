package com.example.requestapp.ui.fragment.finishTasktFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.requestapp.R;
import com.example.requestapp.utils.Config;

public class FinishTaskFragment extends AppCompatDialogFragment implements FinishTaskFragmentContract.View {


    private FinishTaskFragmentContract.Presenter presenter;
    private Button button;
    private TextView descryption;
    private ImageView image;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.fragment_finish_task,null);
        builder.setView(view);
        builder.setCancelable(true);
        initComponent(view);
        final Bundle bundle=getArguments();
        setConponents(bundle);

        setPresenter();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickedDoItAgain(bundle.getString("type"),bundle.getString("descryption"));
            }
        });
        return builder.create();
    }

    private void setPresenter() {
        presenter=new FinishTaskFragmentPresenter(this);
    }

    private void setConponents( Bundle bundle) {
        this.descryption.setText(bundle.getString(Config.DESCRYPTION));
        if(bundle.getString("type").startsWith("Low")){
            image.setImageResource(R.drawable.ic_low_priority);
        }else if(bundle.getString("type").startsWith("Medium")){
            image.setImageResource(R.drawable.ic_medium_priority);
        }else{
            image.setImageResource(R.drawable.ic_high_priority);
        }
    }

    private void initComponent(View view) {
        button=view.findViewById(R.id.buttonFinishTask);
        descryption=view.findViewById(R.id.descryptionFinishTask);
        image=view.findViewById(R.id.imageFinishTask);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

    @Override
    public void closeFragment() {

    }


}
