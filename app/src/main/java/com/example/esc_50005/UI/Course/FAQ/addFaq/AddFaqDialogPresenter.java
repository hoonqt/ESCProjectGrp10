package com.example.esc_50005.UI.Course.FAQ.addFaq;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Otter on 4/4/2018.
 */

public class AddFaqDialogPresenter implements AddFaqDialogContract.Presenter {
    public static final String TAG = "AddFaqDialogPresenter";

    private final FaqRemoteDataSource mFaqRepository;

    private final AddFaqDialogContract.View mFeedbackView;

    String courseId;
    String name;

    public AddFaqDialogPresenter(AddFaqDialogContract.View view) {
        mFaqRepository = new FaqRemoteDataSource();
        mFeedbackView = view;
        mFeedbackView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void addFaq(String question, String answer) {
        Faq newFaq = new Faq();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        newFaq.setCourseId(courseId);
        newFaq.setAuthor(name);
        newFaq.setUsersVoted(new ArrayList<String>());

        newFaq.setQuestion(question);
        newFaq.setAnswer(answer);
        newFaq.setQuestionId(UUID.randomUUID().toString());
        newFaq.setDate(Long.toString(timestamp.getTime()));

        mFaqRepository.saveFaq(newFaq);
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setName(String name) {
        this.name = name;
    }
}
