package com.example.esc_50005.UI.Session.feedback;

import com.example.esc_50005.Database.feedback.Feedback;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by Otter on 21/3/2018.
 */

public class FeedbackContract {

    interface Presenter extends BasePresenter {
        void loadFeedback();
//        void processEmptyFeedback();
    }

    interface View extends BaseView<Presenter> {
        void showFeedback(ArrayList<Feedback> data);
//        void showNoFeedback();
//        void showLoadFeedbackError();
        void feedbackLoaded();
    }
}
