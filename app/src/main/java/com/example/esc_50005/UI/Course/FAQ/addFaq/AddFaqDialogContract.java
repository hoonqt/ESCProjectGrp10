package com.example.esc_50005.UI.Course.FAQ.addFaq;

import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

/**
 * Created by Otter on 4/4/2018.
 */

public class AddFaqDialogContract {
    interface View extends BaseView<AddFaqDialogContract.Presenter> {
        void dismissDialog();
    }

    interface Presenter extends BasePresenter {
        void addFaq(String question, String answer);
        void setCourseId(String courseId);
        void setUserId(String userId);
    }
}
