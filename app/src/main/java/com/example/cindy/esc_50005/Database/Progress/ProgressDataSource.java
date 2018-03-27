package com.example.cindy.esc_50005.Database.Progress;



import java.util.ArrayList;

/**
 * Created by 1002215 on 25/3/18.
 */

public interface ProgressDataSource {

    void putScores(String userid, String subjectcode, String quizname, Double score);
//    public void getFromDatabase(final String userid,final String subjectcode);
    ArrayList<NewQuizScoresDO> getScores(final String userid,final String subjectcode);
    ArrayList<NewQuizScoresDO> getNames(final String userid, final String subjectcode);
}
