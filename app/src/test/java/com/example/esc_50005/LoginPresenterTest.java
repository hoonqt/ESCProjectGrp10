package com.example.esc_50005;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.UsersInformation;
import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;
import com.example.esc_50005.UI.Course.FAQ.FaqContract;
import com.example.esc_50005.UI.Course.FAQ.FaqPresenter;
import com.example.esc_50005.UI.Login.LoginContract;
import com.example.esc_50005.UI.Login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
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
        double number=23.0;
        mLoginPresenter.loadSuccessfulLogin(number);
        verify(mLoginView).showSuccessfulLogin(number);
    }

    @Test
    public void loadAccountLocked() {
        mLoginPresenter.loadAccountLockedOut();
        verify(mLoginView).showAccountLockedOut();
    }

    @Test
    public void verifySecurityAnswer() {

        ArrayList<UsersInformationDO> listOfUsers=new ArrayList<>();
        UsersInformationDO newUser=new UsersInformationDO();
        newUser.setUsername("cindy");
        newUser.setSecurityAnswer("hello");
        listOfUsers.add(newUser);
        when(mLoginRepository.queryParticularUser("cindy","student")).thenReturn(listOfUsers);
        userBruteForceJsonData=mLoginRepository.queryParticularUser("cindy","student");
        String correctSecurityAnswer=userBruteForceJsonData.get(0).getSecurityAnswer();

    }

    @Test
    public void disableAccount() {
        ArrayList<UsersInformationDO> listOfUsers=new ArrayList<>();
        UsersInformationDO newUser=new UsersInformationDO();
        newUser.setUsername("cindy");
        newUser.setSecurityAnswer("hello");
        listOfUsers.add(newUser);

        when(mLoginRepository.queryParticularUser("cindy","student")).thenReturn(listOfUsers);
        userBruteForceJsonData=mLoginRepository.queryParticularUser("cindy","student");
        UsersInformationDO editedUser=userBruteForceJsonData.get(0);
        mLoginView.showAccountLockedOut();
    }

}