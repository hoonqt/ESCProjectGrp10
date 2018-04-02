package com.example.esc_50005.UI.Login;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.*;


public class LoginPresenter implements LoginContract.Presenter  {

    private final LoginContract.View mLoginView;
    private final UsersInformationRemoteDataSource mLoginRepository;
    ArrayList<UsersInformationDO> userInformationJsonData;
    private Context context;
    ArrayList<UsersInformationDO> bruteForceJsonData;
    private SharedPreferences userInformation;

    public LoginPresenter(@NonNull LoginContract.View contractView) {
        mLoginRepository=new UsersInformationRemoteDataSource();
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
        bruteForceJsonData=mLoginRepository.queryParticularUser(username,userType);
        int count=Integer.parseInt(bruteForceJsonData.get(0).getBruteForceCount());
        if(count>=1)
        {
            mLoginView.showSecurityQuestion();
        }
        else{
            UsersInformationDO editedUser=new UsersInformationDO();
            editedUser.setUserId(bruteForceJsonData.get(0).getUserId());
            editedUser.setPassword(bruteForceJsonData.get(0).getPassword());
            editedUser.setUserType(userType);
            editedUser.setUsername(username);
            count++;
            editedUser.setBruteForceCount(Integer.toString(count));
            mLoginRepository.addUser(editedUser);
        }
    }

    @Override
    public void verifySecurityAnswer(String answer, String userType, String username) {
        bruteForceJsonData=mLoginRepository.queryParticularUser(username,userType);
        String answerFromDB=bruteForceJsonData.get(0).getSecurityAnswer();
        if(!answerFromDB.equals(answer))
        {
            disableAccount();
        }
        else{
            mLoginView.showSuccessfulLogin();
        }
    }

    @Override
    public void disableAccount() {

        Log.i("disabling","disabling");

        String username=bruteForceJsonData.get(0).getUsername();
        String userType=bruteForceJsonData.get(0).getUserType();
        String password=bruteForceJsonData.get(0).getUsername();

        String securityAnswer=bruteForceJsonData.get(0).getUsername();
        Double userId=bruteForceJsonData.get(0).getUserId();
        UsersInformationDO editedUser=new UsersInformationDO();
        editedUser.setUsername(username);
        editedUser.setUserType(userType);
        editedUser.setPassword(password);
        editedUser.setSecurityAnswer(securityAnswer);
        editedUser.setDisabled(true);
        editedUser.setUserId(userId);
        Log.i("username 111",username);
        mLoginRepository.addUser(editedUser);
        mLoginView.showAccountLockedOut();
    }

    public void loadUsersFromDatabase(Context context)
    {
        userInformation = PreferenceManager.getDefaultSharedPreferences(context);
        userInformationJsonData=mLoginRepository.queryParticularUser(userInformation.getString("Username",""),userInformation.getString("UserType",""));
        checkIfLoginIsValid(userInformationJsonData,userInformation.getString("Password",""));
    }

    @Override
    public void loadSuccessfulLogin() {
        Log.i("loadSuccessfulLogin","loadSuccessfulLogin");
        try{
            mLoginView.showSuccessfulLogin();
        }
        catch(Exception ex)
        {

        }
    }


    public void checkIfLoginIsValid(ArrayList<UsersInformationDO> userInformationJsonData, String password){

        if(userInformationJsonData.get(0).getDisabled().toString().equals("true"))
        {
            loadAccountLockedOut();
        }
        else if(userInformationJsonData.size()!=0 && userInformationJsonData.get(0).getPassword().equals(password))
        {
            loadSuccessfulLogin();
        }
        else{
            addBruteForceCount(userInformationJsonData.get(0).getUsername(),userInformationJsonData.get(0).getUserType());
            loadUnsuccessfulLogin();
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
