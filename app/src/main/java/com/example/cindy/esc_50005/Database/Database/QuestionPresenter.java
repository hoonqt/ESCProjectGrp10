package com.example.cindy.esc_50005.Database.Database;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

/**
 * Created by hoonqt on 1/3/18.
 */

public class QuestionPresenter implements QuestionContract {

    DynamoDBMapper dynamoDBMapper;

    public void loadQuestions(final String sessionCode) {

        SessionQuestionsRemoteDataSource questionLoader = new SessionQuestionsRemoteDataSource();
        questionLoader.getQuestionsList(sessionCode);
<<<<<<< HEAD:app/src/main/java/com/example/cindy/esc_50005/Database/QuestionPresenter.java

=======
>>>>>>> db7fd3ef14720d3f81d027dbfd7b5b166e4b2de8:app/src/main/java/com/example/cindy/esc_50005/Database/Database/QuestionPresenter.java
    };

    public void addNewQuestion(String question, String sessionCode) {

        SessionQuestionsRemoteDataSource questionAdder = new SessionQuestionsRemoteDataSource();
        questionAdder.addQuestion(question,sessionCode);

    };

    public void processEmptyQuestions() {

    };

    public void upvoteQuestion(String question, String sessionCode) {

        final SessionQuestionsDO newQuestion = new SessionQuestionsDO();

        newQuestion.setSessioncode(sessionCode);
        newQuestion.setQuestion(question);
        newQuestion.setUpvote(newQuestion.getUpvote()+1.0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
                // Item saved
            }
        }).start();

    };


}
