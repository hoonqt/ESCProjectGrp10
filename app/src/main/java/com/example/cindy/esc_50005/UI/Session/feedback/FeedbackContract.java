package com.example.cindy.esc_50005.UI.Session.feedback;

import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseViewTwo;

/**
 * Created by tan_j on 5/3/2018.
 */

public interface FeedbackContract {
    interface View extends BaseViewTwo<Presenter> {
        void showRatingMessageView();

        void hideSubmitButton();

        void showFeedbackSubmitted();

        void dismissDialog();
    }

    interface Presenter extends BasePresenter {
        void onRatingSubmitted(float rating, String message);

        void onLaterClicked();
    }
}
