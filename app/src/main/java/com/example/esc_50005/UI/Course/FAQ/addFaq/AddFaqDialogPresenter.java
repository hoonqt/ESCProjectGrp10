package com.example.esc_50005.UI.Course.FAQ.addFaq;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;

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
    String userId;

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
        newFaq.setCourseId(courseId);
        newFaq.setAuthor(userId);
        newFaq.setUsersVoted(new ArrayList<String>());

        newFaq.setQuestion(question);
        newFaq.setAnswer(answer);
        newFaq.setQuestionId(UUID.randomUUID().toString());
        newFaq.setDate("4 April 2018");

        mFaqRepository.saveFaq(newFaq);
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
