package com.example.cindy.esc_50005.Database.FAQ;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-faqQuestion")

public class FaqQuestionDO {
    private String _courseID;
    private String _question;
    private String _date;
    private String _answer;
    private Double _upVotes;
    private String _userId;

    @DynamoDBHashKey(attributeName = "courseID")
    @DynamoDBAttribute(attributeName = "courseID")
    public String getCourseID() {
        return _courseID;
    }

    public void setCourseID(final String _courseID) {
        this._courseID = _courseID;
    }
    @DynamoDBRangeKey(attributeName = "question")
    @DynamoDBAttribute(attributeName = "question")
    public String getQuestion() {
        return _question;
    }

    public void setQuestion(final String _question) {
        this._question = _question;
    }
    @DynamoDBAttribute(attributeName = "Date")
    public String getDate() {
        return _date;
    }

    public void setDate(final String _date) {
        this._date = _date;
    }
    @DynamoDBAttribute(attributeName = "answer")
    public String getAnswer() {
        return _answer;
    }

    public void setAnswer(final String _answer) {
        this._answer = _answer;
    }
    @DynamoDBAttribute(attributeName = "upVotes")
    public Double getUpVotes() {
        return _upVotes;
    }

    public void setUpVotes(final Double _upVotes) {
        this._upVotes = _upVotes;
    }
    @DynamoDBAttribute(attributeName = "userId")
    public String getUserId() {
        return _userId;
    }

    public void setUserId(final String _userId) {
        this._userId = _userId;
    }

}
