
package com.example.esc_50005.Database.Database;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBRangeKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

import java.util.ArrayList;
import java.util.List;
@DynamoDBTable(tableName = "escproject-mobilehub-27166461-SessionQuestions")

public class SessionQuestionsDO {
    private String _sessionId;
    private String _question;
    private List<String> _answer;
    private int _upvotes;
    private List<String> _usersVoters;

    @DynamoDBHashKey(attributeName = "sessionId")
    @DynamoDBAttribute(attributeName = "sessionId")
    public String getSessionId() {
        return _sessionId;
    }

    public void setSessionId(final String _sessionId) {
        this._sessionId = _sessionId;
    }
    @DynamoDBRangeKey(attributeName = "question")
    @DynamoDBAttribute(attributeName = "question")
    public String getQuestion() {
        return _question;
    }

    public void setQuestion(final String _question) {
        this._question = _question;
    }
    @DynamoDBAttribute(attributeName = "answer")
    public List<String> getAnswer() {
        return _answer;
    }

    public void setAnswer(final List<String> _answer) {
        this._answer = _answer;
    }
    @DynamoDBAttribute(attributeName = "upvotes")
    public int getUpvotes() {
        return _upvotes;
    }

    public void setUpvotes(final int _upvotes) {
        this._upvotes = _upvotes;
    }
    @DynamoDBAttribute(attributeName = "usersVoters")
    public List<String> getUsersVoters() {
        return _usersVoters;
    }

    public void setUsersVoters(final List<String> _usersVoters) {
        this._usersVoters = _usersVoters;
    }

}
