package com.example.esc_50005.UI.Course.FAQ.session.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

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
        mSessionsView.showEmptySessions();
    }

    public void generateListOfSessions()
    {
        Log.i("user json data",Integer.toString(usersJsonData.size()));
        listOfSessions=new ArrayList<String>();

        for(int i=0;i<usersJsonData.get(0).getSessionIds().size();i++)
        {
            String session=usersJsonData.get(0).getSessionDate().get(i)+ " -"  + usersJsonData.get(0).getSessionName().get(i);
            listOfSessions.add(session);
        }
        loadSessions();

    }


    public void processSessions(ArrayList<UsersInformationDO> usersJsonData) {
        if(usersJsonData.get(0).getSessionIds()!=null)
        {
            generateListOfSessions();
        }
        else{
            loadEmptySessions();
        }
    }

    public void queryAddNewSessionProfessor(String sessionId, String sessionName, String timeOfCreation, String courseId)
    {
        sessionsJsonData=mSessionsRepository.querySessions(sessionId);
        checkIfNewSessionIsValidProfessor(sessionId, sessionName,timeOfCreation, courseId);
    }

    @Override
    public void queryAddNewSessionStudent(String sessionId) {
        Log.i("query student","query student");
        sessionsJsonData=mSessionsRepository.querySessions(sessionId);
        checkIfNewSessionIsValidStudent(sessionId);
    }

    public void checkIfNewSessionIsValidStudent(String sessionId)
    {
        if(sessionsJsonData.size()==0)
        {
            addInvalidNewSession();
        }
        else{
            addNewSessionStudent(sessionsJsonData.get(0).getSessionID(),sessionsJsonData.get(0).getSessionName(),sessionsJsonData.get(0).getDateOfCreation());
        }
    }

    public void checkIfNewSessionIsValidProfessor(String sessionId, String sessionName, String timeOfCreation, String courseId)
    {
        if(sessionsJsonData.size()==0)
        {
            addNewSessionProfessor(sessionId,sessionName,timeOfCreation, courseId);
        }
        else{
            addInvalidNewSession();
        }

    }
    public void addNewSessionStudent(String sessionId, String sessionName, String timeOfCreation) {
        Log.i("at user","at student");
        UsersInformationDO updateUser=usersJsonData.get(0);
        if(updateUser.getSessionIds()==null)
        {
            List<String> listOfIds=new ArrayList<String>();
            listOfIds.add(sessionId);
            List<String> listOfNames=new ArrayList<>();
            listOfNames.add(sessionName);
            updateUser.setSessionIds(listOfIds);
            updateUser.setSessionName(listOfNames);
        }
        else{
            updateUser.getSessionIds().add(sessionId);
            updateUser.getSessionName().add(sessionName);
        }

        try{
            Thread.sleep(3000);
        }
        catch (Exception ex)
        {

        }

        mUserRepository.addUser(updateUser);
    }

    @Override
    public void addNewSessionProfessor(String sessionId, String sessionName, String timeOfCreation, String courseId) {
//        Log.i("user type listed",userInformation.getString("UserType",""));
//        mSessionsRepository.addSession(sessionId,sessionName,timeOfCreation,courseId);
//        UsersInformationDO updatedUser=new UsersInformationDO();
//        updatedUser=usersJsonData.get(0);
//        List<String> listOfSessionIdsForUser;
//        listOfSessionIdsForUser=usersJsonData.get(0).getSessionIds();
//        listOfSessionIdsForUser.add(sessionId);
//        List<String> listOfSessionNames;
//        listOfSessionNames=usersJsonData.get(0).getSessionName();
//        listOfSessionNames.add(sessionName);
//        List<String> listOfCourseIds;
//        listOfCourseIds=usersJsonData.get(0).getCourseIds();
//        listOfCourseIds.add(courseId);
//        List<String> listOfTimeCreated;
//        listOfTimeCreated=usersJsonData.get(0).getSessionDate();
//        listOfTimeCreated.add(timeOfCreation);
//
//        updatedUser.setSessionIds(listOfSessionIdsForUser);
//        updatedUser.setCourseIds(listOfCourseIds);
//        updatedUser.setSessionName(listOfSessionNames);
//        updatedUser.setSessionDate(listOfTimeCreated);
//        mUserRepository.addUser(updatedUser);
//
//        mSessionsView.showSuccessfulAddNewSession();

    }

    public void addInvalidNewSession()
    {
        mSessionsView.showUnsuccessfulAddNewSession();
    }

}
