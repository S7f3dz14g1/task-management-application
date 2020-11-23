package com.example.requestapp.ui.fragment.editTaskFragment;

import com.example.requestapp.model.Task;

public interface EditTaskFragmentContract {
    interface Presenter{

        void onClickedEdit(Task task,String descryption);

        void onClickedFinish(Task task);
    }
    interface View{

        void showMessage(String message);

        void lockEditTextDescryption();

        void unlockEditTextDescryption();

        void lockButtonSave();

        void unlockButtonSave();

        void changeNameButton(String name);

        void closeFragment();

    }
}
