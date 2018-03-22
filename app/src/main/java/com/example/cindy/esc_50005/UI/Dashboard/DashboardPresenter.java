package com.example.cindy.esc_50005.UI.Dashboard;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;


public class DashboardPresenter implements DashboardContract.Presenter  {

    private final DashboardContract.View mDashboardView;
    private final UsersInformationRemoteDataSource mCoursesRepository;
    ArrayList<UsersInformationDO> courseInformationJsonData;
    private SharedPreferences userInformation;

    public DashboardPresenter(@NonNull DashboardContract.View contractView) {
        mCoursesRepository=new UsersInformationRemoteDataSource();
        mDashboardView = checkNotNull(contractView, "dashboardView cannot be null!");
        mDashboardView.setPresenter(this);
    }
    @Override
    public void start()
    {
        loadCoursesFromDatabase();
    }
    @Override
    public void loadSuccessfullyLoadedCourses() {
        Log.i("loadSuccessfulLogin","loadSuccessfulLogin");
        mDashboardView.showSuccessfullyLoadedCourses();
    }

    public void showSuccessfulLogin()
    {
        mDashboardView.showSuccessfullyLoadedCourses();
    }


    public void loadUnsuccessfulLogin()
    {

    }

    @Override
    public void loadCoursesFromDatabase() {
        courseInformationJsonData=mCoursesRepository.queryUser(userInformation.getString("Username",""),userInformation.getString("Password",""),userInformation.getString("UserType",""));
        processCourses(courseInformationJsonData);
    }

    public void processCourses(ArrayList<UsersInformationDO> courseInformationJsonData)
    {

    }
}
