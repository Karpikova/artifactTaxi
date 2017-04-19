package main.model.dao;

import main.model.pojo.Driver;

/*
 * User Interface
 */
public interface DriverInterface {

    public Driver create(Driver journal);

    public Driver read(int users_pkey_driver);

    public void update(Driver journal);

    public void delete(Driver journal);

    public void getAll();
}
