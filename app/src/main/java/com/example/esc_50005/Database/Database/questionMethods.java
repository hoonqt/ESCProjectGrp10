package com.example.esc_50005.Database.Database;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.google.gson.Gson;

import java.util.ArrayList;

public class questionMethods {

    DynamoDBMapper dynamoDBMapper;

    public void postQuestion(String question, String sessionCode) {

        final SessionQuestionsDO newQuestion = new SessionQuestionsDO();

        newQuestion.setSessioncode(sessionCode);
        newQuestion.setAnswers(new ArrayList<String>());
        newQuestion.setQuestion(question);
        newQuestion.setUpvote(0.0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
                // Item saved
            }
        }).start();

    }

    public void upvoteQn(String question, String sessionCode) {
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
    }

    //Answers are all stored in an ArrayList, we can add more complications later

    public void answerQn(String question, String sessionCode, String answer) {
        final SessionQuestionsDO newQuestion = new SessionQuestionsDO();

        newQuestion.setSessioncode(sessionCode);
        ArrayList<String> currentAnswers = new ArrayList<>();
        newQuestion.setAnswers(currentAnswers);
        currentAnswers.add(answer);
        newQuestion.setQuestion(question);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
                // Item saved
            }
        }).start();
    }


    //Process to get all the entries in the database for  certain session code

    public void queryBase(final String sessionCode) {
        String result;
        new Thread(new Runnable() {
            @Override
            public void run() {
                SessionQuestionsDO faq = new SessionQuestionsDO();
                faq.setSessioncode(sessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(faq);

                PaginatedList<SessionQuestionsDO> result = dynamoDBMapper.query(SessionQuestionsDO.class,queryExpression);

                Gson gson = new Gson();
                StringBuilder stringBuilder = new StringBuilder();

                for (int i = 0;i<result.size();i++) {
                    String jsonFormOfItem = gson.toJson(result.get(i));
                    stringBuilder.append(jsonFormOfItem + "\n\n");
                }

            }
        }).start();

    }
}
