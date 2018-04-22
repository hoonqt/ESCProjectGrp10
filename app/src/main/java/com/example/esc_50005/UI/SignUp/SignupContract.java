package com.example.esc_50005.UI.SignUp;


import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface SignupContract {

    interface Presenter extends BasePresenter {
        void loadUnsuccessfulSignup();
        void loadSuccessfulSignup();
        void processSuccessfulSignup(String userId, String fullName, String password, String userType, String securityAnswer);
        void loadUsersFromDatabase(String userId, String password, String fullName, String userType, String securityAnswer);
        void checkIfSignupIsValid(ArrayList<UsersInformationDO> userInformationJsonData, String userId, String fullName, String password, String userType, String securityAnswer);
    }
    interface View extends BaseView <Presenter> {
        void showUnsuccessfulSignup();
        void showSuccessfulSignup();
    }
}
