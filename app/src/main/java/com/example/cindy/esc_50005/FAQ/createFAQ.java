package com.example.cindy.esc_50005.FAQ;

import java.text.SimpleDateFormat;
import java.util.Date;

public class createFAQ {

    private String courseID;
    private String question;
    private String answer;
    private int upvotes;
    private String author;
    private Date time;
    private Faq faq;

    public void createFAQ(String courseID,String question,String answer, String author) {

        this.courseID = courseID;
        this.question = question;
        this.answer = answer;
        upvotes = 0;
        this.author = author;
        time = new Date();
        faq = new Faq(courseID, question, answer, author);

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());




    }

}
