package com.revature.service;

import com.revature.DAO.UserDAO;
import com.revature.model.User;

import java.util.ArrayList;

public class UserService {

    //We need an EmployeeDAO to call its methods
    UserDAO userDAO = new UserDAO();

    //In general, "get all" methods are very simple in the service layer. Not much to process
    //no user inputs, no crazy outputs, just a bridge between the controller and the DAO.
    public ArrayList<User> getUser() {

        return userDAO.getUser();
    }

    public ArrayList<User> getAllUser() {

        return userDAO.getAllUser();
    }

    //Here's a more useful example - the incoming employee data has 3 fields we can validate
    public User insertUser(User user) throws IllegalArgumentException {

        //we'll run some checks on each of the employee's fields

        //check that the first name is not empty or null
        if (user.getUsername() == null || user.getUsername().equals("") ||
                user.getPassword() == null || user.getPassword().equals("") ||
                user.getFirst_name() == null || user.getFirst_name().equals("") ||
                user.getLast_name() == null || user.getLast_name().equals("")) {
            throw new IllegalArgumentException("Fields cannot be null or empty!");
        }
        //NOTE: null checks should happen BEFORE empty checks

        //TODO: we can do the same checks for last name
        /*
        //check that the first name is not vulgar
        if (user.getFirst_name().equalsIgnoreCase("JavaScript")) {
            throw new IllegalArgumentException("First Name cannot be vulgar!");
        }

        //check that the role id is non zero and non negative
        if (user.getRole_id_fk() <= 0) {
            throw new IllegalArgumentException("Role ID must be a positive number!");
        }

        //check that the role id is not 1 (assuming there is already a manager)
        //TODO: we would probably ACTUALLY make a call to the DB to see if there's an employee where role = 1;
        if (user.getRole_id_fk() == 1) {
            throw new IllegalArgumentException("There is already a manager!");
        }*/

        //If all of these checks pass, send the employee to the DAO to be inserted
        userDAO.insertUser(user);

        //TODO: we would wrap this^ call in a try/catch as well, or just let the DAO handle
        return user;

    }

    public User deleteUserById(int userid) {

        UserDAO userDAO = new UserDAO();
        return userDAO.deleteUserById(userid);
    }

    public User updateUserInfo(int userid, String password) {

        UserDAO userDAO = new UserDAO();
        return userDAO.updateUserInfo(userid, password);
    }

}
