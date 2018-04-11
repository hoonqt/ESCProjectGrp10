package com.example.esc_50005.UI.Session.Prof.Presenters;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions2DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.Log;
import com.example.esc_50005.R;
import com.example.esc_50005.UI.Session.Prof.Contracts.QuizProfContract;

import java.util.ArrayList;

import static android.support.v4.util.Preconditions.checkNotNull;

/**
 * Created by hoonqt on 25/3/18.
 */

public class ActivityProfPresenter implements QuizProfContract.Presenter{

    private final QuizProfContract.View mQuizProfView;
    private QuizRemoteDataSource mQuizQuestionsRepository;
    private ArrayList<QuizQuestions2DO> questionData;

    SharedPreferences sharedPreferences;


    public ActivityProfPresenter(@NonNull QuizProfContract.View quizProfView,Context context) {

        mQuizQuestionsRepository = new QuizRemoteDataSource();
        mQuizProfView = checkNotNull(quizProfView,"Quiz not null");
        mQuizProfView.setPresenter(this);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

    }

    @Override
    public void start() {

    }

    @Override
    public void loadQuizes(String subjectCode, String sessionCode) {

        questionData = mQuizQuestionsRepository.getQuestions(subjectCode, sessionCode);
        if (questionData.isEmpty()) {
            mQuizProfView.showNoQuiz();
        }

        else {
            mQuizProfView.showQuizes(questionData);
        }

    }

    @Override
    public void processEmptyQuiz() {

    }

    @Override
    public ArrayList<QuizQuestions2DO> getQuestionData(String subjectCode, String sessionCode) {
        loadQuizes(subjectCode, sessionCode);
        return questionData;
    }

    @Override
    public ArrayList<QuizQuestions2DO> getStoredData() {
        return questionData;
    }
}