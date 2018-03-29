package com.example.esc_50005.Database.activityQuestion;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-activityQuestions")

public class ActivityQuestionsDO {
    private String _courseID;
    private String _sessionNO;
    private Double _correctOption;
    private List<String> _options;
    private String _question;

    @DynamoDBHashKey(attributeName = "courseID")
    @DynamoDBAttribute(attributeName = "courseID")
    public String getCourseID() {
        return _courseID;
    }

    public void setCourseID(final String _courseID) {
        this._courseID = _courseID;
    }
    @DynamoDBRangeKey(attributeName = "sessionNO")
    @DynamoDBIndexHashKey(attributeName = "sessionNO", globalSecondaryIndexName = "questions")
    public String getSessionNO() {
        return _sessionNO;
    }

    public void setSessionNO(final String _sessionNO) {
        this._sessionNO = _sessionNO;
    }
    @DynamoDBAttribute(attributeName = "correctOption")
    public Double getCorrectOption() {
        return _correctOption;
    }

    public void setCorrectOption(final Double _correctOption) {
        this._correctOption = _correctOption;
    }
    @DynamoDBAttribute(attributeName = "options")
    public List<String> getOptions() {
        return _options;
    }

    public void setOptions(final List<String> _options) {
        this._options = _options;
    }
    @DynamoDBIndexRangeKey(attributeName = "question", globalSecondaryIndexName = "questions")
    public String getQuestion() {
        return _question;
    }

    public void setQuestion(final String _question) {
        this._question = _question;
    }

}
