package com.example.esc_50005.UI.Course.FAQ.session.main;

import android.content.Context;

import com.example.esc_50005.Database.sessionsInformation.SessionsInformationDO;
import com.example.esc_50005.Database.sessionsInformation.SessionsInformationDataSource;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;
import java.util.List;


public interface SessionsContract {

    interface Presenter extends BasePresenter {
        void loadSessions();
        void processEmptySessions();
        void querySessions(String userId, String currentCourse);
        void queryAddNewSession(String userType, String sessionId, String sessionName, String timeOfCreation, String courseId);
        void addNewSession(String userType, String sessionId,String sessionName,String timeOfCreation, String courseId);
        void deleteSession(String sessionId, String sessionName, String sessionDate, List<String> listOfStudents);

    }

    interface View extends BaseView<Presenter> {
        void showSessions(ArrayList<SessionsInformationDO> sessions);
        void showEmptySessions();
        void showLoadSessionsError();
        void showSuccessfulAddNewSession();
        void showUnsuccessfulAddNewSession();
        void sessionsLoaded();
        void showDeleteSession();
    }
}
