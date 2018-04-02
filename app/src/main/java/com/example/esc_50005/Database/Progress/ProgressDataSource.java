package com.example.esc_50005.Database.Progress;



import java.util.ArrayList;

/**
 * Created by 1002215 on 25/3/18.
 */

public interface ProgressDataSource {

    void putScores(String userid,String subjectcode, String sessionID, String quizname, Double score, String name);
//    public void getFromDatabase(final String userid,final String subjectcode);
    ArrayList<QuizScores2DO> getScores(final String subjectCode, final String sessionID);
    ArrayList<String> getNames(final String userid);
}
