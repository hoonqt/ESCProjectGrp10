package com.example.esc_50005;

import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;
import com.example.esc_50005.UI.Course.FAQ.FaqPresenter;
import com.example.esc_50005.UI.Login.LoginContract;
import com.example.esc_50005.UI.Login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * Created by tan_j on 14/3/2018.
 */

/**
 * Unit tests for the implementation of {@link FaqPresenter}
 */
public class DashboardPresenterTest {

    private static ArrayList<UsersInformationDO> usersInformation;

    @Mock
    private LoginContract.View mLoginView;

    @Mock
    private UsersInformationRemoteDataSource mLoginRepository;

    private LoginPresenter mLoginPresenter;

    private static ArrayList<UsersInformationDO> listOfUsers=new ArrayList<>();

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);
        mLoginPresenter = new LoginPresenter(mLoginRepository, mLoginView);
        UsersInformationDO user=new UsersInformationDO();
        user.setBruteForceCount(Integer.toString(0));
        user.setUserId(12.0);
        user.setUserType("student");
        user.setUsername("cindy");
        listOfUsers.add(user);
        mLoginRepository.addUser(user);
//        listOfUsers=mLoginRepository.queryParticularUser("cindy","student");

    }

    @Test
    public void addBruteForceCount() {
        mLoginPresenter.addBruteForceCount("cindy","student");
        ArrayList<UsersInformationDO> listOfUsers=mLoginRepository.queryParticularUser("cindy","student");
        UsersInformationDO user=new UsersInformationDO();
        user.setBruteForceCount(Integer.toString(0));
        user.setUserId(12.0);
        user.setUserType("student");
        listOfUsers.add(user);

        int count=Integer.parseInt(listOfUsers.get(0).getBruteForceCount());

        if(count>2)
        {
            verify(mLoginView).showSecurityQuestion();
        }
        else{
            UsersInformationDO editedUser;
            editedUser=listOfUsers.get(0);
            count++;
            editedUser.setBruteForceCount(Integer.toString(count));
            verify(mLoginRepository).addUser(editedUser);
            loadUnsuccessfulLogin();
        }
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mLoginPresenter = new LoginPresenter(mLoginRepository, mLoginView);

        // Then the presenter is set to the view
        verify(mLoginView).setPresenter(mLoginPresenter);
    }

    @Test
    public void loadUsersFromRepository() {
        String username="cindy";
        String userType="student";
        String password="cindyhello";
        mLoginPresenter.loadUsersFromDatabase(username,userType,password);
        verify(mLoginRepository).queryParticularUser(username,userType);
    }

    @Test
    public void loadUnsuccessfulLogin() {
        mLoginPresenter.loadUnsuccessfulLogin();
        verify(mLoginView).showUnsuccessfulLogin();
    }

    @Test
    public void loadSuccessfulLogin() {
        //mLoginPresenter.loadSuccessfulLogin();
        //verify(mLoginView).showSuccessfulLogin();
    }

    @Test
    public void loadAccountLocked() {
        mLoginPresenter.loadAccountLockedOut();
        verify(mLoginView).showAccountLockedOut();
    }

    @Test
    public void disableAccount() {
        mLoginPresenter.disableAccount();

        ArrayList<UsersInformationDO> editedUser;
        editedUser=mLoginRepository.queryParticularUser("cindy","student");
        editedUser.get(0).setDisabled(true);

        mLoginRepository.addUser(editedUser.get(0));
        mLoginRepository.addUser(editedUser.get(0));

        verify(mLoginView).showAccountLockedOut();
    }

}