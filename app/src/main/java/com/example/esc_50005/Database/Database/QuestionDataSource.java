package com.example.esc_50005.Database.Database;

import java.util.ArrayList;

public interface QuestionDataSource {

    void addQuestion(String question, String sessionCode);
    void removeQuestion(String question, String sessionCode);
    ArrayList<Question> getQuestionsListBySessionId(final String sessionID);
}
