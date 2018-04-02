package com.example.esc_50005.Database.Quizstuff;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBIndexRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-QuizQuestions2")

public class QuizQuestions2DO {
    private String _subjectCodeSessionCode;
    private String _quizNameQnID;
    private Double _correctans;
    private List<String> _options;
    private String _question;

    @DynamoDBHashKey(attributeName = "SubjectCodeSessionCode")
    @DynamoDBAttribute(attributeName = "SubjectCodeSessionCode")
    public String getSubjectCodeSessionCode() {
        return _subjectCodeSessionCode;
    }

    public void setSubjectCodeSessionCode(final String _subjectCodeSessionCode) {
        this._subjectCodeSessionCode = _subjectCodeSessionCode;
    }
    @DynamoDBRangeKey(attributeName = "QuizNameQnID")
    @DynamoDBAttribute(attributeName = "QuizNameQnID")
    public String getQuizNameQnID() {
        return _quizNameQnID;
    }

    public void setQuizNameQnID(final String _quizNameQnID) {
        this._quizNameQnID = _quizNameQnID;
    }
    @DynamoDBAttribute(attributeName = "Correctans")
    public Double getCorrectans() {
        return _correctans;
    }

    public void setCorrectans(final Double _correctans) {
        this._correctans = _correctans;
    }
    @DynamoDBAttribute(attributeName = "Options")
    public List<String> getOptions() {
        return _options;
    }

    public void setOptions(final List<String> _options) {
        this._options = _options;
    }
    @DynamoDBAttribute(attributeName = "Question")
    public String getQuestion() {
        return _question;
    }

    public void setQuestion(final String _question) {
        this._question = _question;
    }

}
