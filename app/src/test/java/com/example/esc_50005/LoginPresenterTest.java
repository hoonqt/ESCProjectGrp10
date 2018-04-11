package com.example.esc_50005;

import com.example.esc_50005.Database.UsersInformation.EditedUsersInformationDO;
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

    private static ArrayList<EditedUsersInformationDO> usersInformation;

    @Mock
    private LoginContract.View mLoginView;
    private static ArrayList<EditedUsersInformationDO> userBruteForceJsonData;

    @Mock
    private UsersInformationRemoteDataSource mLoginRepository;

    private LoginPresenter mLoginPresenter;

    private static ArrayList<EditedUsersInformationDO> listOfUsers=new ArrayList<>();

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);
        mLoginPresenter = new LoginPresenter(mLoginRepository, mLoginView);

    }

//    @Test
//    public void addBruteForceCount() {
//        mLoginPresenter.addBruteForceCount("cindy","student");
//        ArrayList<EditedUsersInformationDO> listOfUsers=mLoginRepository.queryParticularUser("cindy","student");
//        EditedUsersInformationDO user=new EditedUsersInformationDO();
//        user.setBruteForceCount(Integer.toString(0));
//        user.setUserId(12.0);
//        user.setUserType("student");
//        listOfUsers.add(user);
//
//        int count=Integer.parseInt(listOfUsers.get(0).getBruteForceCount());
//
//        if(count>2)
//        {
//            verify(mLoginView).showSecurityQuestion();
//        }
//        else{
//            EditedUsersInformationDO editedUser;
//            editedUser=listOfUsers.get(0);
//            count++;
//            editedUser.setBruteForceCount(Integer.toString(count));
//            verify(mLoginRepository).addUser(editedUser);
//            loadUnsuccessfulLogin();
//        }
//    }

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
        String username="cindy";
        String userType="student";
        String password="cindyhello";
        mLoginPresenter.loadUsersFromDatabase(userId,username,userType,password);
        verify(mLoginRepository).queryAParticularUser(username,userType);
    }

    @Test
    public void loadUnsuccessfulLogin() {
        mLoginPresenter.loadUnsuccessfulLogin();
        verify(mLoginView).showUnsuccessfulLogin();
    }

    @Test
    public void loadSuccessfulLogin() {
        String number="1001792";
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

        ArrayList<EditedUsersInformationDO> listOfUsers=new ArrayList<>();
        EditedUsersInformationDO newUser=new EditedUsersInformationDO();
        newUser.setUserId("1001792");
        newUser.setSecurityAnswer("hello");
        listOfUsers.add(newUser);
        when(mLoginRepository.queryAParticularUser("1001792","cindy")).thenReturn(listOfUsers);
        userBruteForceJsonData=mLoginRepository.queryAParticularUser("1001792","student");
        String correctSecurityAnswer=userBruteForceJsonData.get(0).getSecurityAnswer();

    }

    @Test
    public void disableAccount() {
        ArrayList<EditedUsersInformationDO> listOfUsers=new ArrayList<>();
        EditedUsersInformationDO newUser=new EditedUsersInformationDO();
        newUser.setUserId("cindy");
        newUser.setSecurityAnswer("hello");
        listOfUsers.add(newUser);

        when(mLoginRepository.queryAParticularUser("1001792","student")).thenReturn(listOfUsers);
        userBruteForceJsonData=mLoginRepository.queryAParticularUser("1001792","student");
        EditedUsersInformationDO editedUser=userBruteForceJsonData.get(0);
        mLoginView.showAccountLockedOut();
    }

}