package com.example.cindy.esc_50005.UI.Session;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by 1002215 on 20/2/18.
 */

public interface QuestionsContract {

    interface View extends BaseView <Presenter>{
        void showQuestions();
        void showAddedQuestion(ArrayList<SessionQuestionsDO> questionsList);
        void showNoQuestions();
        void showLoadQuestionsError();
        void questionsLoaded();
    }

    interface Presenter extends BasePresenter{
        void loadQuestions();
        void addNewQuestion(String question);
        void processEmptyQuestion();
        void upvoteQuestion(SessionQuestionsDO question);
    }
}
