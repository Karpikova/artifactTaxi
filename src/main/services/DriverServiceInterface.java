package main.services;

import main.Exception.TaxiException;
import main.pojo.Driver;
import main.pojo.Passenger;
import org.springframework.stereotype.Repository;

/*
 * Driver Service Interface
 */
public interface DriverServiceInterface {
    /**
     * Gets driver by login with minimum of properties
     * @param login
     * @return Driver
     */
    public Driver getDriver(String login) throws TaxiException;

    /**
     * Read a driver from DB by login
     * @param login
     * @return Driver
     */
    public Driver read(String login) throws TaxiException;

    /**
     * Create a new Driver
     * @param driver
     */
    public void create(Driver driver) throws TaxiException;
}
