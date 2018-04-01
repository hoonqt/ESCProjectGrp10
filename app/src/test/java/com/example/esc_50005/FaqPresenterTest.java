package com.example.esc_50005;

import com.example.cindy.esc_50005.Database.FAQ.Faq;
import com.example.cindy.esc_50005.Database.FAQ.FaqRemoteDataSource;
import com.example.cindy.esc_50005.Database.FAQ.FaqRepository;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqContract;
import com.example.cindy.esc_50005.UI.Course.FAQ.FaqPresenter;
import com.google.common.collect.Lists;

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

    private static ArrayList<Faq> mFaqList;

    @Mock
    private FaqContract.View mFaqView;

    @Captor
    private ArgumentCaptor<Faq> captor;

    @Captor
    private ArgumentCaptor<ArrayList<Faq>> showFaqArgumentCaptor;


    private FaqPresenter mFaqPresenter;

    @Before
    public void setupFaqPresenter() {
        MockitoAnnotations.initMocks(this);
        mFaqPresenter = new FaqPresenter(mFaqView);
        mFaqPresenter.mFaqRepository = mock(FaqRemoteDataSource.class);
    }

    @Test
    public void createPresenter_setsThePresenterToView() {
        // Get a reference to the class under test
        mFaqPresenter = new FaqPresenter(mFaqView);

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

        // When upvote faq
        mFaqPresenter.upvoteFaq(faq);

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

//    @Test
//    public void loadAllFaqFromRepositoryAndLoadIntoView() {
        // Given an initialized FaqPresenter with initialized faq
        // When loading of faq is requested
//        mFaqPresenter.loadFaq();

        // Callback is captured and invoked with stubbed tasks
//        ArrayList<Faq> result = mFaqList;
//        when(mFaqRepository.getFaqListByCourseId("50003")).thenReturn(mFaqList);
//        verify(mFaqView).showFaq(result);
//        assertTrue(result.size() == 3);
//    }
}