package main.services;

import main.Exception.TaxiException;
import main.dao.UserInterface;
import main.pojo.User;
import main.pojo.UserRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*
 * User Service Implementation
 */
@Service
public class UserServiceImplementation implements UserServiceInterface {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(UserServiceImplementation.class);

    @Autowired
    private UserInterface userInterface;// = new UserImplementation();

    public User auth(String login, String password) throws TaxiException {
        logger.info(userInterface);
        return userInterface.read(login, password);
    }

    public User createBrandNew(User user) throws TaxiException {
        return userInterface.createBrandNew(user);
    }

    public UserRole getRole(String login) throws TaxiException {
        return userInterface.getRole(login);
    }

}
