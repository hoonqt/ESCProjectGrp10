package com.example.cindy.esc_50005.UI.Course.FAQ.session.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.CoursesInformation.CoursesInformationRemoteDataSource;
import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationRemoteDataSource;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqContract;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

public class SessionsPresenter implements SessionsContract.Presenter {

    public static final String TAG = "SessionsPresenter";

    private final SessionsContract.View mSessionsView;
    private UsersInformationRemoteDataSource mUserRepository;
    ArrayList<UsersInformationDO> usersJsonData;
    private SharedPreferences userInformation;
    private ArrayList<String> listOfSessions=new ArrayList<String>();

    public SessionsPresenter(@NonNull SessionsContract.View sessionsView) {
        mUserRepository = new UsersInformationRemoteDataSource();
        mSessionsView = checkNotNull(sessionsView, "faqView cannot be null!");
        mSessionsView.setPresenter(this);
    }

    @Override
    public void start() {
//        querySessions()
//        loadSessions();
    }

    public void loadSessions()
    {
        mSessionsView.showSessions(listOfSessions);
    }

    public void querySessions(Context context) {

        userInformation = PreferenceManager.getDefaultSharedPreferences(context);
        usersJsonData=mUserRepository.queryUser(userInformation.getString("Username",""),userInformation.getString("Password",""),userInformation.getString("UserType",""));
        processSessions(usersJsonData);

    }

    public void processEmptySessions() {

    }

    public void loadEmptySessions()
    {

    }

    public void generateListOfSessions()
    {
//        Log.i("user json data",Integer.toString(usersJsonData.size()));
        for(int i=0;i<usersJsonData.get(0).getCourseIds().size();i++)
        {
//            Log.i("user stuff",usersJsonData.get(0).getSessionDate().get(0));
            String session=usersJsonData.get(0).getSessionDate().get(i)+ " -"  + usersJsonData.get(0).getSessionName().get(i);
            listOfSessions.add(session);
        }
        loadSessions();
    }


    public void processSessions(ArrayList<UsersInformationDO> usersJsonData) {

        for(UsersInformationDO user: usersJsonData)
        {
            if(user.getPassword().equals(userInformation.getString("Password","")) && user.getUsername().equals(userInformation.getString("Username","")) && user.getUserType().equals(userInformation.getString("UserType","")) && user.getSessionIds().size()!=0){
                generateListOfSessions();
            }
            else{
                loadEmptySessions();
            }
        }

    }

}
