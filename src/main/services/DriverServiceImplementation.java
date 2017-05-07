package main.services;

import main.Exception.TaxiException;
import main.dao.DriverInterface;
import main.pojo.Driver;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

/*
 * Driver Service Interface Implementation
 */
@Repository
public class DriverServiceImplementation implements DriverServiceInterface {

    @Autowired
    private DriverInterface driverInterface;

    public Driver getDriver(String login) throws TaxiException, InterruptedException, ExecutionException, SQLException {
        return driverInterface.read(login);
    };

    /**
     * Read a driver from DB by login
     * @param login
     * @return Driver
     */
    public Driver read(String login) throws TaxiException, InterruptedException, ExecutionException, SQLException {
        return driverInterface.read(login);
    }

    public void create(Driver driver) throws TaxiException, InterruptedException, ExecutionException, SQLException {
        driverInterface.create(driver);
    }
}
