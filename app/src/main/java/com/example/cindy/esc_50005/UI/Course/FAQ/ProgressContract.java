package com.example.cindy.esc_50005.UI.Course.FAQ;

import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by 1002215 on 12/3/18.
 */
public interface ProgressContract {

    interface View extends BaseView<Presenter> {
        void showProgress(ArrayList<Double> scoreList);
        void showAverage();
        void showNames(ArrayList<String> nameList);
    }

    interface Presenter extends BasePresenter {
        void loadScores();
        void processAverage();
        void loadNames();
    }
}