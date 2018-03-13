package com.example.cindy.esc_50005.UI.Session;

import static com.google.common.base.Preconditions.checkNotNull;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.cindy.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import java.util.ArrayList;



public class QuestionsPresenter implements QuestionsContract.Presenter {

    public static final String TAG = "QuestionsPresenter";
    //temporary set session code to be as such
    private static final String SESSION_CODE="111";

    private final QuestionsContract.View mSessionQuestionView;
    private SessionQuestionsRemoteDataSource mSessionQuestionsRepository;
    ArrayList<SessionQuestionsDO> questionsJsonData;


    public QuestionsPresenter(@NonNull QuestionsContract.View sessionQuestionsView) {
        Log.i("question presenter","question presenter");
        mSessionQuestionsRepository = new SessionQuestionsRemoteDataSource();
        mSessionQuestionView = checkNotNull(sessionQuestionsView, "sessionQuestionView cannot be null!");
        mSessionQuestionView.setPresenter(this);
    }
    @Override
    public void start() {

        loadQuestions();
    }

    public class QuestionsJsonData {

        String _question;
        String upvotes;

    }


    @Override
    public void loadQuestions() {

       questionsJsonData = mSessionQuestionsRepository.getQuestionsListBySessionId(SESSION_CODE);

    }

    @Override
    public void addNewQuestion(String question) {

        mSessionQuestionsRepository.addQuestion(question,SESSION_CODE);
        loadQuestions();

    }

    @Override
    public void processEmptyQuestion() {

    }

    public void processQuestions(ArrayList<SessionQuestionsDO> questionsJsonData)
    {
        Log.i(TAG, "Length of faqJsonData = " + questionsJsonData.size());

        if (questionsJsonData.size() != 0) {
            mSessionQuestionView.showAddedQuestion(questionsJsonData);
            mSessionQuestionView.questionsLoaded();
        }
    }


    public void upvoteQuestion(SessionQuestionsDO question) {
        question.setUpvote(question.getUpvote() + 1);
        mSessionQuestionsRepository.saveQuestion(question);
        Log.i(TAG, "upvote question" + question.getUpvote());
        loadQuestions();
    }



}
