package com.example.trialesc;

public interface allDataInterface {

    public void postQuestion(String question);

    public void getQuestions(String sessionId,String questionNo);

    public void pushQuestions();

    public void submitAnswer();

    //For teachers
    public void openSession();
    //For teachers
    public void closeSession();

    public void getLearningData(Integer weekno);

    public void setLearningData();
    //For students to key in their session code
    public void setSession(String session);
}
