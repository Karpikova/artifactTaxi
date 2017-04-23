package main.services;

import main.pojo.Driver;

/*
 * Driver Service Interface
 */
public interface DriverServiceInterface {
    /**
     * Gets driver by login with minimum of properties
     * @param login
     * @return Driver
     */
    public Driver getDriver(String login);

    /**
     * Read a driver from DB by login
     * @param login
     * @return Driver
     */
    public Driver read(String login);
}
