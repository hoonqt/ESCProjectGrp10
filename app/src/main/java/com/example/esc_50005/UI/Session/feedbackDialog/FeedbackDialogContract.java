package com.example.esc_50005.UI.Session.feedbackDialog;

import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

/**
 * Created by tan_j on 5/3/2018.
 */

public interface FeedbackDialogContract {
    interface View extends BaseView<Presenter> {
        void dismissDialog();
    }

    interface Presenter extends BasePresenter {
        void addFeedback(float rating, String message);
        void setUserId(String userId);
        void setSessionId(String sessionId);
        void setName(String name);
        void onLaterClicked();
    }
}
