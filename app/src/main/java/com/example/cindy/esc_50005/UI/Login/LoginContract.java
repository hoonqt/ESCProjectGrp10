package com.example.cindy.esc_50005.UI.Login;

import android.view.View;

import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

public interface LoginContract {

    interface Presenter extends BasePresenter {
        void checkIfLoginIsValid(String username, String password, String userType);
    }
    interface View extends BaseView <Presenter> {
        void setupLogin(View view);
    }
}
