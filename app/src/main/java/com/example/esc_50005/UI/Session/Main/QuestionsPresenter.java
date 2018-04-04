package com.example.esc_50005.UI.Session.Main;

import android.support.annotation.NonNull;

import com.example.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.esc_50005.Database.Database.SessionQuestionsRemoteDataSource;
import com.example.esc_50005.Log;

import java.util.ArrayList;

import static com.google.common.base.Preconditions.checkNotNull;


public class QuestionsPresenter implements QuestionsContract.Presenter {

    public static final String TAG = "QuestionsPresenter";
    //temporary set session code to be as such
    private static final String SESSION_CODE="111";

    private final QuestionsContract.View mSessionQuestionView;
    private SessionQuestionsRemoteDataSource mSessionQuestionsRepository;
    ArrayList<SessionQuestionsDO> questionsJsonData;

    String courseId;
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
            mSessionQuestionView.questionsLoaded();
        }
    }


    public void upvoteQuestion(SessionQuestionsDO question) {
        ArrayList<String> usersVoted = question.getUsersVoted();
        if (!usersVoted.contains(userId)) {
            question.setUpvote(question.getUpvote() + 1);
            usersVoted.add(userId);
            question.setUsersVoted(usersVoted);
            mSessionQuestionsRepository.saveQuestion(question);
            Log.i(TAG, "upvote Faq" + question.getUpvote());
        }
//        tv_question.setUpvote(tv_question.getUpvote() + 1);
//        mSessionQuestionsRepository.saveQuestion(tv_question);
        loadQuestions();
    }

    public void downvoteQuestion(SessionQuestionsDO question) {
        ArrayList<String> usersVoted = question.getUsersVoted();
        if (usersVoted.contains(userId)) {
            question.setUpvote(question.getUpvote() - 1);
            usersVoted.remove(userId);
            question.setUsersVoted(usersVoted);
            mSessionQuestionsRepository.saveQuestion(question);
            Log.i(TAG, "downvote Faq" + question.getUpvote());
        }
//        tv_question.setUpvote(tv_question.getUpvote() - 1);
//        mSessionQuestionsRepository.saveQuestion(tv_question);
        loadQuestions();
    }

    @Override
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
