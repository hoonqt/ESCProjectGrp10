package com.example.cindy.esc_50005.UI.Dashboard;

import android.content.Context;

import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface DashboardContract {

    interface Presenter extends BasePresenter {
        void loadCoursesFromDatabase(Context context);
    }
    interface View extends BaseView <Presenter> {
        void showSuccessfullyLoadedCourses(ArrayList<String> courseInformationJsonData);
        void attemptLoadCourses();
    }
}
