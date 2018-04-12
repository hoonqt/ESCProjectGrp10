package com.example.esc_50005.UI.Session.Main;

import android.support.annotation.NonNull;

import com.example.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import com.example.esc_50005.Log;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;


public class QuestionsPresenter implements QuestionsContract.Presenter {

    public static final String TAG = "QuestionsPresenter";

    private final QuestionsContract.View mSessionQuestionView;
    private SessionQuestionsRemoteDataSource mSessionQuestionsRepository;
    ArrayList<SessionQuestionsDO> questionsJsonData;

    String sessionId;
    String userId;

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
        questionsJsonData = mSessionQuestionsRepository.getQuestionsListBySessionId(sessionId);
        processQuestions(questionsJsonData);
    }

    @Override
    public void addNewQuestion(String question) {

        mSessionQuestionsRepository.addQuestion(question, sessionId);
        loadQuestions();

    }

    @Override
    public void processEmptyQuestion() {

    }

    public void processQuestions(ArrayList<SessionQuestionsDO> questionsJsonData) {
        mSessionQuestionView.showAddedQuestion(questionsJsonData);
        mSessionQuestionView.questionsLoaded();
    }


    public void upvoteQuestion(SessionQuestionsDO question) {
        List<String> usersVoted = question.getUsersVoted();
        if (!usersVoted.contains(userId)) {
//            question.setUpvotes(question.getUpvotes() + 1);
            usersVoted.add(userId);
            question.setUsersVoted(usersVoted);
            mSessionQuestionsRepository.saveQuestion(question);
            Log.i(TAG, "upvote Faq" + usersVoted.size());
        }
//        tv_question.setUpvote(tv_question.getUpvote() + 1);
//        mSessionQuestionsRepository.saveQuestion(tv_question);
        loadQuestions();
    }

    public void downvoteQuestion(SessionQuestionsDO question) {
        List<String> usersVoted = question.getUsersVoted();
        if (usersVoted.contains(userId)) {
//            question.setUpvotes(question.getUpvotes() - 1);
            usersVoted.remove(userId);
            question.setUsersVoted(usersVoted);
            mSessionQuestionsRepository.saveQuestion(question);
            Log.i(TAG, "downvote Faq" + usersVoted.size());
        }
//        tv_question.setUpvote(tv_question.getUpvote() - 1);
//        mSessionQuestionsRepository.saveQuestion(tv_question);
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
