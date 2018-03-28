package com.example.cindy.esc_50005.Database.sessionsInformation;

import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDataSource;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class SessionsInformationRemoteDataSource implements SessionsInformationDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<SessionsInformationDO> sessionsArrayList;
    public static final String TAG = "QuestionsRemote";

    public SessionsInformationRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void removeSession(SessionsInformationDO faq) {

    }

    @Override
    public void addSession(String sessionId, String sessionName, String dateOfCreation, String courseId) {
        final SessionsInformationDO newSessionToAdd= new SessionsInformationDO();
        newSessionToAdd.setSessionID(sessionId);
        newSessionToAdd.setSessionName(sessionName);
        newSessionToAdd.setCourseID(courseId);
        newSessionToAdd.setDateOfCreation(dateOfCreation);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newSessionToAdd);
            }
        }).start();
    }


    @Override
    public ArrayList<SessionsInformationDO> querySessions(final String sessionId, String courseId ) {
        sessionsArrayList=new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {

                SessionsInformationDO sessionSelected = new SessionsInformationDO();
                sessionSelected.setSessionID(sessionId);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(sessionSelected);

                PaginatedList<SessionsInformationDO> result = dynamoDBMapper.query(SessionQuestionsDO.class,queryExpression);

                for (SessionsInformationDO session : result) {
                    sessionsArrayList.add(session);
                }

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i(TAG, "questionsArrayList" + sessionsArrayList.toString());

        return sessionsArrayList;
    }
}