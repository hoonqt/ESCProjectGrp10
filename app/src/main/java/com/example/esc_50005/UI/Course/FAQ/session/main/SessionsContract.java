package com.example.esc_50005.UI.Course.FAQ.session.main;

import android.content.Context;

import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;


public interface SessionsContract {

    interface Presenter extends BasePresenter {
        void loadSessions();
        void processEmptySessions();
        void querySessions(String username, String userType, String currentCourse);
        void queryAddNewSession(String userType, String sessionId, String sessionName, String timeOfCreation, String courseId);
        void addNewSession(String userType, String sessionId,String sessionName,String timeOfCreation, String courseId);

    }

    interface View extends BaseView<Presenter> {
        void showSessions(ArrayList<String> sessions);
        void showEmptySessions();
        void showLoadSessionsError();
        void showSuccessfulAddNewSession();
        void showUnsuccessfulAddNewSession();
    }
}
