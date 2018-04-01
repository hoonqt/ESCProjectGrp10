package com.example.esc_50005.UI.Dashboard.main;

import android.content.Context;

import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface DashboardContract {

    interface Presenter extends BasePresenter {
        void loadCoursesFromDatabase(Context context);
        void addValidCourseProfessor(Double courseId, String courseName);
        void queryCourseBeforeAddingProfessor(Double courseId, String courseName);
        void queryCourseBeforeAddingStudent(Double courseId);
    }
    interface View extends BaseView <Presenter> {
        void showSuccessfullyLoadedCourses(ArrayList<String> courseInformationJsonData);
        void attemptLoadCourses();
        void showAddValidNewCourse();
        void showAddInvalidCourse();

    }
}
