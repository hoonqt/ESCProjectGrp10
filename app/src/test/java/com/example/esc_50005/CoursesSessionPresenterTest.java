package com.example.esc_50005;

import com.example.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;
import com.example.esc_50005.Database.sessionsInformation.SessionsInformationRemoteDataSource;
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
    private SessionsContract.View mSessionsView;

    @Mock
    private UsersInformationRemoteDataSource mLoginRepository;
    private SessionsInformationRemoteDataSource mSessionsRepository;
    private CoursesInformationRemoteDataSource mCoursesRepository;

    private SessionsPresenter mSessionsPresenter;

    private static ArrayList<UsersInformationDO> listOfUsers=new ArrayList<>();

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);
        mSessionsPresenter = new SessionsPresenter(mSessionsRepository, mCoursesRepository,mLoginRepository,mSessionsView);

    }

    @Test
    public void loadEmptySessions()
    {
        mSessionsPresenter.loadEmptySessions();
        mSessionsView.showEmptySessions();
    }

    @Test
    public void addInvalidNewSession()
    {
        mSessionsPresenter.addInvalidNewSession();
        mSessionsView.showUnsuccessfulAddNewSession();
    }


}