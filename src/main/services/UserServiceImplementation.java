package main.services;

import main.Exception.TaxiException;
import main.dao.UserInterface;
import main.pojo.User;
import main.pojo.UserRole;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;


/*
 * User Service Implementation
 */
@Service
public class UserServiceImplementation implements UserServiceInterface {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(UserServiceImplementation.class);

    @Autowired
    private UserInterface userInterface;// = new UserImplementation();

    public User auth(String login, String password) throws TaxiException, InterruptedException, ExecutionException, SQLException, ParseException, IOException {
        logger.info(userInterface);
        return userInterface.read(login);
    }

    public User createBrandNew(User user) throws TaxiException, InterruptedException, ExecutionException, SQLException {
        return userInterface.createBrandNew(user);
    }

    public UserRole getRole(String login) throws Exception {
        return userInterface.getRole(login);
    }

}
