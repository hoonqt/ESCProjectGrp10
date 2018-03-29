package com.example.esc_50005.Database.FAQ;

import java.util.ArrayList;

/**
 * Created by hoonqt on 1/3/18.
 */

public interface FaqDataSource {

    public void removeFaq(Faq faq);
    public void saveFaq(Faq faq);
    public ArrayList<Faq> getFaqListByCourseId(String courseId);
}
