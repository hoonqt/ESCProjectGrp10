package com.example.esc_50005.UI.Course.FAQ;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;


public interface FaqContract {

    interface Presenter extends BasePresenter {
        void loadFaq();
        void processEmptyFaq();
        void upvoteFaq(Faq faq);
        void downvoteFaq(Faq faq);
        void setUserId(String userId);
        void setCourseId(String courseId);
    }

    interface View extends BaseView<Presenter> {
        void showFaq(ArrayList<Faq> data);
        void showNoFaq();
        void showLoadFaqError();
        void faqLoaded();
    }
}
