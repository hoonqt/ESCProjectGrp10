package com.example.esc_50005.UI.Course.FAQ.editFaq;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Otter on 4/4/2018.
 */

public class EditFaqDialogPresenter implements EditFaqDialogContract.Presenter {
    public static final String TAG = "EditFaqDialogPresenter";

    private final FaqRemoteDataSource mFaqRepository;

    private final EditFaqDialogContract.View mFeedbackView;

    String courseId;
    String userId;

    public EditFaqDialogPresenter(EditFaqDialogContract.View view) {
        mFaqRepository = new FaqRemoteDataSource();
        mFeedbackView = view;
        mFeedbackView.setPresenter(this);
    }

    @Override
    public void start() {
    }

    @Override
    public void addFaq(String question, String answer) {
        Random r = new Random();
        Faq newFaq = new Faq();
        newFaq.setCourseId(courseId);
        newFaq.setAuthor(userId);
        newFaq.setUsersVoted(new ArrayList<String>());
        newFaq.setUpvotes(0);
        newFaq.setQuestion(question);
        newFaq.setAnswer(answer);
        newFaq.setQuestionId(Integer.toString(r.nextInt(1000)));
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
