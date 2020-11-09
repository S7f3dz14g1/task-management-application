package com.example.requestapp.ui.fragment.profileFragment;

import com.example.requestapp.model.Task;

import java.util.List;

public interface ProfileFragmentContract {

    interface Presenter {

        void onImageClicked();

        void updatesData();

    }

    interface View {

        void updateNickName(String nick);

        void upDateImage();

        void upDateTextHighPiority(String number);

        void upDateTextLowPiority(String number);

        void upDateTextMediumPiority(String number);

        void showMessage(String string);

        void updateList(List< Task > task);

    }
}
