package com.example.esc_50005.UI.Dashboard.main;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.CoursesInformation.CoursesInformationDO;
import com.example.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class DashboardPresenter implements DashboardContract.Presenter  {

    private final DashboardContract.View mDashboardView;
    public final UsersInformationRemoteDataSource mUsersRepository;
    public final CoursesInformationRemoteDataSource mCoursesRepository;
    ArrayList<UsersInformationDO> userCoursesInformationJsonData;
    ArrayList<CoursesInformationDO> coursesInformationJsonData;
    private static  ArrayList<String> listOfCourses=new ArrayList<>();

    public DashboardPresenter(@NonNull UsersInformationRemoteDataSource usersInformationRepository,
                              @NonNull CoursesInformationRemoteDataSource coursesInformationRepository,
                              @NonNull DashboardContract.View contractView) {
        mUsersRepository=usersInformationRepository;
        mCoursesRepository=coursesInformationRepository;
        mDashboardView = checkNotNull(contractView, "dashboardView cannot be null!");
        mDashboardView.setPresenter(this);
    }

    @Override
    public void start() {
//        mDashboardView.attemptLoadCourses();
    }

    public void showSuccessfullyLoadedCourses()
    {
        mDashboardView.showSuccessfullyLoadedCourses(listOfCourses);
        mDashboardView.coursesLoaded();
    }


    public void loadUnsuccessfully()    {



    }

    public void loadEmptyView()    {

        mDashboardView.showEmptyCourses();
    }

    @Override
    public void loadCoursesFromDatabase(String userId) {
        userCoursesInformationJsonData=mUsersRepository.queryAParticularUser(userId);
        processCoursesForUsers(userCoursesInformationJsonData,userId);
    }

    public void processCoursesForUsers(ArrayList<UsersInformationDO> usersCoursesInformationJsonData, String username)
    {
            if(usersCoursesInformationJsonData.get(0).getCourseIds()==null)
                {
                    loadEmptyView();
                }

            else {
                    generateListOfCourses();
                    showSuccessfullyLoadedCourses();
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

    public void queryCourseBeforeAdding(String userType,String courseId, String courseName)
    {
        switch(userType)
        {
            case "student":
                coursesInformationJsonData=mCoursesRepository.queryCourses(courseId);
                checkIfCourseIsValid(userType,coursesInformationJsonData,courseId, "" );
                break;
            case "professor":
                coursesInformationJsonData=mCoursesRepository.queryCourses(courseId);
                checkIfCourseIsValid(userType,coursesInformationJsonData,courseId,courseName);
        }

    }

    @Override
    public void deleteCourse(String courseId, String courseName) {


        CoursesInformationDO courseToDelete=new CoursesInformationDO();
        courseToDelete.setCourseId(courseId);
        courseToDelete.setCourseName(courseName);
        courseToDelete.setCourseStudentList(null);
        mCoursesRepository.removeCourse(courseToDelete);
        UsersInformationDO user=new UsersInformationDO();
        user=userCoursesInformationJsonData.get(0);
        for(int i=0;i<user.getCourseIds().size();i++ )
        {
            if(user.getCourseIds().get(i).equals(courseId))
            {  Log.i("removing","removed id");
                user.getCourseIds().remove(i);
                break;
            }
        }
        for(int i=0;i<user.getCourseNames().size();i++ )
        {

            Log.i("print the course",user.getCourseNames().get(i));
            Log.i("print the course",Integer.toString(user.getCourseNames().get(i).length()));
            Log.i("course name",courseName);
            Log.i("course name",Integer.toString(courseName.length()));
            if(user.getCourseNames().get(i).equals(courseName))
            {

                Log.i("removing","removed name");
                user.getCourseNames().remove(i);
                break;
            }
        }
        mUsersRepository.addUser(user);

        mDashboardView.showDeleteCourse();
    }


    public void checkIfCourseIsValid(String userType, ArrayList<CoursesInformationDO> coursesInformationJsonData, String courseId, String courseName)
    {
        switch (userType)
        {
            case "professor":
                if(coursesInformationJsonData.size()==0)
                {
                    addValidCourseProfessor(courseId,courseName);
                }
                else{
                    addInvalidCourse();
                }
                break;
            case "student":
                if(coursesInformationJsonData.size()!=0)
                {
                    addValidCourseStudent(courseId,coursesInformationJsonData.get(0).getCourseName());
                }
                else{
                    addInvalidCourse();
                }
                break;
        }
    }

    public void addInvalidCourse()
    {
        mDashboardView.showAddInvalidCourse();
    }


    public void addValidCourseProfessor(String courseId,String courseName)
    {
        mCoursesRepository.addCourse(courseId,courseName,null);
        CoursesInformationDO newCourse= new CoursesInformationDO();
        UsersInformationDO updateUser=userCoursesInformationJsonData.get(0);

        if(updateUser.getCourseIds()!=null && updateUser.getCourseNames()!=null)
        {
            updateUser.getCourseIds().add(courseId);
            updateUser.getCourseNames().add(courseName);
        }
        else{
            List<String> listOfCourseIds=new ArrayList<>();
            listOfCourseIds.add(courseId);
            List<String> listOfCourseNames=new ArrayList<>();
            listOfCourseNames.add(courseName);

            updateUser.setCourseIds(listOfCourseIds);
            updateUser.setCourseNames(listOfCourseNames);
        }

        mUsersRepository.addUser(updateUser);
        mDashboardView.showAddValidNewCourse();
    }

    public void addValidCourseStudent(String courseId, String courseName)
    {
        UsersInformationDO updateUser=userCoursesInformationJsonData.get(0);
        if(updateUser.getCourseIds().contains(courseId))
        {
            mDashboardView.showAddInvalidCourse();
        }
        else{
            if(updateUser.getCourseIds()!=null && updateUser.getCourseNames()!=null)
            {
                updateUser.getCourseIds().add(courseId);
                updateUser.getCourseNames().add(courseName);
            }
            else{
                List<String> listOfCourseIds=new ArrayList<>();
                listOfCourseIds.add(courseId);
                List<String> listOfCourseNames=new ArrayList<>();
                listOfCourseNames.add(courseName);

                updateUser.setCourseIds(listOfCourseIds);
                updateUser.setCourseNames(listOfCourseNames);
            }

            mUsersRepository.addUser(updateUser);
            if(coursesInformationJsonData.get(0).getCourseStudentList()==null)
            {
                List<String> listOfStudentIds=new ArrayList<>();
                listOfStudentIds.add(updateUser.getUserId());
                mCoursesRepository.addCourse(courseId,courseName,listOfStudentIds);

            }
            else{
                coursesInformationJsonData.get(0).getCourseStudentList().add(updateUser.getUserId());
                mCoursesRepository.addCourse(courseId,courseName,coursesInformationJsonData.get(0).getCourseStudentList());
            }
            mDashboardView.showAddValidNewCourse();
        }

    }
}
