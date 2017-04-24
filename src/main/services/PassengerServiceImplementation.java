package main.services;


import main.Exception.TaxiException;
import main.controllers.LoginServlet;
import main.dao.PassengerImplementation;
import main.dao.PassengerInterface;
import main.dao.UserImplementation;
import main.pojo.Passenger;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/*
 * Passenger Service Implementation
 */
public class PassengerServiceImplementation implements PassengerServiceInterface{

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(PassengerServiceImplementation.class);

    private static PassengerInterface passengerInterface = new PassengerImplementation();

    public Passenger read(String login) throws TaxiException {
        return passengerInterface.read(login);
    }

    public void create(Passenger passenger) throws TaxiException {
        passengerInterface.create(passenger);
    }

}
