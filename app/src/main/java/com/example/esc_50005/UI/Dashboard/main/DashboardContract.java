package com.example.esc_50005.UI.Dashboard.main;

import android.content.Context;

import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface DashboardContract {

    interface Presenter extends BasePresenter {
        void loadCoursesFromDatabase(String userId);
        void addValidCourseProfessor(String courseId, String courseName);
        void queryCourseBeforeAdding(String userType, String courseId, String courseName);
        void deleteCourse(String courseId, String courseName);
    }
    interface View extends BaseView <Presenter> {
        void showSuccessfullyLoadedCourses(ArrayList<String> courseInformationJsonData);
        void attemptLoadCourses();
        void showAddValidNewCourse();
        void showAddInvalidCourse();
        void showEmptyCourses();
        void coursesLoaded();
        void showDeleteCourse();

    }
}
