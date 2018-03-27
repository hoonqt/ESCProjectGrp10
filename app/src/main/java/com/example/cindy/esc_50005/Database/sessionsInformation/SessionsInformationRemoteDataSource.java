//package com.example.cindy.esc_50005.Database.sessionsInformation;
//
//import android.util.Log;
//
//import com.amazonaws.mobile.client.AWSMobileClient;
//import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
//import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
//import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
//import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDO;
//import com.example.cindy.esc_50005.Database.Database.SessionQuestionsDataSource;
//
//import java.util.ArrayList;
//import java.util.concurrent.TimeUnit;
//
//public class SessionsInformationRemoteDataSource implements SessionsInformationDataSource {
//
//    DynamoDBMapper dynamoDBMapper;
//    ArrayList<SessionsInformationDO> sessionsArrayList;
//    public static final String TAG = "QuestionsRemote";
//
//    public SessionsInformationRemoteDataSource() {
//
//        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
//        this.dynamoDBMapper = DynamoDBMapper.builder()
//                .dynamoDBClient(dynamoDBClient)
//                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
//                .build();
//    }
//
//    @Override
//    public void removeSession(SessionsInformationDO faq) {
//
//    }
//
//    @Override
//    public void addSession(Double courseId, String courseName) {
//
//    }
//
//    @Override
//    public ArrayList<SessionsInformationDO> querySessions(Double sessionId, Double courseId ) {
//        sessionsArrayList=new ArrayList<>();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                SessionQuestionsDO sessionSelected = new SessionQuestionsDO();
//                sessionSelected.setSessioncode(sessionCode);
//
//                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
//                        .withHashKeyValues(sessionSelected);
//
//                PaginatedList<SessionQuestionsDO> result = dynamoDBMapper.query(SessionQuestionsDO.class,queryExpression);
//
//                for (SessionQuestionsDO question : result) {
//                    sessionsArrayList.add(question);
//                    Log.i(TAG, question.getQuestion());
//                }
//
//            }
//        }).start();
//
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
//
//        Log.i(TAG, "questionsArrayList" + sessionsArrayList.toString());
//
//        return sessionsArrayList;
//    }
//}