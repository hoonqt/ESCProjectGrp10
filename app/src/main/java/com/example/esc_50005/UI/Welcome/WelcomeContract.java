package com.example.esc_50005.UI.Welcome;


import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;


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
