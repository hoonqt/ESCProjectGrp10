package com.example.cindy.esc_50005.Database.CoursesInformation;

import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformation;
import com.example.cindy.esc_50005.Database.UsersInformation.UsersInformationDO;

import java.util.ArrayList;

/**
 * Created by cindy on 13/3/2018.
 */

public interface CoursesInformationDataSource {

    void removeUser(UsersInformation faq);
    void addUser(UsersInformation faq);
    ArrayList<UsersInformationDO> queryUser(String username, String password, String userType);

}
