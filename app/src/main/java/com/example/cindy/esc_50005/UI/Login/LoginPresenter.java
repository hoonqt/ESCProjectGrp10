package com.example.cindy.esc_50005.UI.Login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformation;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.*;


public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View mLoginView;
    private final UsersInformationRemoteDataSource mLoginRepository;
    ArrayList<UsersInformationDO> userInformationJsonData;

    public LoginPresenter(@NonNull LoginContract.View contractView) {
        mLoginRepository=new UsersInformationRemoteDataSource();
        mLoginView = checkNotNull(contractView, "loginView cannot be null!");
        mLoginView.setPresenter(this);
    }
    @Override
    public void start()
    {

    }

    public boolean checkIfLoginIsValid(String username, String password, String userType){
        userInformationJsonData=mLoginRepository.queryUser(username,password,userType);
        for(UsersInformationDO user: userInformationJsonData)
        {
            if(user.getPassword().equals(password) && user.getUsername().equals(username) && user.getUserType().equals(userType))
            {
                Log.i("yay",user.getUsername());
                return true;
            }
            else{
                return false;
            }
        }
        return false;

    }

}
