package com.example.esc_50005.Database.Database;

import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;

public class QuestionRemoteDataSource implements QuestionDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<Question> questionsArrayList;

    public QuestionRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void addQuestion(String question, String sessionCode) {
        final Question newQuestion = new Question();

        newQuestion.setSessionId(sessionCode);
        newQuestion.setAnswer(null);
        newQuestion.setQuestion(question);
        newQuestion.setUpvotes(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
            }
        }).start();
    }

    @Override
    public void removeQuestion(String question, String sessionCode) {

        final Question deleteQn = new Question();
        deleteQn.setSessionId(sessionCode);
        deleteQn.setQuestion(question);

        new Thread(new Runnable() {
            @Override
            public void run() {

                dynamoDBMapper.delete(deleteQn);

            }
        }).start();

    }

    public void saveQuestion(final Question question) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(question);
            }
        }).start();
    }

    @Override
    public ArrayList<Question> getQuestionsListBySessionId(final String sessionCode) {

        questionsArrayList=new ArrayList<>();
        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                Question sessionSelected = new Question();
                sessionSelected.setSessionId(sessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(sessionSelected);

                PaginatedList<Question> result = dynamoDBMapper.query(Question.class,queryExpression);

                for (Question question : result) {
                    questionsArrayList.add(question);
                }

            }
        });

        retriever.start();

        try {
            retriever.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return questionsArrayList;

    }

}