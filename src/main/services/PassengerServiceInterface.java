package main.services;

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
    public Passenger read(String login);

}
