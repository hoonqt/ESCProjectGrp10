package com.example.esc_50005.UI.Dashboard.main;

import android.content.Context;

import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface DashboardContract {

    interface Presenter extends BasePresenter {
        void loadCoursesFromDatabase(Context context);
        void addValidCourseProfessor(Double courseId, String courseName);
        void queryCourseBeforeAdding(Double courseId, String courseName);
    }
    interface View extends BaseView <Presenter> {
        void showSuccessfullyLoadedCourses(ArrayList<String> courseInformationJsonData);
        void attemptLoadCourses();
        void addValidNewCourse();
        void showAddInvalidCourse();

    }
}
