package com.example.esc_50005.Database.Database;

import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;

public class SessionQuestionsRemoteDataSource implements SessionQuestionsDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<SessionQuestionsDO> questionsArrayList;
    public static final String TAG = "QuestionsRemote";

    public SessionQuestionsRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void addQuestion(String question, String sessionCode) {
        Log.i("question",question);

        final SessionQuestionsDO newQuestion = new SessionQuestionsDO();

        newQuestion.setSessionId(sessionCode);
        newQuestion.setAnswer(null);
        newQuestion.setQuestion(question);
        newQuestion.setUpvotes(0);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newQuestion);
                // Item saved
            }
        }).start();


    }

    @Override
    public void removeQuestion(String question, String sessionCode) {

        final SessionQuestionsDO deleteQn = new SessionQuestionsDO();
        deleteQn.setSessionId(sessionCode);
        deleteQn.setQuestion(question);

        new Thread(new Runnable() {
            @Override
            public void run() {

                dynamoDBMapper.delete(deleteQn);

            }
        }).start();

    }

    public void saveQuestion(final SessionQuestionsDO question) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(question);
            }
        }).start();
    }


    @Override
    public void updateQuestion(String oldQuestion, String sessionCode, String newQuestion) {

    }

    @Override
    public ArrayList<SessionQuestionsDO> getQuestionsListBySessionId(final String sessionCode) {

        questionsArrayList=new ArrayList<>();
        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                SessionQuestionsDO sessionSelected = new SessionQuestionsDO();
                sessionSelected.setSessionId(sessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(sessionSelected);

                PaginatedList<SessionQuestionsDO> result = dynamoDBMapper.query(SessionQuestionsDO.class,queryExpression);

                for (SessionQuestionsDO question : result) {
                    questionsArrayList.add(question);
                    Log.i(TAG, question.getQuestion());
                }

            }
        });

        retriever.start();

        try {
            retriever.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i(TAG, "questionsArrayList" + questionsArrayList.toString());

        return questionsArrayList;

    }

    @Override
    public void findQuestionsById() {

    }


}