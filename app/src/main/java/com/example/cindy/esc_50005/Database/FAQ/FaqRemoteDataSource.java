package com.example.cindy.esc_50005.Database.FAQ;

import android.util.Log;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.cindy.esc_50005.Database.Database.Question;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class FaqRemoteDataSource implements FaqDataSource {

    public static final String TAG = "FaqRemoteDataSource";

    DynamoDBMapper dynamoDBMapper;

    ArrayList<Faq> faqArrayList;

//    private ArrayList<JSONObject> dataInJson;

    public FaqRemoteDataSource() {
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void removeFaq(final Faq faq) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.delete(faq);
            }
        }).start();

    }

    @Override
    public void saveFaq(final Faq faq) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(faq);
            }
        }).start();
    }

    public ArrayList<Faq> getFaqListByCourseId(final String courseId) {

//        dataInJson = new ArrayList<>();

        faqArrayList = new ArrayList<Faq>();

        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                Faq courseSelected = new Faq();
                courseSelected.setCourseId(courseId);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(courseSelected);

                PaginatedList<Faq> result = dynamoDBMapper.query(Faq.class, queryExpression);

                for (Faq faq : result) {
                    faqArrayList.add(faq);
                    Log.i(TAG, faq.getQuestion());
                }

//                Gson gson = new Gson();
//                StringBuilder stringBuilder = new StringBuilder();
//
//                for (int i = 0;i<result.size();i++) {
//                    String jsonFormOfItem = gson.toJson(result.get(i));
//                    stringBuilder.append(jsonFormOfItem + "\n\n");
//
//                    try {
//                        dataInJson.add(new JSONObject(jsonFormOfItem));
//                    }
//
//                    catch (JSONException ex) {
//                        System.out.println(ex);
//            ""        }
//
//                }
//
//                Log.i(TAG,stringBuilder.toString());

            }
        });

        retriever.start();

        try {
            retriever.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        Log.i(TAG, "faqlist2" + faqArrayList.toString());

        return faqArrayList;

    }
}