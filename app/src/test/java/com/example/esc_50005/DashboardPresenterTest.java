package com.example.esc_50005;

import com.example.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.EditedUsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;
import com.example.esc_50005.UI.Course.FAQ.FaqPresenter;
import com.example.esc_50005.UI.Dashboard.main.DashboardContract;
import com.example.esc_50005.UI.Dashboard.main.DashboardPresenter;
import com.example.esc_50005.UI.Login.LoginContract;
import com.example.esc_50005.UI.Login.LoginPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.Mockito.verify;

/**
 * Unit tests for the implementation of {@link FaqPresenter}
 */
public class DashboardPresenterTest {

    private static ArrayList<EditedUsersInformationDO> usersInformation;

    @Mock
    private DashboardContract.View mDashboardView;

    @Mock
    private UsersInformationRemoteDataSource mLoginRepository;
    private CoursesInformationRemoteDataSource mCoursesRepository;
    private static  ArrayList<String> listOfCourses=new ArrayList<>();


    private DashboardPresenter mDashboardPresenter;

    private static ArrayList<EditedUsersInformationDO> listOfUsers=new ArrayList<>();

    @Before
    public void setupLoginPresenter() {
        MockitoAnnotations.initMocks(this);
        mDashboardPresenter = new DashboardPresenter(mLoginRepository,mCoursesRepository, mDashboardView);

    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mDashboardPresenter = new DashboardPresenter(mLoginRepository,mCoursesRepository, mDashboardView);

        // Then the presenter is set to the view
        verify(mDashboardView).setPresenter(mDashboardPresenter);
    }

    @Test
    public void showSuccessfullyLoadedCourses()
    {
        mDashboardPresenter.showSuccessfullyLoadedCourses();
        verify(mDashboardView).showSuccessfullyLoadedCourses(listOfCourses);
    }

    @Test

    public void addInvalidCourse() {
        mDashboardPresenter.addInvalidCourse();
        mDashboardView.showAddInvalidCourse();
    }

}