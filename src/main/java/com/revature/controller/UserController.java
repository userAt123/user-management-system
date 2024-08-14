package com.revature.controller;


import com.revature.model.User;

import com.revature.service.UserService;

import io.javalin.http.Handler;
import org.postgresql.util.PSQLException;

import java.sql.SQLException;
import java.util.ArrayList;

/*
 The Controller Layer is where HTTP Requests get sent after Javalin directs them
 It's in this layer that JSON comes in and gets translated to Java and vice versa
 We'll be taking in HTTP Requests from the client and sending back HTTP Responses
 The Controllers job is processing HTTP Requests */
public class UserController {

    //We need an EmployeeDAO to use its employee data methods
    UserService userService = new UserService();

    //OLD^ we're using services now:
    //EmployeeService es = new EmployeeService();

    //This Handler will handle GET requests to /employees
    //public Handler getEmployeesHandler = ctx -> {
    public Handler getUserHandler = ctx -> {

        //Get an ArrayList of employees, populated by the getEmployees service method
        ArrayList<User> user = userService.getUser();

        //PROBLEM: we can't send plain Java in an HTTP Response - it only takes JSON

        //We can use the .json() method to convert this ArrayList to JSON
        //NOTE: This method also returns the object once the code block is done
        ctx.json(user);

        //We can also set the HTTP Response status code with ctx.status()
        ctx.status(200);

    };

    //This Handler will handle GET requests to /employees
    public Handler getAllUserHandler = ctx -> {

        //Get an ArrayList of employees, populated by the getEmployees service method
        ArrayList<User> user = userService.getAllUser();

        //PROBLEM: we can't send plain Java in an HTTP Response - it only takes JSON

        //We can use the .json() method to convert this ArrayList to JSON
        //NOTE: This method also returns the object once the code block is done
        ctx.json(user);

        //We can also set the HTTP Response status code with ctx.status()
        ctx.status(200);

    };


    //This Handler will handle POST requests to /employees
    public Handler insertUserHandler = ctx -> {

        //We have JSON data coming in, which we need to convert into a Java object before the DAO can use it
        //We're going to use ctx.bodyAsClass(), to convert the incoming JSON into a Java Employee object
        User newUser = ctx.bodyAsClass(User.class);

        try {
            //Send this employee to the service to be inserted into the DB
            User insertedUser = userService.insertUser(newUser);
            ctx.status(201); //201 "created" - the resource was created
            ctx.json(insertedUser); //send the employee back to the user
            ctx.result("Record Inserted Successfully!");   // Does not work
        } catch (IllegalArgumentException e) {
            ctx.status(400); //400 stands for bad request
            ctx.result(e.getMessage()); //send back the specific exception message - user friendly!
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown - we didn't do lastname checks");
        }


//        //If something goes wrong in the DAO, it will return null.
//        //We can send back an error code/message if so
//        if (insertedEmployee == null) {
//            ctx.status(400); //400 stands for bad request
//            //TODO: we could make a custom exception like "ManagerAlreadyExistsException"
//            ctx.result("Failed to insert Employee! Check your JSON!");
//        } else{
//            //if the insert is successful, return 201 and the new Employee
//            ctx.status(201); //201 stands for "created", as in some new data was created
//            ctx.json(insertedEmployee); //send the new inserted Employee back to the user
//        }

        //NOTE: we can have the json() and status() methods in either order

    };

    //TODO: (for you) figure out how to make a DELETE handler
    //public User deleteUserById(int userid) {
    //This Handler will handle POST requests to /employees
    public Handler deleteUserHandler = ctx -> {

        //We have JSON data coming in, which we need to convert into a Java object before the DAO can use it
        //We're going to use ctx.bodyAsClass(), to convert the incoming JSON into a Java Employee object
        User newUser = ctx.bodyAsClass(User.class);

        try {
            //Send this employee to the service to be inserted into the DB

            int userid = Integer.parseInt(ctx.pathParam("userid"));
            System.out.println("userid - Test " + userid);
            User deletedUser = userService.deleteUserById(userid);
            //userService.deleteUserById(userid);

            ctx.status(201); //201 "created" - the resource was created
            ctx.json(userid); //send the employee back to the user
            ctx.result("Record Deleted Successfully!");   // Does not work
        } catch (IllegalArgumentException e) {
            ctx.status(400); //400 stands for bad request
            ctx.result(e.getMessage()); //send back the specific exception message - user friendly!
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown - we didn't do lastname checks");
        }


//        //If something goes wrong in the DAO, it will return null.
//        //We can send back an error code/message if so
//        if (insertedEmployee == null) {
//            ctx.status(400); //400 stands for bad request
//            //TODO: we could make a custom exception like "ManagerAlreadyExistsException"
//            ctx.result("Failed to insert Employee! Check your JSON!");
//        } else{
//            //if the insert is successful, return 201 and the new Employee
//            ctx.status(201); //201 stands for "created", as in some new data was created
//            ctx.json(insertedEmployee); //send the new inserted Employee back to the user
//        }

        //NOTE: we can have the json() and status() methods in either order

    };

    //This Handler will handle POST requests to /employees
    public Handler updateUserInfoHandler = ctx -> {

        //We have JSON data coming in, which we need to convert into a Java object before the DAO can use it
        //We're going to use ctx.bodyAsClass(), to convert the incoming JSON into a Java Employee object
        //User newUser = ctx.bodyAsClass(User.class);

        try {
            //Send this employee to the service to be inserted into the DB

            int userid = Integer.parseInt(ctx.pathParam("userid"));
            System.out.println("userid - Test X " + userid);

            //NOTE: salary is coming in as a single value, so we'll use .body(), not .bodyAsClass()
            String password = (ctx.body());
            System.out.println("password - Test > " + password);


            User updated = userService.updateUserInfo(userid, password);
            //userService.deleteUserById(userid);

            ctx.status(201); //201 "created" - the resource was created
           // ctx.json(updated); //send the employee back to the user
            ctx.result("Record Updated Successfully!");   // Does not work

        } catch (IllegalArgumentException e) {
            ctx.status(400); //400 stands for bad request
            ctx.result(e.getMessage()); //send back the specific exception message - user friendly!
        } catch (NullPointerException e) {
            ctx.status(400);
            ctx.result("NullPointer got thrown");
        }


//        //If something goes wrong in the DAO, it will return null.
//        //We can send back an error code/message if so
//        if (insertedEmployee == null) {
//            ctx.status(400); //400 stands for bad request
//            //TODO: we could make a custom exception like "ManagerAlreadyExistsException"
//            ctx.result("Failed to insert Employee! Check your JSON!");
//        } else{
//            //if the insert is successful, return 201 and the new Employee
//            ctx.status(201); //201 stands for "created", as in some new data was created
//            ctx.json(insertedEmployee); //send the new inserted Employee back to the user
//        }

        //NOTE: we can have the json() and status() methods in either order

    };
}

