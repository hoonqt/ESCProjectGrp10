package com.example.esc_50005.UI.StudentActivity.Presenters;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.esc_50005.Database.Quizstuff.QuizQuestions1DO;
import com.example.esc_50005.Database.Quizstuff.QuizRemoteDataSource;
import com.example.esc_50005.UI.ProfSession.Contracts.QuizProfContract;
import com.example.esc_50005.UI.StudentActivity.Adapters.ActivityStudentAdapter;
import com.example.esc_50005.UI.StudentActivity.Contracts.QuizStudentContract;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by hoonqt on 30/3/18.
 */

public class ActivityStudentPresenter implements QuizStudentContract.Presenter {

    private final QuizStudentContract.View mQuizStudentView;
    private QuizRemoteDataSource mQuizQuestionsRepository;
    private ArrayList<QuizQuestions1DO> questionData;

    public ActivityStudentPresenter(QuizStudentContract.View fragment) {

        mQuizQuestionsRepository = new QuizRemoteDataSource();
        mQuizStudentView = checkNotNull(fragment,"Quiz not null");
        mQuizStudentView.setPresenter(this);


    }

    @Override
    public void start() {
        loadQuizes("50.004","Session1");
        Log.i("Presenter","been here");

    }

    @Override
    public void loadQuizes(String subjectCode, String sessionCode) {
        questionData = mQuizQuestionsRepository.getQuestions(subjectCode,sessionCode);
        Log.i("Size of data:", Integer.toString(questionData.size()));
        mQuizStudentView.showQuizes(questionData);
    }

    @Override
    public void processEmptyQuiz() {

    }

    @Override
    public ArrayList<QuizQuestions1DO> getQuestionData(String subjectCode, String sessionCode) {
        return null;
    }
}
