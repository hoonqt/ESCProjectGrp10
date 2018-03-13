package com.example.cindy.esc_50005.Database.UsersInformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-UsersInformation")

public class UsersInformationDO {
    private String _userType;
    private Double _userId;
    private String _password;
    private String _username;

    @DynamoDBHashKey(attributeName = "userType")
    @DynamoDBIndexHashKey(attributeName = "userType", globalSecondaryIndexName = "userType-userId")
    public String getUserType() {
        return _userType;
    }

    public void setUserType(final String _userType) {
        this._userType = _userType;
    }
    @DynamoDBRangeKey(attributeName = "userId")
    @DynamoDBIndexRangeKey(attributeName = "userId", globalSecondaryIndexName = "userType-userId")
    public Double getUserId() {
        return _userId;
    }

    public void setUserId(final Double _userId) {
        this._userId = _userId;
    }
    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return _password;
    }

    public void setPassword(final String _password) {
        this._password = _password;
    }
    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return _username;
    }

    public void setUsername(final String _username) {
        this._username = _username;
    }

}
