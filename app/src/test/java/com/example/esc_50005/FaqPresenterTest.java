package com.example.esc_50005;

import com.example.esc_50005.Database.FAQ.Faq;
import com.example.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.esc_50005.UI.Course.FAQ.FaqContract;
import com.example.esc_50005.UI.Course.FAQ.FaqPresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tan_j on 14/3/2018.
 */

/**
 * Unit tests for the implementation of {@link FaqPresenter}
 */
public class FaqPresenterTest {

    private static ArrayList<Faq> FAQS;

    @Mock
    private FaqContract.View mFaqView;

    @Mock
    private FaqRemoteDataSource mFaqRepository;

    @Captor
    private ArgumentCaptor<Faq> captor;

    @Captor
    private ArgumentCaptor<ArrayList<Faq>> showFaqArgumentCaptor;


    private FaqPresenter mFaqPresenter;

    @Before
    public void setupFaqPresenter() {
        MockitoAnnotations.initMocks(this);
        mFaqPresenter = new FaqPresenter(mFaqRepository, mFaqView);

        FAQS = new ArrayList<Faq>();
        Faq faq1 = new Faq();
        faq1.setCourseId("50003");
        faq1.setQuestionId("101");
        faq1.setQuestion("Why is the sky white?");
        faq1.setAnswer("Because its not red");
        faq1.setAuthor("Jonathan");
        FAQS.add(faq1);
        Faq faq2 = new Faq();
        faq2.setCourseId("50003");
        faq2.setQuestionId("102");
        faq2.setQuestion("Why is the sky blue?");
        faq2.setAnswer("Because its not white");
        faq2.setAuthor("Jane");
        FAQS.add(faq2);
        Faq faq3 = new Faq();
        faq3.setCourseId("50003");
        faq3.setQuestionId("103");
        faq3.setQuestion("Why is the sky red?");
        faq3.setAnswer("Because its not blue");
        faq3.setAuthor("John");
        FAQS.add(faq3);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mFaqPresenter = new FaqPresenter(mFaqRepository, mFaqView);

        // Then the presenter is set to the view
        verify(mFaqView).setPresenter(mFaqPresenter);
    }



//    @Test
//    public void clickOnFab_ShowsAddFaqUi() {
//        // When adding a new faq
//        mFaqPresenter.addNewFaq();
//
//        // Then add faq activity is shown
//        verify(mFaqView).showAddFaq();
//    }

    @Test
    public void clickOnUpvoteBtn_saveFaqInRepository() {
        // Given a stubbed faq
        Faq faq = new Faq();
        faq.setCourseId("50003");
        faq.setQuestionId("103");
        faq.setQuestion("Why is the sky white?");
        faq.setAnswer("Because its not red");
        faq.setAuthor("Jonathan");
        faq.setUsersVoted(new ArrayList<String>());

        // When upvote faq
        mFaqPresenter.upvoteFaq(faq);

//         Then faq is saved
        verify(mFaqPresenter.mFaqRepository).saveFaq(any(Faq.class));
    }

    @Test
    public void secondClickOnUpvoteBtn_saveFaqInRepository() {
        // Given a stubbed faq
        Faq faq = new Faq();
        faq.setCourseId("50003");
        faq.setQuestionId("103");
        faq.setQuestion("Why is the sky white?");
        faq.setAnswer("Because its not red");
        faq.setAuthor("Jonathan");
        ArrayList<String> usersVoted = new ArrayList<String>();
        usersVoted.add("1001688");
        faq.setUsersVoted(usersVoted);

        // When upvote faq on second click
        mFaqPresenter.downvoteFaq(faq);

//         Then faq is saved
        verify(mFaqPresenter.mFaqRepository).saveFaq(any(Faq.class));
    }

//    @Test
//    public void unavailableTasks_ShowsError() {
//        // When tasks are loaded
//        mFaqPresenter.loadFaq();
//
//        // And the tasks aren't available in the repository
//        verify(mFaqRepository).getFaqListByCourseId("50005");
//
//        // Then an error message is shown
//        verify(mFaqView).showLoadingTasksError();
//    }

    @Test
    public void loadFaqFromRepository() {
//         Given an initialized FaqPresenter with initialized faq
//         When loading of faq is requested
        mFaqPresenter.loadFaq();
        verify(mFaqRepository).getFaqListByCourseId("50003");
    }

    @Test
    public void processFaqFromRepositoryAndLoadIntoView() {
        ArrayList<Faq> result = FAQS;
        mFaqPresenter.processFaq(result);
        verify(mFaqView).showFaq(result);
    }

    @Test
    public void processEmptyFaqFromRepositoryAndLoadIntoView() {
        mFaqPresenter.processEmptyFaq();
        verify(mFaqView).showNoFaq();
    }

}