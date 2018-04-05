package com.example.esc_50005.UI.SignUp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.UsersInformation.EditedUsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;


public class SignupPresenter implements SignupContract.Presenter  {

    private final SignupContract.View mSignupView;
    private final UsersInformationRemoteDataSource mLoginRepository;
    ArrayList<EditedUsersInformationDO> userInformationJsonData;

    public SignupPresenter(@NonNull SignupContract.View contractView) {
        mLoginRepository=new UsersInformationRemoteDataSource();
        mSignupView = checkNotNull(contractView, "loginView cannot be null!");
        mSignupView.setPresenter(this);
    }
    @Override
    public void start()
    {
//        mSignupView.setUpLogin();
    }

    @Override
    public void loadUnsuccessfulSignup() {
        mSignupView.showUnsuccessfulSignup();
    }

    @Override
    public void loadSuccessfulSignup() {
        mSignupView.showSuccessfulSignup();
    }

    @Override
    public void processSuccessfulSignup(String userId, String fullName, String password, String userType, String securityAnswer){

        EditedUsersInformationDO newUser =new EditedUsersInformationDO();
        newUser.setUserId(userId);
        newUser.setFullName(fullName);
        newUser.setPassword(password);
        newUser.setUserType(userType);
        newUser.setSecurityAnswer(securityAnswer);
        newUser.setDisabled(false);
        newUser.setBruteForceCount("0");
        mLoginRepository.addUser(newUser);

        loadSuccessfulSignup();

    }

    public void loadUsersFromDatabase(String userId, String password, String fullName, String userType, String securityAnswer)
    {

        userInformationJsonData=mLoginRepository.queryAParticularUser(userId);
        checkIfSignupIsValid(userInformationJsonData,userId, fullName, password,userType,securityAnswer);
    }

    @Override
    public void checkIfSignupIsValid(ArrayList<EditedUsersInformationDO> userInformationJsonData, String userId, String fullName, String password, String userType, String securityAnswer ) {
        if(userInformationJsonData.size()==0)
        {
            processSuccessfulSignup(userId, fullName, password, userType, securityAnswer);
        }
        else{
            loadUnsuccessfulSignup();
        }

    }

}
