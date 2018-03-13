
package com.example.cindy.esc_50005.Database.Database;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;

import java.util.List;

public class SessionQuestionsDO {
    private String _sessioncode;
    private String _question;
    private List<String> _answers;
    private Double _upvote;

    @DynamoDBHashKey(attributeName = "sessioncode")
    @DynamoDBAttribute(attributeName = "sessioncode")
    public String getSessioncode() {
        return _sessioncode;
    }

    public void setSessioncode(final String _sessioncode) {
        this._sessioncode = _sessioncode;
    }
    @DynamoDBRangeKey(attributeName = "question")
    @DynamoDBAttribute(attributeName = "question")
    public String getQuestion() {
        return _question;
    }

    public void setQuestion(final String _question) {
        this._question = _question;
    }
    @DynamoDBAttribute(attributeName = "answers")
    public List<String> getAnswers() {
        return _answers;
    }

    public void setAnswers(final List<String> _answers) {
        this._answers = _answers;
    }
    @DynamoDBAttribute(attributeName = "upvote")
    public Double getUpvote() {
        return _upvote;
    }

    public void setUpvote(final Double _upvote) {
        this._upvote = _upvote;
    }

}