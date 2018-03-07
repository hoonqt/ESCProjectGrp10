package com.example.cindy.esc_50005.UI.Login;

import android.view.View;

import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

/**
 * Created by cindy on 19/2/2018.
 */

public interface LoginContract {

    interface LoginContractPresenter extends BasePresenter {
        void loadLogin();
    }
    interface LoginContractView extends BaseView {
        void setupLogin(View view);
    }
}
