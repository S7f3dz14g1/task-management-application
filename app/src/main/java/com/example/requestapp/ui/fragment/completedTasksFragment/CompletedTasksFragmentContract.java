package com.example.requestapp.ui.fragment.completedTasksFragment;

import com.example.requestapp.model.Task;

import java.util.List;

public interface CompletedTasksFragmentContract {

    interface Presenter {

        void completingTheList();

    }

    interface View {

        void showMessage(String message);

        void setTasksList(List< Task > tasksList);
    }
}
