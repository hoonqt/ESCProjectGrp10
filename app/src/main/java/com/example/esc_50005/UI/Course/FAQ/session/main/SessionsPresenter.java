package com.example.esc_50005.UI.Course.FAQ.session.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.CoursesInformation.CoursesInformationDO;
import com.example.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.Database.UsersInformation.EditedUsersInformationDO;
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
    ArrayList<EditedUsersInformationDO> usersJsonData;
    private ArrayList<SessionsInformationDO> listOfSessions=new ArrayList<SessionsInformationDO>();
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

    public void querySessions(String userId, String currentCourse) {

        usersJsonData=mUserRepository.queryAParticularUser(userId);
        processSessions(usersJsonData, currentCourse);

    }
    public void processSessions(ArrayList<EditedUsersInformationDO> usersJsonData, String currentCourse) {
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
        listOfSessions=new ArrayList<SessionsInformationDO>();
        String[] retrieveCourseId = currentCourse.split("\\s+");
        String courseId=retrieveCourseId[0];
        courseJsonData=mCoursesRepository.queryCourses(retrieveCourseId[0]);

        for(int i=0;i<usersJsonData.get(0).getSessionIds().size();i++)
        {
            queriedSessionsJsonData=mSessionsRepository.querySessions(usersJsonData.get(0).getSessionIds().get(0));

            if(queriedSessionsJsonData.get(0).getCourseId().equals(courseId))
            {
                SessionsInformationDO session=new SessionsInformationDO();
                session.setSessionDate(usersJsonData.get(0).getSessionDates().get(i));
                session.setSessionName(usersJsonData.get(0).getSessionNames().get(i));
                session.setSessionId(usersJsonData.get(0).getSessionIds().get(i));
//                session=usersJsonData.get(0).getSessionDates().get(i)+ " -"  + usersJsonData.get(0).getSessionNames().get(i);
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

        if(sessionsJsonData.size()==0 && userType.equals("professor"))
        {
            addNewSession(userType,sessionId,sessionName,timeOfCreation,courseId);
        }

        else if(sessionsJsonData.size()==0 && userType.equals("student"))
        {
            addInvalidNewSession();
        }

        else{
            switch(userType)
            {
                case "student":
                    addNewSession(userType,sessionsJsonData.get(0).getSessionId(),sessionsJsonData.get(0).getSessionName(),sessionsJsonData.get(0).getSessionDate(),"");
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

        EditedUsersInformationDO updatedUser=usersJsonData.get(0);
        if(updatedUser.getSessionIds()==null)
        {
            List<String> listOfIds=new ArrayList<String>();
            listOfIds.add(sessionId);
            List<String> listOfNames=new ArrayList<>();
            listOfNames.add(sessionName);
            updatedUser.setSessionIds(listOfIds);
            updatedUser.setSessionNames(listOfNames);
        }
        else{
            updatedUser.getSessionIds().add(sessionId);
            updatedUser.getSessionNames().add(sessionName);
            updatedUser.getSessionDates().add(timeOfCreation);
        }
        mUserRepository.addUser(updatedUser);

        mSessionsView.showSuccessfulAddNewSession();

    }

    public void addInvalidNewSession()
    {
        mSessionsView.showUnsuccessfulAddNewSession();
    }

}
