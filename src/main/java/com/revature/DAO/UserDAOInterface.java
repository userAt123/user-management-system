package com.revature.DAO;

import com.revature.model.User;

import java.util.ArrayList;

public interface UserDAOInterface {

    // a method to select user with payment and Address
    ArrayList<User> getUser();

    // a method to select all users
    public ArrayList<User> getAllUser();

    // a method to insert a new user
    User insertUser(User user);
    /*If we're sending an Employee to be inserted into the DB, why are returning one back?
    we can send the data back to the User to be used in other functionalities or just to verify the insert*/

    // a method to delete a user by its id
    public User deleteUserById(int userid);

    // a method to update User info
    public User updateUserInfo(int userid, String password);

}
