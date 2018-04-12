package com.example.esc_50005.UI.Session.addQuestionDialog;

import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

/**
 * Created by tan_j on 5/3/2018.
 */

public interface AddQuestionDialogContract {
    interface View extends BaseView<Presenter> {
        void dismissDialog();
    }

    interface Presenter extends BasePresenter {
        void addQuestion(String question);
        void setUserId(String userId);
        void setSessionId(String sessionId);
        void setName(String name);
        void onLaterClicked();
    }
}
