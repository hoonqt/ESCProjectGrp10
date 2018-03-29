package com.example.esc_50005.UI.Session.Main;

import android.support.annotation.NonNull;

import com.example.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;


public class QuestionsPresenter implements QuestionsContract.Presenter {

    public static final String TAG = "QuestionsPresenter";
    //temporary set session code to be as such
    private static final String SESSION_CODE="111";

    private final QuestionsContract.View mSessionQuestionView;
    private SessionQuestionsRemoteDataSource mSessionQuestionsRepository;
    ArrayList<SessionQuestionsDO> questionsJsonData;


    public QuestionsPresenter(@NonNull QuestionsContract.View sessionQuestionsView) {
        mSessionQuestionsRepository = new SessionQuestionsRemoteDataSource();
        mSessionQuestionView = checkNotNull(sessionQuestionsView, "sessionQuestionView cannot be null!");
        mSessionQuestionView.setPresenter(this);
    }
    @Override
    public void start() {

        loadQuestions();
    }

    @Override
    public void loadQuestions() {
        questionsJsonData = mSessionQuestionsRepository.getQuestionsListBySessionId(SESSION_CODE);
        processQuestions(questionsJsonData);
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
        if (questionsJsonData.size() != 0) {
            mSessionQuestionView.showAddedQuestion(questionsJsonData);
        }
    }


    public void upvoteQuestion(SessionQuestionsDO question) {
        question.setUpvote(question.getUpvote() + 1);
        mSessionQuestionsRepository.saveQuestion(question);
        loadQuestions();
    }

}
