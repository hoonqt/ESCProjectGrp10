package com.example.esc_50005.Database.Quizstuff;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;

/**
 * Created by hoonqt on 27/3/18.
 */

public class QuizRemoteDataSource {
    DynamoDBMapper dynamoDBMapper;
    ArrayList<QuizQuestions1DO> questionsArrayList;

    public QuizRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();

    }

    public void putQuestion(String subjCode, String sessionCode, String quizName,String question, String correctAns, ArrayList<String> options) {

        final QuizQuestions1DO adder = new QuizQuestions1DO();

        adder.setSubjectCodeSessionCode(subjCode+sessionCode);
        adder.setQuizName(quizName);
        adder.setCorrectans(correctAns);
        adder.setOptions(options);
        adder.setResults(new ArrayList<String>());
        adder.setQuestion(question);

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(adder);
                // Item saved
            }
        }).start();

    }
    
    public ArrayList<QuizQuestions1DO> getQuestions(final String subjCode, final String sessionCode) {
        
        questionsArrayList = new ArrayList<QuizQuestions1DO>();

        Thread random = new Thread(new Runnable() {
            @Override
            public void run() {

                final QuizQuestions1DO adder = new QuizQuestions1DO();
                adder.setSubjectCodeSessionCode(subjCode+sessionCode);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(adder);

                PaginatedList<QuizQuestions1DO> result = dynamoDBMapper.query(QuizQuestions1DO.class, queryExpression);

                for (QuizQuestions1DO score : result) {
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


}
