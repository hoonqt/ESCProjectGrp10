package com.example.esc_50005.Database.Database;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;

public class QuestionPresenter {

    DynamoDBMapper dynamoDBMapper;

    public void loadQuestions(final String sessionCode) {

        QuestionRemoteDataSource questionLoader = new QuestionRemoteDataSource();
//        questionLoader.getQuestionsListBySessionId(sessionCode);
        questionLoader.getQuestionsListBySessionId(sessionCode);
    };

    public void addNewQuestion(String question, String sessionCode) {

        QuestionRemoteDataSource questionAdder = new QuestionRemoteDataSource();
        questionAdder.addQuestion(question,sessionCode);

    };

    public void processEmptyQuestions() {

    };

    public void upvoteQuestion(String question, String sessionCode) {

        final Question newQuestion = new Question();

        newQuestion.setSessionId(sessionCode);
        newQuestion.setQuestion(question);
        newQuestion.setUpvotes(newQuestion.getUpvotes()+1);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
                // Item saved
            }
        }).start();

    };


}
