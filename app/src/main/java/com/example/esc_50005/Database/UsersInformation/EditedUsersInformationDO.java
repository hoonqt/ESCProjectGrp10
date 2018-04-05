package com.example.esc_50005.Database.UsersInformation;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;


@DynamoDBTable(tableName = "escproject-mobilehub-27166461-editedUsersInformation")

public class EditedUsersInformationDO {
    private String _userId;
    private String _fullName;
    private String _bruteForceCount;
    private List<String> _courseIds;
    private List<String> _courseNames;
    private Boolean _disabled;
    private String _password;
    private String _securityAnswer;
    private List<String> _sessionDates;
    private List<String> _sessionIds;
    private List<String> _sessionNames;
    private String _userType;

    @DynamoDBHashKey(attributeName = "userId")
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }
    @DynamoDBRangeKey(attributeName = "fullName")
    @DynamoDBAttribute(attributeName = "fullName")
    public String getFullName() {
        return _fullName;
    }

    public void setFullName(final String _fullName) {
        this._fullName = _fullName;
    }
    @DynamoDBAttribute(attributeName = "bruteForceCount")
    public String getBruteForceCount() {
        return _bruteForceCount;
    }

    public void setBruteForceCount(final String _bruteForceCount) {
        this._bruteForceCount = _bruteForceCount;
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
    @DynamoDBAttribute(attributeName = "disabled")
    public Boolean getDisabled() {
        return _disabled;
    }

    public void setDisabled(final Boolean _disabled) {
        this._disabled = _disabled;
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
    @DynamoDBAttribute(attributeName = "sessionDates")
    public List<String> getSessionDates() {
        return _sessionDates;
    }

    public void setSessionDates(final List<String> _sessionDates) {
        this._sessionDates = _sessionDates;
    }
    @DynamoDBAttribute(attributeName = "sessionIds")
    public List<String> getSessionIds() {
        return _sessionIds;
    }

    public void setSessionIds(final List<String> _sessionIds) {
        this._sessionIds = _sessionIds;
    }
    @DynamoDBAttribute(attributeName = "sessionNames")
    public List<String> getSessionNames() {
        return _sessionNames;
    }

    public void setSessionNames(final List<String> _sessionNames) {
        this._sessionNames = _sessionNames;
    }
    @DynamoDBAttribute(attributeName = "userType")
    public String getUserType() {
        return _userType;
    }

    public void setUserType(final String _userType) {
        this._userType = _userType;
    }

}
