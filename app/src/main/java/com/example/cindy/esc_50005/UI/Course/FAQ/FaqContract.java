package com.example.cindy.esc_50005.UI.Course.FAQ;

import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;


public interface FaqContract {

    interface Presenter extends BasePresenter {
        void loadFaq();
        void processEmptyFaq();
        void upvoteFaq();
    }

    interface View extends BaseView<Presenter> {
        <T> void showFaq(T data);
        void showNoFaq();
        void showLoadFaqError();
    }
}
