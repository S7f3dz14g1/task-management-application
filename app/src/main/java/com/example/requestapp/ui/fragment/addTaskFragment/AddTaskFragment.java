package com.example.requestapp.ui.fragment.addTaskFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.example.requestapp.R;
import com.example.requestapp.Utils.Config;
import com.example.requestapp.model.Task;

public class AddTaskFragment extends AppCompatDialogFragment implements AddTaskFragmentContract.View {

    private EditText descryption;
    private RadioGroup radioGroup;
    private Button add;
    private RadioButton radioButton;
    String nick;

    private AddTaskFragmentContract.Presenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState){
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        LayoutInflater inflater=getActivity().getLayoutInflater();
        final View view=inflater.inflate(R.layout.fragment_add_task,null);
        nick=getActivity().getIntent().getStringExtra(Config.NICKNAME_NODE);
        builder.setView(view);
        builder.setTitle("Add new task");
        builder.setCancelable(true);
        initComponent(view);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton=view.findViewById(radioGroup.getCheckedRadioButtonId());
                presenter.onAddTaskClicked(new Task(getActivity().getIntent().getStringExtra(Config.NICKNAME_NODE),radioButton.getText().toString(),descryption.getText().toString()));
            }
        });

        return builder.create();
    }

    private void initComponent(View view) {
        descryption=view.findViewById(R.id.description_add);
        radioGroup=view.findViewById(R.id.type_RB);
        add=view.findViewById(R.id.add_task_buttom);

        presenter=new AddTaskFragmentPresenter(this);
    }

    @Override
    public void showMessage(String message) {
       Toast.makeText(getContext(),message,Toast.LENGTH_LONG).show();
    }

}
