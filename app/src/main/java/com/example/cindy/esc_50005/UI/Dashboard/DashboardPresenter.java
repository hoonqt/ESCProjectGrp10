package com.example.cindy.esc_50005.UI.Dashboard;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.CoursesInformation.CoursesInformationDO;
import com.example.cindy.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformation;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class DashboardPresenter implements DashboardContract.Presenter  {

    private final DashboardContract.View mDashboardView;
    private final UsersInformationRemoteDataSource mUsersRepository;
    private final CoursesInformationRemoteDataSource mCoursesRepository;
    ArrayList<UsersInformationDO> usersCoursesInformationJsonData;
    ArrayList<CoursesInformationDO> coursesInformationJsonData;
    private SharedPreferences userInformation;
    private static  ArrayList<String> listOfCourses=new ArrayList<>();



    public DashboardPresenter(@NonNull DashboardContract.View contractView) {
        Log.i("DashBoardPresenter","DashBoardPresenter");
        mUsersRepository=new UsersInformationRemoteDataSource();
        mCoursesRepository=new CoursesInformationRemoteDataSource();
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
        usersCoursesInformationJsonData=mUsersRepository.queryParticularUser(userInformation.getString("Username",""),userInformation.getString("Password",""),userInformation.getString("UserType",""));
        processCoursesForUsers(usersCoursesInformationJsonData);
    }

    public void processCoursesForUsers(ArrayList<UsersInformationDO> usersCoursesInformationJsonData)
    {
        for(UsersInformationDO user: usersCoursesInformationJsonData)
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
        for(int i=0;i<usersCoursesInformationJsonData.get(0).getCourseIds().size();i++)
        {
            String course=usersCoursesInformationJsonData.get(0).getCourseIds().get(i)+ " " + usersCoursesInformationJsonData.get(0).getCourseNames().get(i);
            listOfCourses.add(course);
        }
    }

    public void addNewCourseProfessor(Double courseId,String courseName)
    {
        mCoursesRepository.addCourse(courseId,courseName);
    }

    public boolean queryCourseBeforeAdding(Double courseId,String courseName)
    {
        coursesInformationJsonData=mCoursesRepository.queryCourses(courseId,courseName);
        boolean result=checkIfCourseIsValid(coursesInformationJsonData,courseId,courseName);

        return result;
    }

    public boolean checkIfCourseIsValid(ArrayList<CoursesInformationDO> coursesInformationJsonData, Double courseId, String courseName)
    {
        for(CoursesInformationDO course: coursesInformationJsonData)
        {
            if(course.getCourseID().equals(courseId) && course.getCourseName().equals(courseName)){
                return false;
            }
            else{
                addNewCourseProfessor(courseId,courseName);
                return true;
            }
        }
        return false;
    }
}
