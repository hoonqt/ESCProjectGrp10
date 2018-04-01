package com.example.esc_50005.Database.UsersInformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;


@DynamoDBTable(tableName = "escproject-mobilehub-27166461-UsersInformation")

public class UsersInformationDO {
    private String _userType;
    private Double _userId;
    private List<String> _courseIds;
    private List<String> _courseNames;
    private String _password;
    private String _securityAnswer;
    private List<String> _sessionDate;
    private List<String> _sessionIds;
    private List<String> _sessionName;
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
    @DynamoDBAttribute(attributeName = "courseIds")
    public List<String> getCourseIds() {
        return _courseIds;
    }

    public void setCourseIds(final List<String> _courseIds) {
        this._courseIds = _courseIds;
    }
    @DynamoDBAttribute(attributeName = "courseNames")
    public List<String> getCourseNames() {
        return _courseNames;
    }

    public void setCourseNames(final List<String> _courseNames) {
        this._courseNames = _courseNames;
    }
    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return _password;
    }

    public void setPassword(final String _password) {
        this._password = _password;
    }
    @DynamoDBAttribute(attributeName = "securityAnswer")
    public String getSecurityAnswer() {
        return _securityAnswer;
    }

    public void setSecurityAnswer(final String _securityAnswer) {
        this._securityAnswer = _securityAnswer;
    }
    @DynamoDBAttribute(attributeName = "sessionDate")
    public List<String> getSessionDate() {
        return _sessionDate;
    }

    public void setSessionDate(final List<String> _sessionDate) {
        this._sessionDate = _sessionDate;
    }
    @DynamoDBAttribute(attributeName = "sessionIds")
    public List<String> getSessionIds() {
        return _sessionIds;
    }

    public void setSessionIds(final List<String> _sessionIds) {
        this._sessionIds = _sessionIds;
    }
    @DynamoDBAttribute(attributeName = "sessionName")
    public List<String> getSessionName() {
        return _sessionName;
    }

    public void setSessionName(final List<String> _sessionName) {
        this._sessionName = _sessionName;
    }
    @DynamoDBAttribute(attributeName = "username")
    public String getUsername() {
        return _username;
    }

    public void setUsername(final String _username) {
        this._username = _username;
    }

}
