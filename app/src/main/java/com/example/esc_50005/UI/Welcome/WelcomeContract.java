package com.example.esc_50005.UI.Welcome;

import android.content.Context;

import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface WelcomeContract {

    interface Presenter extends BasePresenter {
        void loadSignUp();
        void loadSignIn();
    }
    interface View extends BaseView <Presenter> {
        void showSignUp();
        void showSignIn();
    }
}
