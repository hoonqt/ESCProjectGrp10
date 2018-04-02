package com.example.esc_50005.UI.Session.Student.StudentActivity.Contracts;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.UI.Base.BasePresenter;
import com.example.esc_50005.UI.Base.BaseView;

import java.util.ArrayList;

/**
 * Created by hoonqt on 30/3/18.
 */

public interface QuizStudentContract {

    interface View extends BaseView<Presenter> {
        void showQuizes(ArrayList<QuizQuestions2DO> allthequestions);
        void showNoQuiz();
        void showLoadQuizError();
    }

    interface Presenter extends BasePresenter {
        void loadQuizes(String subjectCode, String sessionCode);
        void processEmptyQuiz();
        ArrayList<QuizQuestions2DO> getQuestionData(String subjectCode, String sessionCode);

    }

}