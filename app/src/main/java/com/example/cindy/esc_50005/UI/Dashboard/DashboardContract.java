package com.example.cindy.esc_50005.UI.Dashboard;

import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

public interface DashboardContract {

    interface Presenter extends BasePresenter {
        void loadCoursesFromDatabase();
        void loadSuccessfullyLoadedCourses();
    }
    interface View extends BaseView <Presenter> {
        void showSuccessfullyLoadedCourses();
    }
}
