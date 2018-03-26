package com.example.cindy.esc_50005.UI.ProfSession.Contracts;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public interface QnProfContract {

    interface View extends BaseView<QnProfContract.Presenter> {
        void showQuestions();
        void showAddedQuestion(ArrayList<SessionQuestionsDO> questionsList);
        void showNoQuestions();
        void showLoadQuestionsError();

    }

    interface Presenter extends BasePresenter {
        void loadQuestions();
        void addNewQuestion(String question);
        void processEmptyQuestion();
        void upvoteQuestion(SessionQuestionsDO question);
    }

}
