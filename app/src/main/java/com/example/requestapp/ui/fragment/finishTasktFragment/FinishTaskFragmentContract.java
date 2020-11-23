package com.example.requestapp.ui.fragment.finishTasktFragment;

public interface FinishTaskFragmentContract {
    interface Presenter{

        void onClickedDoItAgain(String piority,String descryption);

    }
    interface View{

        void showMessage(String message);

        void closeFragment();

    }
}
