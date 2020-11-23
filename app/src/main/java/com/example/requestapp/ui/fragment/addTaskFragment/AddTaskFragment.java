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
import com.example.requestapp.utils.Config;
import com.example.requestapp.model.Task;

public class AddTaskFragment extends AppCompatDialogFragment implements AddTaskFragmentContract.View {

    private EditText descryption;
    private RadioGroup radioGroup;
    private Button add;
    private RadioButton radioButton;
    private View view;

    private AddTaskFragmentContract.Presenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.fragment_add_task, null);
        builder.setView(view);
        builder.setTitle(Config.TITLE_WINDOW_ADD_TASK);
        builder.setCancelable(true);
        initComponent(view);
        setPresenter();
        add.setOnClickListener(addSetOnClickListener);
        return builder.create();
    }

    private void setPresenter() {
        presenter = new AddTaskFragmentPresenter(this);
    }

    private View.OnClickListener addSetOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            radioButton = view.findViewById(radioGroup.getCheckedRadioButtonId());
            presenter.onAddTaskClicked(new Task(radioButton.getText().toString(), descryption.getText().toString()));
        }
    };

    private void initComponent(View view) {
        descryption = view.findViewById(R.id.description_add);
        radioGroup = view.findViewById(R.id.type_RB);
        add = view.findViewById(R.id.add_task_buttom);
    }

    @Override
    public void onAddedSuccessfulToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAddedFailureToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }
}
