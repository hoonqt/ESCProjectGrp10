package com.example.esc_50005.UI.SignUp;

import android.content.Context;

import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface SignupContract {

    interface Presenter extends BasePresenter {
        void loadUnsuccessfulSignup();
        void loadSuccessfulSignup();
        void processSuccessfulSignup(String username, String password, String userType, String securityAnswer);
        void loadUsersFromDatabase(String username, String password, String userType, String securityAnswer);
        void checkIfSignupIsValid(ArrayList<UsersInformationDO> userInformationJsonData, String username, String password, String userType, String securityAnswer);
    }
    interface View extends BaseView <Presenter> {
        void showUnsuccessfulSignup();
        void showSuccessfulSignup();
    }
}
