package main.services;

import main.dao.UserImplementation;
import main.dao.UserInterface;
import main.pojo.User;
import main.pojo.UserRole;

import java.util.Date;


/*
 * User Service Implementation
 */
public class UserServiceImplementation implements UserServiceInterface {

    private static UserInterface userInterface = new UserImplementation();

    public User auth(String login, String password) {
        return userInterface.read(login, password);
    }

    public User createBrandNew(User user) {
        return userInterface.createBrandNew(user);
    }

    public UserRole getRole(String login) {
        return userInterface.getRole(login);
    }

}
