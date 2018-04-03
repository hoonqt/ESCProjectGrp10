package com.example.esc_50005.UI.Login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.*;


public class LoginPresenter implements LoginContract.Presenter  {

    private final LoginContract.View mLoginView;
    public UsersInformationRemoteDataSource mLoginRepository;

    ArrayList<UsersInformationDO> userInformationJsonData;
    ArrayList<UsersInformationDO> userBruteForceJsonData;

    public LoginPresenter(@NonNull UsersInformationRemoteDataSource usersInformationRepository, @NonNull LoginContract.View contractView) {
        mLoginRepository=usersInformationRepository;
        mLoginView = checkNotNull(contractView, "loginView cannot be null!");
        mLoginView.setPresenter(this);
    }
    @Override
    public void start()
    {
//        mLoginView.setUpLogin();
    }

    public void addBruteForceCount(String username, String userType)
    {
        userBruteForceJsonData=mLoginRepository.queryParticularUser(username,userType);
        int count=Integer.parseInt(userBruteForceJsonData.get(0).getBruteForceCount());

        if(count>2)
        {
            mLoginView.showSecurityQuestion();
        }
        else{
            UsersInformationDO editedUser;
            editedUser=userBruteForceJsonData.get(0);
            count++;
            editedUser.setBruteForceCount(Integer.toString(count));
            mLoginRepository.addUser(editedUser);
            loadUnsuccessfulLogin();
        }
    }

    @Override
    public void verifySecurityAnswer(String answer, String userType, String username) {

        userBruteForceJsonData=mLoginRepository.queryParticularUser(username,userType);
        String correctSecurityAnswer=userBruteForceJsonData.get(0).getSecurityAnswer();

        if(!correctSecurityAnswer.equals(answer))
        {
            disableAccount();
        }
        else{
            mLoginView.showSuccessfulLogin(userBruteForceJsonData.get(0).getUserId());
        }
    }

    @Override
    public void disableAccount() {

        UsersInformationDO editedUser;
        editedUser=userBruteForceJsonData.get(0);
        editedUser.setDisabled(true);
        
        mLoginRepository.addUser(editedUser);
        mLoginView.showAccountLockedOut();
    }

    public void loadUsersFromDatabase(String username, String userType, String password)
    {
        userInformationJsonData=mLoginRepository.queryParticularUser(username,userType);
        checkIfLoginIsValid(userInformationJsonData, password);
    }

    @Override
    public void loadSuccessfulLogin(Double userId) {

        mLoginView.showSuccessfulLogin(userId);
    }


    public void checkIfLoginIsValid(ArrayList<UsersInformationDO> userInformationJsonData, String password){

        if(userInformationJsonData.size()==0)
        {
            loadUnsuccessfulLogin();
        }
        else if(userInformationJsonData.get(0).getDisabled().toString().equals("true"))
        {
            loadAccountLockedOut();
        }
        else if(userInformationJsonData.get(0).getPassword().equals(password))
        {
            Log.i("password type",password);
            Log.i("password real",userInformationJsonData.get(0).getPassword());
            loadSuccessfulLogin(userInformationJsonData.get(0).getUserId());
        }
        else{
            addBruteForceCount(userInformationJsonData.get(0).getUsername(),userInformationJsonData.get(0).getUserType());
        }

    }

    public void loadUnsuccessfulLogin()
    {
        mLoginView.showUnsuccessfulLogin();
    }

    public void loadAccountLockedOut()
    {
        mLoginView.showAccountLockedOut();
    }

}
