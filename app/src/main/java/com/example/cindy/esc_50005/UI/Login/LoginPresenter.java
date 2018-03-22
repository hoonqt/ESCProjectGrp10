package com.example.cindy.esc_50005.UI.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.*;


public class LoginPresenter implements LoginContract.Presenter  {

    private final LoginContract.View mLoginView;
    private final UsersInformationRemoteDataSource mLoginRepository;
    ArrayList<UsersInformationDO> userInformationJsonData;
    private Context context;
    private SharedPreferences userInformation;

    public LoginPresenter(@NonNull LoginContract.View contractView) {
        this.context=context;
        mLoginRepository=new UsersInformationRemoteDataSource();
        mLoginView = checkNotNull(contractView, "loginView cannot be null!");
        mLoginView.setPresenter(this);
    }
    @Override
    public void start()
    {
//        mLoginView.setUpLogin();
    }

    public void loadUsersFromDatabase(Context context)
    {
        userInformation = PreferenceManager.getDefaultSharedPreferences(context);
        userInformationJsonData=mLoginRepository.queryUser(userInformation.getString("Username",""),userInformation.getString("Password",""),userInformation.getString("UserType",""));
        checkIfLoginIsValid(userInformationJsonData);
    }

    @Override
    public void loadSuccessfulLogin() {
        Log.i("loadSuccessfulLogin","loadSuccessfulLogin");
        mLoginView.showSuccessfulLogin();
    }

    public void showSuccessfulLogin()
    {
        mLoginView.showSuccessfulLogin();
    }

    public void checkIfLoginIsValid(ArrayList<UsersInformationDO> userInformationJsonData){
        Log.i("checkIfLoginIsValid","checkIfLoginIsValid");
        for(UsersInformationDO user: userInformationJsonData)
        {
            if(user.getPassword().equals(userInformation.getString("Password","")) && user.getUsername().equals(userInformation.getString("Username","")) && user.getUserType().equals(userInformation.getString("UserType","")))
            {
                Log.i("successful","successful");
                loadSuccessfulLogin();
            }
            else{
                Log.i("unsuccessful","unsuccessful");
                loadUnsuccessfulLogin();
            }
        }

    }

    public void loadUnsuccessfulLogin()
    {
        mLoginView.showUnsuccessfulLogin();
    }

}
