package com.example.esc_50005.UI.Course.FAQ.session.main;

import android.content.Context;

import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;


public interface SessionsContract {

    interface Presenter extends BasePresenter {
        void loadSessions();
        void processEmptySessions();
        void querySessions(Context context);
        void queryAddNewSessionProfessor(String sessionId, String sessionName, String timeOfCreation, String courseId);
        void queryAddNewSessionStudent(String sessionId);
        void addNewSessionProfessor(String sessionId,String sessionName,String timeOfCreation, String courseId);
        void addNewSessionStudent(String sessionId,String sessionName,String timeOfCreation);
    }

    interface View extends BaseView<Presenter> {
        void showSessions(ArrayList<String> sessions);
        void showEmptySessions();
        void showLoadSessionsError();
        void showSuccessfulAddNewSession();
        void showUnsuccessfulAddNewSession();
        void sessionsLoaded();
    }
}
