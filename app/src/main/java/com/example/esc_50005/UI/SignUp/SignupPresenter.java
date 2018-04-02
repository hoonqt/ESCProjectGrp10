package com.example.esc_50005.UI.SignUp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;
import java.util.Random;

import static com.google.common.base.Preconditions.checkNotNull;


public class SignupPresenter implements SignupContract.Presenter  {

    private final SignupContract.View mSignupView;
    private final UsersInformationRemoteDataSource mLoginRepository;
    ArrayList<UsersInformationDO> userInformationJsonData;

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
    public void processSuccessfulSignup(String username, String password, String userType, String securityAnswer){

        Random random= new Random();
        int randomNumber=random.nextInt(1000);
        double userId= (double) randomNumber;
        UsersInformationDO newUser =new UsersInformationDO();
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setUserType(userType);
        newUser.setSecurityAnswer(securityAnswer);
        newUser.setUserId(userId);
        newUser.setDisabled(false);
        newUser.setBruteForceCount(Integer.toString(0));
        mLoginRepository.addUser(newUser);

//        try{
//            Thread.sleep(5);
//        }
//        catch(Exception ex)
//        {
//
//        }
        loadSuccessfulSignup();

    }

    public void loadUsersFromDatabase(String username, String password, String userType, String securityAnswer)
    {

        userInformationJsonData=mLoginRepository.queryParticularUser(username,userType);
        checkIfSignupIsValid(userInformationJsonData,username,password,userType,securityAnswer);
    }

    @Override
    public void checkIfSignupIsValid(ArrayList<UsersInformationDO> userInformationJsonData, String username, String password, String userType, String securityAnswer ) {
        if(userInformationJsonData.size()==0)
        {
            processSuccessfulSignup(username,password, userType, securityAnswer);
        }
        else{
            loadUnsuccessfulSignup();
        }

    }

}
