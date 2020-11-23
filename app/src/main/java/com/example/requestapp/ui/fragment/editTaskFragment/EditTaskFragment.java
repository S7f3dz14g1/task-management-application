package com.example.requestapp.ui.fragment.editTaskFragment;

import android.app.AlertDialog;
import android.app.Dialog;
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
    public Dialog onCreateDialog(final Bundle saveInstanceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.fragment_edit_task,null);
        builder.setView(view);
        builder.setTitle("Edit task");
        builder.setCancelable(true);
        initComponent(view);
        setConponents();
        setPresenter();
         Bundle bundle=getArguments();
         final String key=bundle.getString("type");
         final String descryption=bundle.getString("descryption");
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickedFinish(new Task(key,
                        descryption));
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onClickedEdit(new Task(key,desryption.getText().toString()),desryption.getText().toString());
            }
        });
        return builder.create();
    }

    private void setPresenter() {
        presenter=new EditTaskFragmentPresenter(this);
    }

    private void setConponents() {
        Bundle bundle=getArguments();
        if(bundle.getString("type").startsWith("Low")){
            type.setImageResource(R.drawable.ic_low_priority);
        }else if(bundle.getString("type").startsWith("Medium")){
            type.setImageResource(R.drawable.ic_medium_priority);
        }else{
            type.setImageResource(R.drawable.ic_high_priority);
        }
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
    public void lockEditTextDescryption() {
        desryption.setEnabled(true);
    }

    @Override
    public void unlockEditTextDescryption() {
        desryption.setEnabled(false);
    }

    @Override
    public void lockButtonSave() {
        edit.setEnabled(true);
    }

    @Override
    public void unlockButtonSave() {
        edit.setEnabled(false);
    }

    @Override
    public void changeNameButton(String name) {
        edit.setText(name);
    }

    @Override
    public void closeFragment() {
        getActivity().getSupportFragmentManager().popBackStack();
    }


}
