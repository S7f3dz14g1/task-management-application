package com.example.requestapp.ui.fragment.editTaskFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.requestapp.R;
import com.example.requestapp.model.Task;

public class EditTaskFragment extends AppCompatDialogFragment implements EditTaskFragmentContract.View{

    private ImageView type;
    private EditText desryption;
    private Button edit,finish;

    private EditTaskFragmentPresenter presenter;

    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.fragment_edit_task,null);
        builder.setView(view);
        builder.setTitle("Edit task");
        builder.setCancelable(true);
        initComponent(view);
        setConponents();
        return builder.create();
    }

    private void setConponents() {
        Bundle bundle=getArguments();
        Drawable.createFromPath(bundle.getString("type"));
        if(bundle.getString("type").startsWith("Matrix{[1.0677507, 0.0, 0.0][0.0, 1.0677507, -44.0][0.0, 0.0, 1.0]}")){
            type.setImageResource(R.drawable.ic_low_priority);
        }else if(bundle.getString("type").startsWith("Matrix{[1.0706521, 0.0, 0.0][0.0, 1.0706521, -44.0][0.0, 0.0, 1.0]}")){
            type.setImageResource(R.drawable.ic_medium_priority);
        }else{
            type.setImageResource(R.drawable.ic_high_priority);
        }
        System.out.println(R.drawable.ic_low_priority);
        System.out.println(bundle.getString("type"));
        desryption.setText(bundle.getString("descryption"));
    }

    private void initComponent(View view) {
        type=view.findViewById(R.id.imageEditTask);
        desryption=view.findViewById(R.id.descryptionEditTask);
        edit=view.findViewById(R.id.editEditText);
        finish=view.findViewById(R.id.finishEditText);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(),message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void lockEdits() {
        desryption.setFocusable(false);
    }

    @Override
    public void unlockEdists() {
        desryption.setFocusable(true);
    }

    @Override
    public void changeNameButton(String name) {
        edit.setText(name);
    }
}
