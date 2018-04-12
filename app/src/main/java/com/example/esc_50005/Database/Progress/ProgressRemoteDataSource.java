package com.example.esc_50005.Database.Progress;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ProgressRemoteDataSource implements ProgressDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<JSONObject> datainjson;
    ArrayList<QuizScores4DO> progressArrayList;
    ArrayList<QuizScores4DO> nameList;

    private static ProgressRemoteDataSource INSTANCE;

    public static ProgressRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProgressRemoteDataSource();
        }
        return INSTANCE;
    }

    public ProgressRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();


    }

    public void putScores(String studentID, String subjectcode,String sessionID,  String quizname, Double score, String name) {

        final QuizScores4DO quizscore = new QuizScores4DO();

        quizscore.setCourseID(subjectcode);
        quizscore.setStudentIDSessionID(studentID+sessionID);
        quizscore.setQuizName(quizname);
        quizscore.setScore(score);
        quizscore.setName(name);


        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(quizscore);
                // Item saved
            }
        }).start();


    }

    public ArrayList<QuizScores4DO> getScoresallStudents(final String subjectCode, final String sessionID) {

        progressArrayList = new ArrayList<QuizScores4DO>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                QuizScores4DO scores = new QuizScores4DO();
                scores.setCourseID(subjectCode);

                Condition rangeKeyCondition = new Condition()
                        .withComparisonOperator(ComparisonOperator.CONTAINS)
                        .withAttributeValueList(new AttributeValue().withS(sessionID));


                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(scores)
                        .withRangeKeyCondition("StudentIDSessionID",rangeKeyCondition)
                        .withConsistentRead(false);

                PaginatedList<QuizScores4DO> result = dynamoDBMapper.query(QuizScores4DO.class, queryExpression);

                for (QuizScores4DO score : result) {
                    progressArrayList.add(score);
                    Log.i("scores in prds","scores: " + score.getScore().toString());
                }

            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i("Progresslist in prds", "progressList" + progressArrayList.toString());

        return progressArrayList;

    }


    public ArrayList<QuizScores4DO> getScores(final String subjectCode, final String studentID) {

        progressArrayList = new ArrayList<QuizScores4DO>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                QuizScores4DO scores = new QuizScores4DO();
                scores.setCourseID(subjectCode);

                Condition rangeKeyCondition = new Condition()
                        .withComparisonOperator(ComparisonOperator.BEGINS_WITH)
                        .withAttributeValueList(new AttributeValue().withS(studentID));


                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(scores)
                        .withRangeKeyCondition("StudentIDSessionID",rangeKeyCondition)
                        .withConsistentRead(false);

                PaginatedList<QuizScores4DO> result = dynamoDBMapper.query(QuizScores4DO.class, queryExpression);

                for (QuizScores4DO score : result) {
                    progressArrayList.add(score);
                    Log.i("scores in prds","scores: " + score.getScore().toString());
                }

            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i("Progresslist in prds", "progressList" + progressArrayList.toString());

        return progressArrayList;

    }

    public ArrayList<QuizScores4DO> getNames(final String subjectCode) {
        final ArrayList<QuizScores4DO> output = new ArrayList<>();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                QuizScores4DO names = new QuizScores4DO();
                names.setCourseID(subjectCode);


                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(names);


                PaginatedList<QuizScores4DO> result = dynamoDBMapper.query(QuizScores4DO.class, queryExpression);

                for (QuizScores4DO name : result) {
                    if (!output.contains(output.add(name))) {
                        output.add(name);
                    }
                    Log.i("Names in prds","names: " + name.getName());
                }
                for (QuizScores4DO out: output){
                    Log.i("Names in prds","out: " + out.getName());

                }

            }
        });

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

//        Log.i("Progresslist in prds", "progressList" + progressArrayList.toString());

        return output;
    }


}
