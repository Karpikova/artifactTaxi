package main.services;


import main.dao.PassengerImplementation;
import main.dao.PassengerInterface;
import main.pojo.Passenger;

/*
 * Passenger Service Implementation
 */
public class PassengerServiceImplementation implements PassengerServiceInterface{
    private static PassengerInterface passengerInterface = new PassengerImplementation();

    public Passenger read(String login){
        return passengerInterface.read(login);
    }

}
