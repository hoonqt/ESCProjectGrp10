package com.example.esc_50005.UI.Login;

import android.content.Context;

import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface LoginContract {

    interface Presenter extends BasePresenter {
        void checkIfLoginIsValid(ArrayList<UsersInformationDO> userInformationJsonData);
        void loadUsersFromDatabase(Context context);
        void loadUnsuccessfulLogin();
        void loadSuccessfulLogin();
    }
    interface View extends BaseView <Presenter> {
        void showSuccessfulLogin();
        void showUnsuccessfulLogin();
    }
}