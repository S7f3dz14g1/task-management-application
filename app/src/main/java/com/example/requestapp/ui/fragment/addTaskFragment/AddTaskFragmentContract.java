package com.example.requestapp.ui.fragment.addTaskFragment;

import com.example.requestapp.model.Task;

public interface AddTaskFragmentContract {

    interface Presenter{

        void onAddTaskClicked(Task task);

    }
    interface View{

        void showMessage(String message);

    }

}
