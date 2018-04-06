package com.example.esc_50005.UI.Session.Professor.Contracts;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public interface QuizProfContract {

    interface View extends BaseView<Presenter> {
        void showQuizes(ArrayList<QuizQuestions2DO> allthequestions);
        void showAddedQuiz(ArrayList<QuizQuestions2DO> questionsList);
        void showNoQuiz();
        void showLoadQuizError();
//        void questionsLoaded();
    }

    interface Presenter extends BasePresenter {
        void loadQuizes(String subjectCode, String sessionCode);
        void processEmptyQuiz();
        ArrayList<QuizQuestions2DO> getQuestionData(String subjectCode, String sessionCode);
        ArrayList<QuizQuestions2DO> getStoredData();


    }

}
