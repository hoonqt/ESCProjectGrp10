package com.example.esc_50005.UI.ProfSession.Contracts;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by hoonqt on 25/3/18.
 */

public interface QuizProfContract {

    interface View extends BaseView<Presenter> {
        void showQuizes(ArrayList<QuizQuestions1DO> allthequestions);
        void showAddedQuiz(ArrayList<QuizQuestions1DO> questionsList);
        void showNoQuiz();
        void showLoadQuizError();
//        void questionsLoaded();
    }

    interface Presenter extends BasePresenter {
        void loadQuizes(String subjectCode, String sessionCode);
        void addNewQuiz(String subjCode, String sessionCode, String quizName,String question, String correctAns, ArrayList<String> options);
        void processEmptyQuiz();
        ArrayList<QuizQuestions1DO> getQuestionData(String subjectCode, String sessionCode);

    }

}
