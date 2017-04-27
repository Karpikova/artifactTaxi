package main.services;


import main.Exception.TaxiException;
import main.controllers.LoginServlet;
import main.dao.PassengerImplementation;
import main.dao.PassengerInterface;
import main.dao.UserImplementation;
import main.pojo.Passenger;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
 * Passenger Service Implementation
 */
@Repository
public class PassengerServiceImplementation implements PassengerServiceInterface{

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(PassengerServiceImplementation.class);

    @Autowired
    private PassengerInterface passengerInterface;// = new PassengerImplementation();

    public Passenger read(String login) throws TaxiException {
        return passengerInterface.read(login);
    }

    public void create(Passenger passenger) throws TaxiException {
        passengerInterface.create(passenger);
    }

}
