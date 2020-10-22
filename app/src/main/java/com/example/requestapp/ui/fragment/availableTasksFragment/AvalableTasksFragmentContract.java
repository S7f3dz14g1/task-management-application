package com.example.requestapp.ui.fragment.availableTasksFragment;

import com.example.requestapp.model.Task;

import java.util.List;

public interface AvalableTasksFragmentContract {

    interface Presenter{

    }
    interface View{

        void setListMedium(List< Task > task );

        void setListHigh(List<Task> task );

        void setListLow(List<Task> task );

        void showMessage(String message);


    }
}
