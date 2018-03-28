package com.example.cindy.esc_50005.Database.CoursesInformation;


import android.util.Log;

import com.amazonaws.mobile.client.AWSMobileClient;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBQueryExpression;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.PaginatedList;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformation;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDataSource;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CoursesInformationRemoteDataSource implements CoursesInformationDataSource {

    DynamoDBMapper dynamoDBMapper;
    ArrayList<CoursesInformationDO> coursesArrayList;
    public static final String TAG = "CoursesInformationRemote";

    public CoursesInformationRemoteDataSource() {

        AmazonDynamoDBClient dynamoDBClient = new AmazonDynamoDBClient(AWSMobileClient.getInstance().getCredentialsProvider());
        this.dynamoDBMapper = DynamoDBMapper.builder()
                .dynamoDBClient(dynamoDBClient)
                .awsConfiguration(AWSMobileClient.getInstance().getConfiguration())
                .build();
    }

    @Override
    public void addCourse(final Double courseId, final String courseName) {
        final CoursesInformationDO newCourse=new CoursesInformationDO();
        newCourse.setCourseID(courseId);
        newCourse.setCourseName(courseName);
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.save(newCourse);
            }
        }).start();

    }

    @Override
    public void removeCourse(final CoursesInformationDO userInformation) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                dynamoDBMapper.delete(userInformation);
            }
        }).start();

    }

    public ArrayList<CoursesInformationDO> queryCourses(final Double courseId, final String courseName) {

        coursesArrayList = new ArrayList<CoursesInformationDO>();

        Thread retriever = new Thread(new Runnable() {
            @Override
            public void run() {

                CoursesInformationDO courseSelected = new CoursesInformationDO();

                courseSelected.setCourseID(courseId);
                courseSelected.setCourseName(courseName);

                DynamoDBQueryExpression queryExpression = new DynamoDBQueryExpression()
                        .withHashKeyValues(courseSelected);

                PaginatedList<CoursesInformationDO> result = dynamoDBMapper.query(CoursesInformationDO.class, queryExpression);

                for (CoursesInformationDO courseInformation : result) {
                    coursesArrayList.add(courseInformation);

                    //You gonna have to change the way you retrive stuff here.
                }


            }
        });

        retriever.start();

        try {
            retriever.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        return coursesArrayList;
    }


}