package main.services;

import main.dao.DriverImplementation;
import main.dao.DriverInterface;
import main.pojo.Driver;

/*
 * Driver Service Interface Implementation
 */
public class DriverServiceImplementation implements DriverServiceInterface {

    private static DriverInterface driverInterface = new DriverImplementation();

    public Driver getDriver(String login){
        return driverInterface.read(login);
    };

    /**
     * Read a driver from DB by login
     * @param login
     * @return Driver
     */
    public Driver read(String login) {
        return driverInterface.read(login);
    };
}
