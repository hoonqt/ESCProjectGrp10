package com.example.esc_50005.Database.sessionsInformation;

import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;

import java.util.ArrayList;

public class SessionsInformationRemoteDataSource implements SessionsInformationDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<SessionsInformationDO> sessionsArrayList;
    public static final String TAG = "QuestionsRemote";

    private static SessionsInformationRemoteDataSource INSTANCE;

//    private ArrayList<JSONObject> dataInJson;

    public static SessionsInformationRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SessionsInformationRemoteDataSource();
        }
        return INSTANCE;
    }


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
        newSessionToAdd.setSessionId(sessionId);
        newSessionToAdd.setSessionName(sessionName);
        newSessionToAdd.setCourseId(courseId);
        newSessionToAdd.setSessionDate(dateOfCreation);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newSessionToAdd);
            }
        }).start();
    }


    @Override
    public ArrayList<SessionsInformationDO> querySessions(final String sessionId ) {
        sessionsArrayList=new ArrayList<>();
        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                SessionsInformationDO sessionSelected = new SessionsInformationDO();
                sessionSelected.setSessionId(sessionId);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(sessionSelected);

                PaginatedList<SessionsInformationDO> result = dynamoDBMapper.query(SessionsInformationDO.class,queryExpression);

                for (SessionsInformationDO session : result) {
                    sessionsArrayList.add(session);
                }

            }
        });

        retriever.start();

        try {
            retriever.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i(TAG, "questionsArrayList" + sessionsArrayList.toString());

        return sessionsArrayList;
    }
}