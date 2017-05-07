package main.services;


import main.Exception.TaxiException;
import main.dao.PassengerInterface;
import main.pojo.Passenger;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

/*
 * Passenger Service Implementation
 */
@Repository
public class PassengerServiceImplementation implements PassengerServiceInterface{

    @Autowired
    private PassengerInterface passengerInterface;

    public Passenger read(String login) throws Exception {
        return passengerInterface.read(login);
    }

    public void create(Passenger passenger) throws TaxiException, InterruptedException, ExecutionException, SQLException {
        passengerInterface.create(passenger);
    }

}
