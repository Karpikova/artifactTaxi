package main.services;

import main.Exception.TaxiException;
import main.controllers.LoginServlet;
import main.dao.DriverImplementation;
import main.dao.DriverInterface;
import main.dao.PassengerImplementation;
import main.pojo.Driver;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * Driver Service Interface Implementation
 */
@Repository
public class DriverServiceImplementation implements DriverServiceInterface {

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(DriverServiceImplementation.class);

    @Autowired
    private DriverInterface driverInterface;// = new DriverImplementation();

    public Driver getDriver(String login) throws TaxiException {
        return driverInterface.read(login);
    };

    /**
     * Read a driver from DB by login
     * @param login
     * @return Driver
     */
    public Driver read(String login) throws TaxiException {
        return driverInterface.read(login);
    }

    public void create(Driver driver) throws TaxiException {
        driverInterface.create(driver);
    }
}
