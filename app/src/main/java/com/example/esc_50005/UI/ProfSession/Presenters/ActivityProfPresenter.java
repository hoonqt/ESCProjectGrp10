package com.example.esc_50005.UI.ProfSession.Presenters;

import android.support.annotation.NonNull;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.UI.ProfSession.Contracts.QuizProfContract;

import java.util.ArrayList;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by hoonqt on 25/3/18.
 */

public class ActivityProfPresenter implements QuizProfContract.Presenter{

    private final QuizProfContract.View mQuizProfView;
    private QuizRemoteDataSource mQuizQuestionsRepository;
    private ArrayList<QuizQuestions1DO> questionData;

    public ActivityProfPresenter(@NonNull QuizProfContract.View quizProfView) {

        mQuizQuestionsRepository = new QuizRemoteDataSource();
        mQuizProfView = checkNotNull(quizProfView,"Quiz not null");
        mQuizProfView.setPresenter(this);

    }

    @Override
    public void start() {

        loadQuizes("50.004","session1");

    }

    @Override
    public void loadQuizes(String subjectCode, String sessionCode) {

        questionData = mQuizQuestionsRepository.getQuestions(subjectCode, sessionCode);

    }

    @Override
    public void addNewQuiz(String subjCode, String sessionCode, String quizName,String question, String correctAns, ArrayList<String> options) {


    }

    @Override
    public void processEmptyQuiz() {

    }

    public ArrayList<QuizQuestions1DO> getQuestionData(String subjectCode, String sessionCode) {
        loadQuizes(subjectCode, sessionCode);
        return questionData;
    }
}
