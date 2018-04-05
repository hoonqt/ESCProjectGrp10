package com.example.esc_50005.Database.UsersInformation;

import java.util.ArrayList;

/**
 * Created by cindy on 13/3/2018.
 */

public interface UsersInformationDataSource {

    void removeUser(EditedUsersInformationDO faq);
    void addUser(EditedUsersInformationDO faq);
    ArrayList<EditedUsersInformationDO> queryAllUsers(final String userId, final String fullName);
    ArrayList<EditedUsersInformationDO> queryAParticularUser(final String userId);


}
