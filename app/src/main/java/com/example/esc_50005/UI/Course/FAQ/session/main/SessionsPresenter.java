package com.example.esc_50005.UI.Course.FAQ.session.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.CoursesInformation.CoursesInformationDO;
import com.example.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;
import com.example.esc_50005.Database.sessionsInformation.SessionsInformationDO;
import com.example.esc_50005.Database.sessionsInformation.SessionsInformationRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public class SessionsPresenter implements SessionsContract.Presenter {

    public static final String TAG = "SessionsPresenter";

    private final SessionsContract.View mSessionsView;
    public UsersInformationRemoteDataSource mUserRepository;
    public SessionsInformationRemoteDataSource mSessionsRepository;
    public CoursesInformationRemoteDataSource mCoursesRepository;
    ArrayList<UsersInformationDO> usersJsonData;
    private ArrayList<String> listOfSessions=new ArrayList<String>();
    private ArrayList<SessionsInformationDO> sessionsJsonData;
    private ArrayList<SessionsInformationDO> queriedSessionsJsonData;
    private ArrayList<CoursesInformationDO> courseJsonData;

    public SessionsPresenter(@NonNull SessionsInformationRemoteDataSource sessionsRepository,
                             @NonNull CoursesInformationRemoteDataSource coursesRepository,
                             @NonNull UsersInformationRemoteDataSource usersRepository,
                             @NonNull SessionsContract.View sessionsView) {
        mSessionsRepository=sessionsRepository;
        mCoursesRepository=coursesRepository;
        mUserRepository=usersRepository;
        mSessionsView = checkNotNull(sessionsView, "faqView cannot be null!");
        mSessionsView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    public void loadSessions()
    {
        mSessionsView.showSessions(listOfSessions);
        mSessionsView.sessionsLoaded();
    }

    public void querySessions(String username, String userType, String currentCourse) {
        usersJsonData=mUserRepository.queryParticularUser(username,userType);
        processSessions(usersJsonData, currentCourse);

    }
    public void processSessions(ArrayList<UsersInformationDO> usersJsonData, String currentCourse) {
        if(usersJsonData.get(0).getSessionIds()!=null)
        {
            generateListOfSessions(currentCourse);
        }
        else{
            loadEmptySessions();
        }
    }

    public void processEmptySessions() {

    }

    public void loadEmptySessions()
    {
        mSessionsView.showEmptySessions();
    }

    public void generateListOfSessions(String currentCourse)
    {
        listOfSessions=new ArrayList<String>();
        String[] retrieveCourseId = currentCourse.split("\\s+");
        String courseId=retrieveCourseId[0];
        courseJsonData=mCoursesRepository.queryCourses(Double.parseDouble(retrieveCourseId[0]));
//        ArrayList<UsersInformationDO> newUserJsonData;
//
//        newUserJsonData=mUserRepository.queryParticularUser(usersJsonData.get(0).getUsername(),usersJsonData.get(0).getUserType());


        for(int i=0;i<usersJsonData.get(0).getSessionIds().size();i++)
        {

            queriedSessionsJsonData=mSessionsRepository.querySessions(usersJsonData.get(0).getSessionIds().get(0));

            if(queriedSessionsJsonData.get(0).getCourseID().equals(courseId))
            {
                String session=usersJsonData.get(0).getSessionDate().get(i)+ " -"  + usersJsonData.get(0).getSessionName().get(i);
                listOfSessions.add(session);
            }
        }
        if(listOfSessions.size()==0)
        {
            loadEmptySessions();
        }
        else{
            loadSessions();
        }

    }


    public void queryAddNewSession(String userType, String sessionId, String sessionName, String timeOfCreation, String courseId)
    {
        switch(userType)
        {
            case "student":
                sessionsJsonData=mSessionsRepository.querySessions(sessionId);
                checkIfNewSessionIsValid(userType, sessionId, "","","");
            case "professor":
                sessionsJsonData=mSessionsRepository.querySessions(sessionId);
                checkIfNewSessionIsValid(userType, sessionId, sessionName,timeOfCreation, courseId);
        }

    }

    public void checkIfNewSessionIsValid(String userType, String sessionId, String sessionName, String timeOfCreation, String courseId)
    {
        if(sessionsJsonData.size()==0)
        {
            addInvalidNewSession();
        }

        else{
            switch(userType)
            {
                case "student":
                    addNewSession(userType,sessionsJsonData.get(0).getSessionID(),sessionsJsonData.get(0).getSessionName(),sessionsJsonData.get(0).getDateOfCreation(),"");
                case "professor":
                    addNewSession(userType,sessionId,sessionName,timeOfCreation, courseId);
            }
        }


    }

    @Override
    public void addNewSession(String userType, String sessionId, String sessionName, String timeOfCreation, String courseId) {
        switch(userType){
            case "student":
                break;
            case "professor":
                mSessionsRepository.addSession(sessionId,sessionName,timeOfCreation,courseId);
        }

        UsersInformationDO updatedUser=usersJsonData.get(0);
        if(updatedUser.getSessionIds()==null)
        {
            List<String> listOfIds=new ArrayList<String>();
            listOfIds.add(sessionId);
            List<String> listOfNames=new ArrayList<>();
            listOfNames.add(sessionName);
            updatedUser.setSessionIds(listOfIds);
            updatedUser.setSessionName(listOfNames);
        }
        else{
            updatedUser.getSessionIds().add(sessionId);
            updatedUser.getSessionName().add(sessionName);
            updatedUser.getSessionDate().add(timeOfCreation);
        }
        mUserRepository.addUser(updatedUser);

        mSessionsView.showSuccessfulAddNewSession();

    }

    public void addInvalidNewSession()
    {
        mSessionsView.showUnsuccessfulAddNewSession();
    }

}
