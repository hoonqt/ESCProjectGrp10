package com.example.esc_50005;

import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;
import com.example.esc_50005.UI.Course.FAQ.FaqPresenter;
import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsContract;
import com.example.esc_50005.UI.Course.FAQ.session.main.SessionsPresenter;
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
public class CoursesSessionPresenterTest {

    private static ArrayList<UsersInformationDO> usersInformation;

    @Mock
    private SessionsContract.View mSessionView;

    @Mock
    private UsersInformationRemoteDataSource mLoginRepository;

    private SessionsPresenter mSessionsPresenter;

    private static ArrayList<UsersInformationDO> listOfUsers=new ArrayList<>();

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);
        mSessionsPresenter = new LoginPresenter(mLoginRepository, mSessionView);
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
        mSessionsPresenter.addBruteForceCount("cindy","student");
        ArrayList<UsersInformationDO> listOfUsers=mLoginRepository.queryParticularUser("cindy","student");
        UsersInformationDO user=new UsersInformationDO();
        user.setBruteForceCount(Integer.toString(0));
        user.setUserId(12.0);
        user.setUserType("student");
        listOfUsers.add(user);

        int count=Integer.parseInt(listOfUsers.get(0).getBruteForceCount());

        if(count>2)
        {
            verify(mSessionView).showSecurityQuestion();
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
        mSessionsPresenter = new SessionsPresenter(mLoginRepository, mSessionView);

        // Then the presenter is set to the view
        verify(mSessionView).setPresenter(mSessionsPresenter);
    }

    @Test
    public void loadUsersFromRepository() {
        String username="cindy";
        String userType="student";
        String password="cindyhello";
        mSessionsPresenter.loadUsersFromDatabase(username,userType,password);
        verify(mLoginRepository).queryParticularUser(username,userType);
    }

    @Test
    public void loadUnsuccessfulLogin() {
        mSessionsPresenter.loadUnsuccessfulLogin();
        verify(mSessionView).showUnsuccessfulLogin();
    }

    @Test
    public void loadSuccessfulLogin() {
        mSessionsPresenter.loadSuccessfulLogin();
        verify(mSessionView).showSuccessfulLogin();
    }

    @Test
    public void loadAccountLocked() {
        mSessionsPresenter.loadAccountLockedOut();
        verify(mSessionView).showAccountLockedOut();
    }

    @Test
    public void disableAccount() {
        mSessionsPresenter.disableAccount();

        ArrayList<UsersInformationDO> editedUser;
        editedUser=mLoginRepository.queryParticularUser("cindy","student");
        editedUser.get(0).setDisabled(true);

        mLoginRepository.addUser(editedUser.get(0));
        mLoginRepository.addUser(editedUser.get(0));

        verify(mSessionView).showAccountLockedOut();
    }

}