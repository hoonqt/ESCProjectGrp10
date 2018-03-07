package com.example.cindy.esc_50005.UI.Session;

import com.example.cindy.esc_50005.UI.Base.BasePresenter;
import com.example.cindy.esc_50005.UI.Base.BaseView;

/**
 * Created by 1002215 on 20/2/18.
 */

public interface QuestionsContract {

    interface QuestionContractView extends BaseView{
        void showQuestion();
        void showAddQuestion();
        void showNoQuestions();
        void showLoadQuestionsError();
    }

    interface QuestionContractPresenter extends BasePresenter{
        void loadQuestions();
        void addNewQuestion();
        void processEmptyQuestion();
        void upvoteQuestion();
    }
}
