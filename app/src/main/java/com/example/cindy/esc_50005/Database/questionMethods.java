package com.example.cindy.esc_50005.Database;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by hoonqt on 20/2/18.
 */

public class questionMethods {

    DynamoDBMapper dynamoDBMapper;

    public void postQuestion(String question, String sessionCode) {

        final NewfaqDO newQuestion = new NewfaqDO();

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
        final NewfaqDO newQuestion = new NewfaqDO();

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
        final NewfaqDO newQuestion = new NewfaqDO();

        newQuestion.setSessioncode(sessionCode);
        ArrayList<String> currentanswers = new ArrayList<>();
        newQuestion.setAnswers(currentanswers);
        currentanswers.add(answer);
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

        new Thread(new Runnable() {
            @Override
            public void run() {
                NewfaqDO faq = new NewfaqDO();
                faq.setSessioncode(sessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(faq);

                PaginatedList<NewfaqDO> result = dynamoDBMapper.query(NewfaqDO.class,queryExpression);

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
