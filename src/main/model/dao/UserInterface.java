package main.model.dao;

import main.model.pojo.User;

/*
 * User Interface
 */
public interface UserInterface {

    public User create(User journal);

    public User read(int usersPkey);

    public void update(User journal);

    public void delete(User journal);

    public void getAll();
}
