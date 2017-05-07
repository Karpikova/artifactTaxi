package main.services;

import main.Exception.ExceptionDBStructure;
import main.Exception.TaxiException;
import main.pojo.User;
import main.pojo.UserRole;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutionException;

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
    public User auth(String login, String password) throws TaxiException, InterruptedException, ExecutionException, SQLException, ParseException, IOException;

    /**
     * Creates User in DB
     * @return User
     */
    public User createBrandNew(User user) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Gets User role
     * @param login
     * @return UserRole
     */
    public UserRole getRole(String login) throws Exception;


}
