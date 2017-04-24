package main.dao;

import main.Exception.ExceptionDBStructure;
import main.Exception.TaxiException;
import main.pojo.User;
import main.pojo.UserRole;

import java.util.Date;

/*
 * User Interface
 */
public interface UserInterface {

    /**
     * Create a new user in DB
     * @param user
     */
    public void create(User user) throws TaxiException;

    /**
     * Create a new user in DB
     * @param user
     */
    public User createBrandNew(User user) throws TaxiException;

    /**
     * Read a user from DB by users_pkey_driver
     * @param usersPkey
     * @return
     */
    public User read(int usersPkey);

    public User read(String login, String userPassword) throws TaxiException;

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
    public Date getRegDate(String login) throws TaxiException;

    /**
     * Gets user role
     * @param login
     * @return UserRole
     */
    public UserRole getRole(String login) throws TaxiException;
}
