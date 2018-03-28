package com.example.cindy.esc_50005.UI.Course.FAQ.session.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformation;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;
import com.example.cindy.esc_50005.Database.sessionsInformation.SessionsInformationDO;
import com.example.cindy.esc_50005.Database.sessionsInformation.SessionsInformationRemoteDataSource;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqContract;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

public class SessionsPresenter implements SessionsContract.Presenter {

    public static final String TAG = "SessionsPresenter";

    private final SessionsContract.View mSessionsView;
    private UsersInformationRemoteDataSource mUserRepository;
    private SessionsInformationRemoteDataSource mSessionsRepository;
    ArrayList<UsersInformationDO> usersJsonData;
    private SharedPreferences userInformation;
    private ArrayList<String> listOfSessions=new ArrayList<String>();
    private ArrayList<SessionsInformationDO> sessionsJsonData;

    public SessionsPresenter(@NonNull SessionsContract.View sessionsView) {
        mUserRepository = new UsersInformationRemoteDataSource();
        mSessionsRepository=new SessionsInformationRemoteDataSource();
        mSessionsView = checkNotNull(sessionsView, "faqView cannot be null!");
        mSessionsView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    public void loadSessions()
    {
        mSessionsView.showSessions(listOfSessions);
    }

    public void querySessions(Context context) {

        userInformation = PreferenceManager.getDefaultSharedPreferences(context);
        usersJsonData=mUserRepository.queryParticularUser(userInformation.getString("Username",""),userInformation.getString("Password",""),userInformation.getString("UserType",""));
        Log.i("size of json",Integer.toString(usersJsonData.size()));
        processSessions(usersJsonData);

    }

    public void processEmptySessions() {

    }

    public void loadEmptySessions()
    {

    }

    public void generateListOfSessions()
    {
        Log.i("user json data",Integer.toString(usersJsonData.size()));
        listOfSessions=new ArrayList<String>();
//        try{
//            TimeUnit.SECONDS.sleep(10);
//        }
//        catch (Exception ex)
//        {
//
//        }
        for(int i=0;i<usersJsonData.get(0).getSessionIds().size();i++)
        {
//            Log.i("user stuff",usersJsonData.get(0).getSessionDate().get(0));
            String session=usersJsonData.get(0).getSessionDate().get(i)+ " -"  + usersJsonData.get(0).getSessionName().get(i);
            listOfSessions.add(session);
        }
        loadSessions();
    }


    public void processSessions(ArrayList<UsersInformationDO> usersJsonData) {
        if(usersJsonData.size()==1)
        {
            generateListOfSessions();
        }
        else{
            loadEmptySessions();
        }

//        for(UsersInformationDO user: usersJsonData)
//        {
//            if(user.getPassword().equals(userInformation.getString("Password","")) && user.getUsername().equals(userInformation.getString("Username","")) && user.getUserType().equals(userInformation.getString("UserType","")) && user.getSessionIds().size()!=0){
//                generateListOfSessions();
//            }
//            else{
//                loadEmptySessions();
//            }
//        }

    }

    public void queryAddNewSession(String sessionId, String sessionName, String timeOfCreation, String courseId)
    {
        sessionsJsonData=mSessionsRepository.querySessions(sessionId,sessionName);
        checkIfNewSessionIsValid(sessionId, sessionName,timeOfCreation, courseId);
    }

    public void checkIfNewSessionIsValid(String sessionId, String sessionName, String timeOfCreation, String courseId)
    {
        for(SessionsInformationDO sessions: sessionsJsonData)
        {
            if(sessions.getSessionID().equals(sessionId) && sessions.getSessionName().equals(sessionName)){
                addInvalidNewSession();
            }
            else{
                addNewSession(sessionId,sessionName,timeOfCreation, courseId);
            }
        }

    }

    @Override
    public void addNewSession(String sessionId, String sessionName, String timeOfCreation, String courseId) {

        mSessionsRepository.addSession(sessionId,sessionName,timeOfCreation,courseId);
        UsersInformationDO updatedUser=new UsersInformationDO();
        updatedUser=usersJsonData.get(0);
        List<String> listOfSessionIds=new ArrayList<>();
        listOfSessionIds=usersJsonData.get(0).getSessionIds();
        listOfSessionIds.add(sessionId);
        List<String> listOfSessionNames=new ArrayList<>();
        listOfSessionNames=usersJsonData.get(0).getSessionName();
        listOfSessionNames.add(sessionName);
        List<String> listOfCourseIds=new ArrayList<>();
        listOfCourseIds=usersJsonData.get(0).getCourseIds();
        listOfCourseIds.add(courseId);
        List<String> listOfTimeCreated=new ArrayList<>();
        listOfTimeCreated=usersJsonData.get(0).getSessionDate();
        listOfTimeCreated.add(timeOfCreation);

        updatedUser.setSessionIds(listOfSessionIds);
        updatedUser.setCourseIds(listOfCourseIds);
        updatedUser.setSessionName(listOfSessionNames);
        updatedUser.setSessionDate(listOfTimeCreated);
        mUserRepository.addUser(updatedUser);

        mSessionsView.showSuccessfulAddNewSession();

    }

    public void addInvalidNewSession()
    {
        mSessionsView.showUnsuccessfulAddNewSession();
    }

}
