package com.example.requestapp.ui.fragment.profileFragment;

import android.net.Uri;

import com.example.requestapp.model.Task;

import java.util.List;

public interface ProfileFragmentContract {

    interface Presenter {

        void onImageClicked(Uri uri);

        void updatesData();

        void onSignOutClicked();

    }

    interface View {

        void updateNickName(String nick);

        void upDateImage(Uri uri);

        void setDefaultImage();

        void upDateTextHighPiority(String number);

        void upDateTextLowPiority(String number);

        void upDateTextMediumPiority(String number);

        void setLastCompletedTask(List< Task > task);

        void showMessage(String string);

        void showSignOutMessageToast();

        void onProcessStart();

        void onProcessEnd();
    }
}
