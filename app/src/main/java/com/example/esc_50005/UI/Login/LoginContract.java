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
        void loadUsersFromDatabase(Context context);
        void checkIfLoginIsValid(ArrayList<UsersInformationDO> userInformationJsonData);
    }
    interface View extends BaseView <Presenter> {
        void showUnsuccessfulLogin();
        void showSuccessfulLogin();
    }
}
