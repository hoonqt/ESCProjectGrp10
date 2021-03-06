package com.example.esc_50005.Database.FAQ;

import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;

public class FaqRemoteDataSource implements FaqDataSource {

    public static final String TAG = "FaqRemoteDataSource";

    DynamoDBMapper dynamoDBMapper;

    ArrayList<Faq> mFaqList;

    private static FaqRemoteDataSource INSTANCE;

//    private ArrayList<JSONObject> dataInJson;

    public static FaqRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FaqRemoteDataSource();
        }
        return INSTANCE;
    }

    public FaqRemoteDataSource() {
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void deleteFaq(final Faq faq) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.delete(faq);
            }
        }).start();

    }

    // Not allowed
//    @Override
//    public void deleteAllFaqs() {
//    }

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

        mFaqList = new ArrayList<Faq>();

        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                Faq courseSelected = new Faq();
                courseSelected.setCourseId(courseId);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(courseSelected);

                PaginatedList<Faq> result = dynamoDBMapper.query(Faq.class, queryExpression);

                for (Faq faq : result) {
                    mFaqList.add(faq);
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

        Log.i(TAG, "faqlist2" + mFaqList.toString());

        return mFaqList;

    }
}