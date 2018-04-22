package com.example.esc_50005.UI.Session.Main;

import com.example.esc_50005.Database.Database.Question;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by 1002215 on 20/2/18.
 */

public interface QuestionsContract {

    interface View extends BaseView <Presenter>{
        void showQuestions(ArrayList<Question> questionsList);
        void questionsLoaded();
    }

    interface Presenter extends BasePresenter{
        void loadQuestions();
        void addNewQuestion(String question);
        void upvoteQuestion(Question question);
        void downvoteQuestion(Question question);
        void setUserId(String userId);
        void setSessionId(String sessionId);
    }
}
