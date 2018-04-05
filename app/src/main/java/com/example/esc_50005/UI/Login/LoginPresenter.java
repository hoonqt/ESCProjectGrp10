package com.example.esc_50005.UI.Login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.EditedUsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.*;


public class LoginPresenter implements LoginContract.Presenter  {

    private final LoginContract.View mLoginView;
    public UsersInformationRemoteDataSource mLoginRepository;

    ArrayList<EditedUsersInformationDO> userInformationJsonData;
    ArrayList<EditedUsersInformationDO> userBruteForceJsonData;

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

    public void addBruteForceCount(String userId, String fullName)
    {
        userBruteForceJsonData=mLoginRepository.queryAParticularUser(userId);
        int count=Integer.parseInt(userBruteForceJsonData.get(0).getBruteForceCount());

        if(count>2)
        {
            mLoginView.showSecurityQuestion();
        }
        else{
            EditedUsersInformationDO editedUser;
            editedUser=userBruteForceJsonData.get(0);
            count++;
            editedUser.setBruteForceCount(Integer.toString(count));
            mLoginRepository.addUser(editedUser);
            loadUnsuccessfulLogin();
        }
    }

    @Override
    public void verifySecurityAnswer(String answer, String userId, String fullName) {

        userBruteForceJsonData=mLoginRepository.queryAParticularUser(userId);
        String correctSecurityAnswer=userBruteForceJsonData.get(0).getSecurityAnswer();

        if(!correctSecurityAnswer.equals(answer))
        {
//            disableAccount();
            mLoginView.showAccountLockedOut();
        }
        else{
            mLoginView.showSuccessfulLogin(userBruteForceJsonData.get(0).getUserId());
        }
    }

    @Override
    public void disableAccount() {

        EditedUsersInformationDO editedUser;
        editedUser=userBruteForceJsonData.get(0);
        editedUser.setDisabled(true);
        
        mLoginRepository.addUser(editedUser);
        mLoginView.showAccountLockedOut();
    }

    public void loadUsersFromDatabase(String userId, String userType, String password)
    {
        userInformationJsonData=mLoginRepository.queryAParticularUser(userId);
        checkIfLoginIsValid(userInformationJsonData, password, userType);
    }

    @Override
    public void loadSuccessfulLogin(String userId) {

        mLoginView.showSuccessfulLogin(userId);
    }

    public void checkIfLoginIsValid(ArrayList<EditedUsersInformationDO> userInformationJsonData, String password, String userType){

        Log.i("size",Integer.toString(userInformationJsonData.size()));
        if(userInformationJsonData.size()==0)
        {
            loadUnsuccessfulLogin();
        }

        if(!userInformationJsonData.get(0).getUserType().equals(userType))
        {
            loadUnsuccessfulLogin();
        }
        else if(userInformationJsonData.get(0).getDisabled().toString().equals("true"))
        {
            loadAccountLockedOut();
        }
        else if(userInformationJsonData.get(0).getPassword().equals(password))
        {
            loadSuccessfulLogin(userInformationJsonData.get(0).getUserId());
        }
        else{
            addBruteForceCount(userInformationJsonData.get(0).getUserId(),userInformationJsonData.get(0).getFullName());
        }

    }

    @Override
    public void loadSignUp() {
        mLoginView.showSignUp();
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
