package main.services;

import main.Exception.ExceptionDBStructure;
import main.Exception.TaxiException;
import main.pojo.User;
import main.pojo.UserRole;

import java.util.Date;

/*
 * User Service Interface
 */
public interface UserServiceInterface {
    /**
     * Gets User during autentification
     * @param login
     * @param password
     * @return User
     */
    public User auth(String login, String password) throws TaxiException;

    /**
     * Creates User in DB
     * @return User
     */
    public User createBrandNew(User user) throws TaxiException;

    /**
     * Gets User role
     * @param login
     * @return UserRole
     */
    public UserRole getRole(String login) throws TaxiException;


}
