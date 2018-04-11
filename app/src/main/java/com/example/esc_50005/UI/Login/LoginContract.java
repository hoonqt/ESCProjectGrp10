package com.example.esc_50005.UI.Login;

import android.content.Context;

import com.example.esc_50005.Database.UsersInformation.EditedUsersInformationDO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;
import com.nexmo.client.NexmoClientException;

import java.io.IOException;
import java.util.ArrayList;

public interface LoginContract {

    interface Presenter extends BasePresenter {
        void loadSignUp();
        void loadUnsuccessfulLogin();
        void loadSuccessfulLogin(String userId,String name);
        void loadUsersFromDatabase(String userId, String userType, String password);
        void checkIfLoginIsValid(ArrayList<EditedUsersInformationDO> userInformationJsonData, String password, String userType);
        void addBruteForceCount(String username, String userType);
        void verifySecurityAnswer(String answer, String userId, String fullName);
        void disableAccount();
        void loadAccountLockedOut();
    }
    interface View extends BaseView <Presenter> {
        void showUnsuccessfulLogin();
        void showSuccessfulLogin(String userId, String name);
        void showSecurityQuestion();
        void showAccountLockedOut();
        void showSignUp();
    }
}
