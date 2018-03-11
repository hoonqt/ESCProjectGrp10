package com.example.cindy.esc_50005.Database.Database;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by hoonqt on 1/3/18.
 */

public interface SessionQuestionsDataSource {

    public void addQuestion(String question, String sessionCode);
    public void removeQuestion(String question, String sessionCode);
    public void updateQuestion(String oldquestion, String sessionCode, String newQuestion);
    public void getQuestionsList(String sessionID);
    public void findQuestionsById();
}
