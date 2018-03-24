package com.example.cindy.esc_50005.UI.Dashboard;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformation;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class DashboardPresenter implements DashboardContract.Presenter  {

    private final DashboardContract.View mDashboardView;
    private final UsersInformationRemoteDataSource mCoursesRepository;
    ArrayList<UsersInformationDO> courseInformationJsonData;
    private SharedPreferences userInformation;
    private static Context context;
    private static  ArrayList<String> listOfCourses=new ArrayList<>();



    public DashboardPresenter(@NonNull DashboardContract.View contractView) {
        Log.i("DashBoardPresenter","DashBoardPresenter");
        mCoursesRepository=new UsersInformationRemoteDataSource();
        mDashboardView = checkNotNull(contractView, "dashboardView cannot be null!");
        mDashboardView.setPresenter(this);
    }

    @Override
    public void start()
    {
//        Log.i("start","start");
//        mDashboardView.attemptLoadCourses();
    }

    public void showSuccessfullyLoadedCourses()
    {
        mDashboardView.showSuccessfullyLoadedCourses(listOfCourses);
    }


    public void loadUnsuccessfully()    {

    }

    @Override
    public void loadCoursesFromDatabase(Context context) {
        userInformation = PreferenceManager.getDefaultSharedPreferences(context);
        courseInformationJsonData=mCoursesRepository.queryUser(userInformation.getString("Username",""),userInformation.getString("Password",""),userInformation.getString("UserType",""));
        Log.i("user information",Integer.toString(courseInformationJsonData.get(0).getCourseIds().size()));
        processCourses(courseInformationJsonData);
    }

    public void processCourses(ArrayList<UsersInformationDO> courseInformationJsonData)
    {
        for(UsersInformationDO user: courseInformationJsonData)
        {
            if(user.getPassword().equals(userInformation.getString("Password","")) && user.getUsername().equals(userInformation.getString("Username","")) && user.getUserType().equals(userInformation.getString("UserType",""))){
                generateListOfCourses();
                showSuccessfullyLoadedCourses();
            }
            else{
                loadUnsuccessfully();
            }
        }
    }

    public void generateListOfCourses()
    {
        for(int i=0;i<courseInformationJsonData.get(0).getCourseIds().size();i++)
        {
            String course=courseInformationJsonData.get(0).getCourseIds().get(i)+ " " + courseInformationJsonData.get(0).getCourseNames().get(i);
            listOfCourses.add(course);
        }
    }
}
