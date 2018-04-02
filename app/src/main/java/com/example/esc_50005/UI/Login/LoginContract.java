package com.example.esc_50005.UI.Login;

import android.content.Context;

import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;
import com.nexmo.client.NexmoClientException;

import java.io.IOException;
import java.util.ArrayList;

public interface LoginContract {

    interface Presenter extends BasePresenter {
        void loadUnsuccessfulLogin();
        void loadSuccessfulLogin();
        void loadUsersFromDatabase(String username, String userType, String password);
        void checkIfLoginIsValid(ArrayList<UsersInformationDO> userInformationJsonData, String password);
        void addBruteForceCount(String username, String userType);
        void verifySecurityAnswer(String answer, String userType, String username);
        void disableAccount();
        void loadAccountLockedOut();
    }
    interface View extends BaseView <Presenter> {
        void showUnsuccessfulLogin();
        void showSuccessfulLogin();
        void showSecurityQuestion();
        void showAccountLockedOut();
    }
}
