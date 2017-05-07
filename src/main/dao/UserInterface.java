package main.dao;

import main.Exception.ExceptionDBStructure;
import main.Exception.TaxiException;
import main.pojo.User;
import main.pojo.UserRole;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

/*
 * User Interface
 */

public interface UserInterface {

    /**
     * Create a new user in DB
     * @param user
     */
    public void create(User user) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Create a new user in DB
     * @param user
     */
    public User createBrandNew(User user) throws TaxiException, SQLException, ExecutionException, InterruptedException;

    /**
     * Read a user from DB by users_pkey_driver
     * @param usersPkey
     * @return
     */
    public User read(int usersPkey);

    public User read(String login) throws TaxiException, ExecutionException, InterruptedException, ParseException, SQLException, IOException;

    /**
     * Update a user from DB
     * @param user
     */
    public void update(User user);

    /**
     * Delete a user from DB
     * @param user
     */
    public void delete(User user);

    /**
     * Get all passengers from DB
     */
    public void getAll();


    /**
     * Gets registration date
     * @param login
     * @return Date
     */
    public Date getRegDate(String login) throws TaxiException, ParseException;

    /**
     * Gets user role
     * @param login
     * @return UserRole
     */
    public UserRole getRole(String login) throws Exception;
}
