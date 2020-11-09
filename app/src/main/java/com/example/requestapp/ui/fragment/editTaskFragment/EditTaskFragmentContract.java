package com.example.requestapp.ui.fragment.editTaskFragment;

public interface EditTaskFragmentContract {
    interface Presenter{

        void onClickedEdit();

        void onClickedFinish();
    }
    interface View{

        void showMessage(String message);

        void lockEdits();

        void unlockEdists();

        void changeNameButton(String name);

    }
}
