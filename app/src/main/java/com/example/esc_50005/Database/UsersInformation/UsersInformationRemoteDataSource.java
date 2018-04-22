package com.example.esc_50005.Database.UsersInformation;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

import java.util.ArrayList;

public class UsersInformationRemoteDataSource implements UsersInformationDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<UsersInformationDO> usersArrayList;
    public static final String TAG = "UsersInformationRemote";

    private static UsersInformationRemoteDataSource INSTANCE;

    public static UsersInformationRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UsersInformationRemoteDataSource();
        }
        return INSTANCE;
    }

    public UsersInformationRemoteDataSource() {
        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void removeUser(final UsersInformationDO usersInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.delete(usersInformation);
            }
        }).start();


    }

    @Override
    public void addUser(final UsersInformationDO userInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(userInformation);
            }
        }).start();

    }



    public void editUser(String course) {

    }
    public ArrayList<UsersInformationDO> queryAParticularUser(final String userId) {
        Log.i("here at query","here at query");
        usersArrayList = new ArrayList<UsersInformationDO>();

        Thread random = new Thread(new Runnable() {
            @Override
            public void run() {

                UsersInformationDO userSelected = new UsersInformationDO();
               userSelected.setUserId(userId);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(userSelected);

                PaginatedList<UsersInformationDO> result = dynamoDBMapper.query(UsersInformationDO.class, queryExpression);
                for (UsersInformationDO userInformation : result) {
                    Log.i("user id",userInformation.getUserId());
                    if(userInformation.getUserId().equals(userId))
                    {
                        usersArrayList.add(userInformation);
                    }
                }



            }
        });

        random.start();

        try {
            random.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return usersArrayList;

    }


    public ArrayList<UsersInformationDO> queryAllUsers(final String userId, final String fullName) {
        usersArrayList = new ArrayList<UsersInformationDO>();

        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                UsersInformationDO userSelected = new UsersInformationDO();
                userSelected.setUserId(userId);
                userSelected.setFullName(fullName);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(userSelected);

                PaginatedList<UsersInformationDO> result = dynamoDBMapper.query(UsersInformationDO.class, queryExpression);
                for (UsersInformationDO userInformation : result) {
                    usersArrayList.add(userInformation);
                }



            }
        });

        retriever.start();

        try {
            retriever.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return usersArrayList;
    }


}