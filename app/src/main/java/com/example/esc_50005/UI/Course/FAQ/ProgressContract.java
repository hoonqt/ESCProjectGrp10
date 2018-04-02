package com.example.esc_50005.UI.Course.FAQ;

import com.example.esc_50005.Database.Progress.NewQuizScoresDO;
import com.example.esc_50005.Database.Progress.QuizScores2DO;
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
        void showNames(ArrayList<String> nameList, ArrayList<String> studentIds, double avg);
    }

    interface Presenter extends BasePresenter {
        void loadScores();
        double processAverage(ArrayList<QuizScores2DO> progressArrayList);
        void loadNames();
    }
}