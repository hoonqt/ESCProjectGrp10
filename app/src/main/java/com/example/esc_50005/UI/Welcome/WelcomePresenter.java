package com.example.esc_50005.UI.Welcome;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;


public class WelcomePresenter implements WelcomeContract.Presenter  {

    private final WelcomeContract.View mWelcomeView;

    public WelcomePresenter(@NonNull WelcomeContract.View contractView) {
        mWelcomeView = checkNotNull(contractView, "loginView cannot be null!");
        mWelcomeView.setPresenter(this);
    }
    @Override
    public void start()
    {
//        mWelcomeView.setUpLogin();
    }


    @Override
    public void loadSignUp(){

        mWelcomeView.showSignUp();

    }

    @Override
    public void loadSignIn() {

        mWelcomeView.showSignIn();

    }
}
