package com.example.esc_50005.UI.Dashboard.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.CoursesInformation.CoursesInformationDO;
import com.example.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static com.google.common.base.Preconditions.checkNotNull;


public class DashboardPresenter implements DashboardContract.Presenter  {

    private final DashboardContract.View mDashboardView;
    private final UsersInformationRemoteDataSource mUsersRepository;
    private final CoursesInformationRemoteDataSource mCoursesRepository;
    ArrayList<UsersInformationDO> userCoursesInformationJsonData;
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
        userCoursesInformationJsonData=mUsersRepository.queryParticularUser(userInformation.getString("Username",""),userInformation.getString("Password",""),userInformation.getString("UserType",""));
        processCoursesForUsers(userCoursesInformationJsonData);
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
        listOfCourses=new ArrayList<>();
        for(int i=0;i<userCoursesInformationJsonData.get(0).getCourseIds().size();i++)
        {
            String course=userCoursesInformationJsonData.get(0).getCourseIds().get(i)+ " " + userCoursesInformationJsonData.get(0).getCourseNames().get(i);
            listOfCourses.add(course);
        }
    }

    public void queryCourseBeforeAddingProfessor(Double courseId, String courseName)
    {
        coursesInformationJsonData=mCoursesRepository.queryCourses(courseId);
        checkIfCourseIsValid(coursesInformationJsonData,courseId,courseName);
    }

    public void queryCourseBeforeAddingStudent(Double courseId)
    {
        coursesInformationJsonData=mCoursesRepository.queryCourses(courseId);
        checkIfCourseIsValid(coursesInformationJsonData,courseId, "" );
    }

    public void checkIfCourseIsValid(ArrayList<CoursesInformationDO> coursesInformationJsonData, Double courseId, String courseName)
    {
        switch (userInformation.getString("UserType",""))
        {
            case "professor":
                if(coursesInformationJsonData.size()==0)
                {
                    addValidCourseProfessor(courseId,courseName);
                }
                else{
                    addInvalidCourseProfessor();
                }
                break;
            case "student":
                if(coursesInformationJsonData.size()!=0)
                {
                    addValidCourseStudent(courseId,coursesInformationJsonData.get(0).getCourseName());
                }
                else{
                    addInvalidCourseStudent();
                }
                break;
        }
    }

    public void addInvalidCourseProfessor()
    {
        mDashboardView.showAddInvalidCourse();
    }
    public void addInvalidCourseStudent()
    {
        mDashboardView.showAddInvalidCourse();
    }

    public void addValidCourseProfessor(Double courseId,String courseName)
    {
        mCoursesRepository.addCourse(courseId,courseName,null);
        CoursesInformationDO newCourse= new CoursesInformationDO();
        UsersInformationDO updateUser=userCoursesInformationJsonData.get(0);

        if(updateUser.getCourseIds()!=null && updateUser.getCourseNames()!=null)
        {
            updateUser.getCourseIds().add(Double.toString(courseId));
            updateUser.getCourseNames().add(courseName);
        }
        else{
            List<String> listOfCourseIds=new ArrayList<>();
            listOfCourseIds.add(Double.toString(courseId));
            List<String> listOfCourseNames=new ArrayList<>();
            listOfCourseNames.add(courseName);

            updateUser.setCourseIds(listOfCourseIds);
            updateUser.setCourseNames(listOfCourseNames);
        }

        mUsersRepository.addUser(updateUser);
        mDashboardView.showAddInvalidCourse();
    }

    public void addValidCourseStudent(Double courseId, String courseName)
    {
        UsersInformationDO updateUser=userCoursesInformationJsonData.get(0);
        if(updateUser.getCourseIds().contains(Double.toString(courseId)))
        {
            Log.i("invalid","invalid");
            mDashboardView.showAddInvalidCourse();
        }
        else{
            if(updateUser.getCourseIds()!=null && updateUser.getCourseNames()!=null)
            {
                updateUser.getCourseIds().add(Double.toString(courseId));
                updateUser.getCourseNames().add(courseName);
            }
            else{
                List<String> listOfCourseIds=new ArrayList<>();
                listOfCourseIds.add(Double.toString(courseId));
                List<String> listOfCourseNames=new ArrayList<>();
                listOfCourseNames.add(courseName);

                updateUser.setCourseIds(listOfCourseIds);
                updateUser.setCourseNames(listOfCourseNames);
            }

            mUsersRepository.addUser(updateUser);
            if(coursesInformationJsonData.get(0).getListOfStudents()==null)
            {
                List<String> listOfStudentIds=new ArrayList<>();
                listOfStudentIds.add(Double.toString(updateUser.getUserId()));
                mCoursesRepository.addCourse(courseId,courseName,listOfStudentIds);

            }
            else{
                coursesInformationJsonData.get(0).getListOfStudents().add(Double.toString(updateUser.getUserId()));
                mCoursesRepository.addCourse(courseId,courseName,coursesInformationJsonData.get(0).getListOfStudents());
            }
            mDashboardView.showAddValidNewCourse();
        }

    }
}
