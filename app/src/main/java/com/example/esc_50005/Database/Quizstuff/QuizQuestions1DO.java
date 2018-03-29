package com.example.esc_50005.Database.Quizstuff;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.List;

@DynamoDBTable(tableName = "escproject-mobilehub-27166461-QuizQuestions1")

public class QuizQuestions1DO {
    private String _subjectCodeSessionCode;
    private String _quizName;
    private String _correctans;
    private List<String> _options;
    private String _question;
    private List<String> _results;

    @DynamoDBHashKey(attributeName = "SubjectCodeSessionCode")
    @DynamoDBAttribute(attributeName = "SubjectCodeSessionCode")
    public String getSubjectCodeSessionCode() {
        return _subjectCodeSessionCode;
    }

    public void setSubjectCodeSessionCode(final String _subjectCodeSessionCode) {
        this._subjectCodeSessionCode = _subjectCodeSessionCode;
    }
    @DynamoDBRangeKey(attributeName = "QuizName")
    @DynamoDBAttribute(attributeName = "QuizName")
    public String getQuizName() {
        return _quizName;
    }

    public void setQuizName(final String _quizName) {
        this._quizName = _quizName;
    }
    @DynamoDBAttribute(attributeName = "Correctans")
    public String getCorrectans() {
        return _correctans;
    }

    public void setCorrectans(final String _correctans) {
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
    @DynamoDBAttribute(attributeName = "Results")
    public List<String> getResults() {
        return _results;
    }

    public void setResults(final List<String> _results) {
        this._results = _results;
    }

}
