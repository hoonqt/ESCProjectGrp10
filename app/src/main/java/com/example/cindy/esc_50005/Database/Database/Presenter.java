package com.example.cindy.esc_50005.Database.Database;

/**
 * Created by hoonqt on 1/3/18.
 */

public interface Presenter extends QuestionContract {

    public void loadQuestions();

    public void addNewQuestion();

    public void processEmptyQuestions();

    public void upvoteQuestion();

}
