package com.example.cindy.esc_50005.Database.Progress;



import java.util.ArrayList;

/**
 * Created by 1002215 on 25/3/18.
 */

public interface ProgressDataSource {

<<<<<<< HEAD
    void putScores(String userid, String subjectcode, String quizname, Double score);
=======
    public void putScores(String userid, String subjectcode, String quizname, Double score, String name);
>>>>>>> 163fb8b6167936199947d4d3ada013cc817a2540
//    public void getFromDatabase(final String userid,final String subjectcode);
    ArrayList<NewQuizScoresDO> getScores(final String userid,final String subjectcode);
    ArrayList<NewQuizScoresDO> getNames(final String userid, final String subjectcode);
}
