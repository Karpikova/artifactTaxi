package main.services;

import main.Exception.TaxiException;
import main.pojo.Passenger;

/*
 * Passenger Service Interface
 */
public interface PassengerServiceInterface {
    /**
     * Gets passenger by login with minimum of properties
     * @param login
     * @return Passenger
     */
    public Passenger read(String login) throws TaxiException;

    /**
     * Create a new Passenger in DB
     * @param passenger
     */
    public void create(Passenger passenger) throws TaxiException;

}
