package com.example.cindy.esc_50005.UI.Course.FAQ;

import android.view.View;

import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

/**
 * Created by cindy on 19/2/2018.
 */

public interface FaqContract {

    interface FaqContractPresenter extends BasePresenter {

        void loadFaq();
        void processEmptyFaq();
        void upvoteFaq();
    }
    interface FaqContractView extends BaseView {
        void showFaq(View view);
        void showNoFaq();
        void showLoadFaqError();
    }
}
