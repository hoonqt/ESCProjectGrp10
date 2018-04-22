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

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tan_j on 14/3/2018.
 */

/**
 * Unit tests for the implementation of {@link FaqPresenter}
 */
public class LoginPresenterTest {

    private static ArrayList<UsersInformationDO> usersInformation;

    @Mock
    private LoginContract.View mLoginView;
    private static ArrayList<UsersInformationDO> userBruteForceJsonData;

    @Mock
    private UsersInformationRemoteDataSource mLoginRepository;

    private LoginPresenter mLoginPresenter;

    private static ArrayList<UsersInformationDO> listOfUsers=new ArrayList<>();

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);
        mLoginPresenter = new LoginPresenter(mLoginRepository, mLoginView);

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
        String userId="1001792";
        String userType="student";
        String password="cindy";
        mLoginPresenter.loadUsersFromDatabase(userId,userType,password);
        verify(mLoginRepository).queryAParticularUser(userId);
    }

    @Test
    public void loadUnsuccessfulLogin() {
        mLoginPresenter.loadUnsuccessfulLogin();
        verify(mLoginView).showUnsuccessfulLogin();
    }

    @Test
    public void loadSuccessfulLogin() {
        String userId="1001792";
        String name="Cindy Ong Wen Ling";
        mLoginPresenter.loadSuccessfulLogin(userId,name);
        verify(mLoginView).showSuccessfulLogin(userId,name);

    }


    @Test
    public void verifySecurityAnswer() {

        ArrayList<UsersInformationDO> listOfUsers=new ArrayList<>();
        UsersInformationDO newUser=new UsersInformationDO();
        newUser.setUserId("1001792");
        newUser.setSecurityAnswer("hello");
        listOfUsers.add(newUser);
        when(mLoginRepository.queryAParticularUser("1001792")).thenReturn(listOfUsers);
        userBruteForceJsonData=mLoginRepository.queryAParticularUser("1001792");
        String correctSecurityAnswer=userBruteForceJsonData.get(0).getSecurityAnswer();

    }

    @Test
    public void disableAccount() {
        ArrayList<UsersInformationDO> listOfUsers=new ArrayList<>();
        UsersInformationDO newUser=new UsersInformationDO();
        newUser.setUserId("cindy");
        newUser.setSecurityAnswer("hello");
        listOfUsers.add(newUser);

        when(mLoginRepository.queryAParticularUser("1001792")).thenReturn(listOfUsers);
        userBruteForceJsonData=mLoginRepository.queryAParticularUser("1001792");
        UsersInformationDO editedUser=userBruteForceJsonData.get(0);
        mLoginView.showAccountLockedOut();
    }

}