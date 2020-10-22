package com.example.requestapp.ui.fragment.completedTasksFragment;

public interface CompletedTasksFragmentContract {

    interface Presenter{

        void completingTheList();

    }
    interface View{

        void showMessage(String message);
    }
}
