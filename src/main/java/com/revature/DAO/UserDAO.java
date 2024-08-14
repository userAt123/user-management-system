package com.revature.DAO;

import com.revature.model.User;
import com.revature.model.UserAddress;
import com.revature.model.UserPayment;
import com.revature.util.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO implements UserDAOInterface {

    public ArrayList<User> getAllUser() {


            //instantiate a Connection object so we can talk to the DB
            try(Connection conn = ConnectionUtil.getConnection()){

                //A String that will represent the SQL we send to the DB
                String sql = "SELECT * FROM users";

                //We need to create a Statement object to execute our query
                //NOTE: The query above has no variables, so we'll use Statement, not PreparedStatement
                Statement stmt = conn.createStatement();

                //We execute the query, and save the results into a ResultSet
                ResultSet rs = stmt.executeQuery(sql);

                //We need an ArrayList to hold the SELECTed Employees
                ArrayList<User> user = new ArrayList<User>();

                //rs.next() will iterate through the ResultSet...
                //and return false when there are no more records
                while(rs.next()){

                    //For every Employee found, add it to the ArrayList
                    //We will need to instantiate a new Employee object for each record
                    //We can get each column from the ResultSet with rs.getXYZ()
                    User us = new User(
                            rs.getInt("user_id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("first_name"),
                            rs.getString("last_name")

                    );

                    // We need to use the getRoleById method to populate the Employees Role object
                    // RoleDAO rDAO = new RoleDAO();
                    // Role role = rDAO.getRoleById(rs.getInt("role_id_fk"));





                    //use the setter of Employee to populate the newly created Role object
                    // e.setRole(role);

                    //NOTE: we could have instantiated the Role before the Employee
                    //but we did things in a different order on Monday

                    user.add(us); //add the populated Employee to the ArrayList

                } //end of while loop - no more employees to see!

                return user; //return the ArrayList of Employees

            } catch(SQLException e){
                e.printStackTrace(); //Tell us what went wrong
            }

            return null;
            //Why return null at the end? we need to satisfy the return type.
            //Something needs to be returned no matter what

    }

    @Override
    public ArrayList<User> getUser() {

        //instantiate a Connection object so we can talk to the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //A String that will represent the SQL we send to the DB
            //String sql = "SELECT * FROM users";
            String sql = "SELECT * FROM users " +
                    "INNER JOIN user_address ON users.user_id = user_address.user_id_fk " +
                    "INNER JOIN user_payment ON user_payment.user_id_fk = user_address.user_id_fk";

            //We need to create a Statement object to execute our query
            //NOTE: The query above has no variables, so we'll use Statement, not PreparedStatement
            Statement stmt = conn.createStatement();

            //We execute the query, and save the results into a ResultSet
            ResultSet rs = stmt.executeQuery(sql);

            //We need an ArrayList to hold the SELECTed Employees
            ArrayList<User> user = new ArrayList<User>();

            //rs.next() will iterate through the ResultSet...
            //and return false when there are no more records
            while(rs.next()){

                //For every Employee found, add it to the ArrayList
                //We will need to instantiate a new Employee object for each record
                //We can get each column from the ResultSet with rs.getXYZ()
                User us = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("first_name"),
                        rs.getString("last_name")

                );

                UserAddress userAddress = new UserAddress(
                        rs.getString("address"),
                        rs.getString("city"),
                        rs.getInt("zip_code"),
                        rs.getString("country")
                );

                UserPayment userPayment = new UserPayment(
                        rs.getString("payment_type"),
                        rs.getString("provider"),
                        rs.getInt("account_no")
                );

               us.setUserAddress(userAddress);
               us.setUserPayment(userPayment);

                // We need to use the getRoleById method to populate the Employees Role object
                // RoleDAO rDAO = new RoleDAO();
                // Role role = rDAO.getRoleById(rs.getInt("role_id_fk"));





                //use the setter of Employee to populate the newly created Role object
               // e.setRole(role);

                //NOTE: we could have instantiated the Role before the Employee
                //but we did things in a different order on Monday

                user.add(us); //add the populated Employee to the ArrayList

            } //end of while loop - no more employees to see!

            return user; //return the ArrayList of Employees

        } catch(SQLException e){
            e.printStackTrace(); //Tell us what went wrong
        }

        return null;
        //Why return null at the end? we need to satisfy the return type.
        //Something needs to be returned no matter what

    }

    @Override
    public User insertUser(User user) {

        //We need a Connection object to interact with the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //create our SQL statement String
            String sql = "INSERT INTO users (username, password, first_name, last_name) VALUES (?,?,?,?)";

            //Instantiate a PreparedStatement to hold our SQL command and fill in the wildcards "?"
            PreparedStatement ps = conn.prepareStatement(sql);

            //fill in each wildcard with the Employee object and ps.setXYZ() methods
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirst_name());
            ps.setString(4, user.getLast_name());

            //Now that our SQL command is complete, we can execute it
            ps.executeUpdate();
            //NOTE: executeUpdate() is used for INSERT, UPDATE, DELETE commands
            //...while executeQuery() is used for SELECT (querying the DB)

            //Now we can return the Employee to the user, assuming nothing went wrong
            return user;

        } catch(SQLException e){
            e.printStackTrace(); //Tell us what went wrong
            System.out.println("Failed to insert user!");
        }
        return null;
    }

    @Override
    public User deleteUserById(int userid) {

        //We need a Connection object to interact with the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //create our SQL statement String
            String sql = "DELETE FROM users WHERE user_id = ?";

            //Instantiate a PreparedStatement to hold our SQL command and fill in the wildcards "?"
            PreparedStatement ps = conn.prepareStatement(sql);

            //fill in each wildcard with the Employee object and ps.setXYZ() methods
            ps.setInt(1, userid);

            //Now that our SQL command is complete, we can execute it
            ps.executeUpdate();
            //NOTE: executeUpdate() is used for INSERT, UPDATE, DELETE commands
            //...while executeQuery() is used for SELECT (querying the DB)

            //Now we can return the Employee to the user, assuming nothing went wrong
            return null;

        } catch(SQLException e){
            e.printStackTrace(); //Tell us what went wrong
            System.out.println("Failed to delete user!");
        }
        return null;
    }

    public User updateUserInfo(int userid, String password) {

        //We need a Connection object to interact with the DB
        try(Connection conn = ConnectionUtil.getConnection()){

            //create our SQL statement String
            //String sql = "UPDATE user_payment SET payment_type = ?, provider = ?, account_no = ? WHERE user_id_fk = ?";
            String sql = "UPDATE users SET password = ? WHERE user_id = ?";

            //Instantiate a PreparedStatement to hold our SQL command and fill in the wildcards "?"
            PreparedStatement ps = conn.prepareStatement(sql);

            //fill in each wildcard with the Employee object and ps.setXYZ() methods
            ps.setString(1, password);
            ps.setInt(2, userid);
            /*
            ps.setString(1, userid);
            ps.setString(2, userid);
            ps.setString(3, userid);
            ps.setInt(4, userid);
            */
            //Now that our SQL command is complete, we can execute it
            int result = ps.executeUpdate();
            //NOTE: executeUpdate() is used for INSERT, UPDATE, DELETE commands
            //...while executeQuery() is used for SELECT (querying the DB)

            //Now we can return the Employee to the user, assuming nothing went wrong
            return null;

        } catch(SQLException e){
            e.printStackTrace(); //Tell us what went wrong
            System.out.println("Failed to delete user!");
        }
        return null;
    }

}
