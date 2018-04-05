package com.example.esc_50005.Database.UsersInformation;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;

import java.util.ArrayList;

public class UsersInformationRemoteDataSource implements UsersInformationDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<EditedUsersInformationDO> usersArrayList;
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
    public void removeUser(final EditedUsersInformationDO usersInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.delete(usersInformation);
            }
        }).start();


    }

    @Override
    public void addUser(final EditedUsersInformationDO userInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(userInformation);
            }
        }).start();

    }



    public void editUser(String course) {

    }
    public ArrayList<EditedUsersInformationDO> queryAParticularUser(final String userId) {
        Log.i("here at query","here at query");
        Log.i("user id received",userId);
        usersArrayList = new ArrayList<EditedUsersInformationDO>();

        Thread random = new Thread(new Runnable() {
            @Override
            public void run() {

                EditedUsersInformationDO userSelected = new EditedUsersInformationDO();
               userSelected.setUserId(userId);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(userSelected);

                PaginatedList<EditedUsersInformationDO> result = dynamoDBMapper.query(EditedUsersInformationDO.class, queryExpression);
                for (EditedUsersInformationDO userInformation : result) {
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


    public ArrayList<EditedUsersInformationDO> queryAllUsers(final String userId, final String fullName) {
        usersArrayList = new ArrayList<EditedUsersInformationDO>();

        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                EditedUsersInformationDO userSelected = new EditedUsersInformationDO();
                userSelected.setUserId(userId);
                userSelected.setFullName(fullName);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(userSelected);

                PaginatedList<EditedUsersInformationDO> result = dynamoDBMapper.query(EditedUsersInformationDO.class, queryExpression);
                for (EditedUsersInformationDO userInformation : result) {
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