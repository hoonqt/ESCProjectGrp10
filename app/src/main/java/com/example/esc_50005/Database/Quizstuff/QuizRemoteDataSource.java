package com.example.esc_50005.Database.Quizstuff;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by hoonqt on 27/3/18.
 */

public class QuizRemoteDataSource {
    DynamoDBMapper dynamoDBMapper;
    ArrayList<QuizQuestions2DO> questionsArrayList;

    private static QuizRemoteDataSource INSTANCE;

    public static QuizRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new QuizRemoteDataSource();
        }
        return INSTANCE;
    }

    public QuizRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();

    }

    public void putQuestion(final QuizQuestions2DO input) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(input);
                // Item saved
            }
        }).start();

    }

    public void putQuestion(String subjCode, String sessionCode, String quizName,String question, Double correctAns, ArrayList<String> options) {

        final QuizQuestions2DO adder = new QuizQuestions2DO();

        adder.setSubjectCodeSessionCode(subjCode+sessionCode);
        adder.setQuizNameQnID(quizName + " " + getSaltString());
        adder.setCorrectans(correctAns);
        adder.setOptions(options);
        adder.setQuestion(question);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(adder);
                // Item saved
            }
        }).start();

    }
    
    public ArrayList<QuizQuestions2DO> getQuestions(final String subjCode, final String sessionCode) {
        
        questionsArrayList = new ArrayList<QuizQuestions2DO>();

        Thread random = new Thread(new Runnable() {
            @Override
            public void run() {

                final QuizQuestions2DO adder = new QuizQuestions2DO();
                adder.setSubjectCodeSessionCode(subjCode+sessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(adder);

                PaginatedList<QuizQuestions2DO> result = dynamoDBMapper.query(QuizQuestions2DO.class, queryExpression);

                for (QuizQuestions2DO score : result) {
                    questionsArrayList.add(score);
                }

            }
        });

        random.start();

        try {
            random.join();
        }

        catch (InterruptedException ex) {
        }



        return questionsArrayList;
    }

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }


}
