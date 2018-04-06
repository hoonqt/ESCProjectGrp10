package com.example.esc_50005.UI.Course.FAQ;

import com.example.esc_50005.Database.Progress.QuizScores4DO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by 1002215 on 12/3/18.
 */
public interface ProgressContract {

    interface View extends BaseView<Presenter> {
        void showProgress(ArrayList<Double> scoreList);
        void showAverage(double avg);
        void showNames(ArrayList<String> nameList, ArrayList<String> studentIds, ArrayList<Double> avg);
        void showNoName();
    }

    interface Presenter extends BasePresenter {
        void setStudentId(String studentId);
        void setCourseId(String courseId);
        void loadScores();
        ArrayList<Double> processAverage(ArrayList<QuizScores4DO> progressArrayList);
        void loadNames();
    }
}