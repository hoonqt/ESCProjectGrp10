package com.example.cindy.esc_50005.Database.feedback;

import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by Otter on 22/3/2018.
 */

public class FeedbackRemoteDataSource implements FeedbackDataSource {
    public static final String TAG = "FaqRemoteDataSource";

    DynamoDBMapper dynamoDBMapper;

    ArrayList<Feedback> feedbackArrayList;

//    private ArrayList<JSONObject> dataInJson;

    public FeedbackRemoteDataSource() {
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void removeFeedback(final Feedback feedback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.delete(feedback);
            }
        }).start();

    }

    @Override
    public void saveFeedback(final Feedback feedback) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(feedback);
            }
        }).start();
    }

    public ArrayList<Feedback> getFeedbackListBySessionId(final String sessionId) {

//        dataInJson = new ArrayList<>();

        feedbackArrayList = new ArrayList<Feedback>();

        new Thread(new Runnable() {
            @Override
            public void run() {

                Feedback courseSelected = new Feedback();
                courseSelected.setSessionId(sessionId);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(courseSelected);

                PaginatedList<Feedback> result = dynamoDBMapper.query(Feedback.class, queryExpression);

                for (Feedback feedback : result) {
                    feedbackArrayList.add(feedback);
                    Log.i(TAG, feedback.getComment());
                }

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i(TAG, "feedbacklist2" + feedbackArrayList.toString());

        return feedbackArrayList;

    }
}
