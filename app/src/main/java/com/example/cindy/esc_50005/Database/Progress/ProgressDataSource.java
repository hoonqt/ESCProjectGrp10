package com.example.cindy.esc_50005.Database.Progress;

import com.example.cindy.esc_50005.Database.FAQ.Faq;

import java.util.ArrayList;

/**
 * Created by 1002215 on 25/3/18.
 */

public interface ProgressDataSource {

    public void putScores(String userid, String subjectcode, String quizname, Double score);
//    public void getFromDatabase(final String userid,final String subjectcode);
    public ArrayList<NewQuizScoresDO> getScores(final String userid,final String subjectcode);
}
