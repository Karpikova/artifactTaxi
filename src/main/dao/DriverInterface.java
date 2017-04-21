package main.dao;

import main.pojo.Driver;

/*
 * User Interface
 */
public interface DriverInterface {

    /**
     * Create a new driver in DB
     * @param driver
     */
    public void create(Driver driver);

    /**
     * Read a driver from DB by users_pkey_driver
     * @param users_pkey_driver
     * @return
     */
    public Driver read(int users_pkey_driver);

    /**
     * Read a driver from DB by login
     * @param login
     * @return Driver
     */
    public Driver read(String login);

    /**
     * Update a driver from DB
     * @param driver
     */
    public void update(Driver driver);

    /**
     * Delete a Driver from DB
     * @param driver
     */
    public void delete(Driver driver);

    /**
     * Get all drivers from DB
     */
    public void getAll();
}
