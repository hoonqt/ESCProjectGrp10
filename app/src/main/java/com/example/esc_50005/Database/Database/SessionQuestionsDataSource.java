package com.example.esc_50005.Database.Database;

import java.util.ArrayList;

public interface SessionQuestionsDataSource {

    void addQuestion(String question, String sessionCode);
    void removeQuestion(String question, String sessionCode);
    void updateQuestion(String oldquestion, String sessionCode, String newQuestion);
    ArrayList<SessionQuestionsDO> getQuestionsListBySessionId(final String sessionID);
//    void getQuestionsList(final String sessionID);
    void findQuestionsById();
}
