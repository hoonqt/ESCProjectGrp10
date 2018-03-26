package com.example.cindy.esc_50005.UI.ProfSession;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public interface ProfQnContract {

    interface View extends BaseView<ProfQnContract.Presenter> {
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
