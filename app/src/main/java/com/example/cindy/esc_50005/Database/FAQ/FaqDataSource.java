package com.example.cindy.esc_50005.Database.FAQ;

import java.util.ArrayList;

/**
 * Created by hoonqt on 1/3/18.
 */

public interface FaqDataSource {

    public void deleteFaq(Faq faq);
//    public void deleteAllFaqs();
    public void saveFaq(Faq faq);
    public ArrayList<Faq> getFaqListByCourseId(String courseId);
}
