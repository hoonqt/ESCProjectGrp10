package com.example.cindy.esc_50005.UI.Course.FAQ.session.main;

import android.content.Context;

import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;
import java.util.List;


public interface SessionsContract {

    interface Presenter extends BasePresenter {
        void loadSessions();
        void processEmptySessions();
        void querySessions(Context context);
    }

    interface View extends BaseView<Presenter> {
        void showSessions(ArrayList<String> sessions);
        void showEmptySessions();
        void showLoadSessionsError();
    }
}
