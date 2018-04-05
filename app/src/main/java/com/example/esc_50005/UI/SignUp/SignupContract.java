package com.example.esc_50005.UI.SignUp;

import android.content.Context;


import com.example.esc_50005.Database.UsersInformation.EditedUsersInformationDO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface SignupContract {

    interface Presenter extends BasePresenter {
        void loadUnsuccessfulSignup();
        void loadSuccessfulSignup();
        void processSuccessfulSignup(String userId, String fullName, String password, String userType, String securityAnswer);
        void loadUsersFromDatabase(String userId, String password, String fullName, String userType, String securityAnswer);
        void checkIfSignupIsValid(ArrayList<EditedUsersInformationDO> userInformationJsonData, String userId, String fullName, String password, String userType, String securityAnswer);
    }
    interface View extends BaseView <Presenter> {
        void showUnsuccessfulSignup();
        void showSuccessfulSignup();
    }
}
