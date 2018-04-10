package com.example.esc_50005.UI.Session.Prof.Contracts;



import com.example.esc_50005.Database.QuizAnswers.QuizAnswersDO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

public interface ResponseProfContract {

    interface View extends BaseView<Presenter> {
        void showResponses(ArrayList<QuizAnswersDO> allthequestions);
        void showNoResponses();

    }

    interface Presenter extends BasePresenter {
        void loadResponses(String subjectCode, String sessionCode, String qnName);
        void processEmptyQuiz();


    }
}
