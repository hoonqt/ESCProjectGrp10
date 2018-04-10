package com.example.esc_50005.Database.QuizAnswers;

import java.util.ArrayList;

/**
 * Created by hoonqt on 10/4/18.
 */

public interface QuizAnswersDataSource {

    void addQuestion(String subjectCodesessionCode, String quizNameStudentID, String answer, String time);
    ArrayList<QuizAnswersDO> getQuestions(String subjectCodesessionCode);

}
