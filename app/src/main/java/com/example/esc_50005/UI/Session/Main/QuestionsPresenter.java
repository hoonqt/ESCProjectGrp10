package com.example.esc_50005.UI.Session.Main;

import android.support.annotation.NonNull;

import com.example.esc_50005.Database.Database.Question;
import com.example.esc_50005.Database.Database.QuestionRemoteDataSource;
import com.example.esc_50005.Log;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class QuestionsPresenter implements QuestionsContract.Presenter {

    public static final String TAG = "QuestionsPresenter";

    private final QuestionsContract.View mSessionQuestionView;
    private QuestionRemoteDataSource mSessionQuestionsRepository;
    ArrayList<Question> mQuestionsList;

    String sessionId;
    String userId;

    public QuestionsPresenter(@NonNull QuestionsContract.View sessionQuestionsView) {
        mSessionQuestionsRepository = new QuestionRemoteDataSource();
        mSessionQuestionView = checkNotNull(sessionQuestionsView, "sessionQuestionView cannot be null!");
        mSessionQuestionView.setPresenter(this);
    }

    @Override
    public void start() {

        loadQuestions();
    }

    @Override
    public void loadQuestions() {
        mQuestionsList = mSessionQuestionsRepository.getQuestionsListBySessionId(sessionId);
        processQuestions(mQuestionsList);
    }

    @Override
    public void addNewQuestion(String question) {

        mSessionQuestionsRepository.addQuestion(question, sessionId);
        loadQuestions();

    }

    public void processQuestions(ArrayList<Question> questionsJsonData) {
        mSessionQuestionView.showQuestions(questionsJsonData);
        mSessionQuestionView.questionsLoaded();
    }


    public void upvoteQuestion(Question question) {
        List<String> usersVoted = question.getUsersVoted();
        if (!usersVoted.contains(userId)) {
            usersVoted.add(userId);
            question.setUsersVoted(usersVoted);
            mSessionQuestionsRepository.saveQuestion(question);
            Log.i(TAG, "upvote Faq" + usersVoted.size());
        }
        loadQuestions();
    }

    public void downvoteQuestion(Question question) {
        List<String> usersVoted = question.getUsersVoted();
        if (usersVoted.contains(userId)) {
            usersVoted.remove(userId);
            question.setUsersVoted(usersVoted);
            mSessionQuestionsRepository.saveQuestion(question);
            Log.i(TAG, "downvote Faq" + usersVoted.size());
        }
        loadQuestions();
    }

    @Override
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
