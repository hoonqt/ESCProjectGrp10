package com.example.cindy.esc_50005.Database.UsersInformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-UsersInformation")

public class UsersInformation {
    public int userId;
    public String userType;
    public String password;
    public String username;

    @DynamoDBHashKey(attributeName = "userType")
    @DynamoDBAttribute(attributeName = "userType")
    public String getUserType() {
        return userType;
    }
    public void setUserType(final String userType) {
        this.userType = userType;
    }

    @DynamoDBRangeKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public int getUserId() {
        return userId;
    }
    public void setUserId(final int userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return username;
    }
    public void setUsername(final String username) {
        this.username = username;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(final String password) {
        this.password = password;
    }


}
