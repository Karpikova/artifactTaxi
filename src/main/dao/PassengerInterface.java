package main.dao;

import main.Exception.TaxiException;
import main.pojo.Driver;
import main.pojo.Passenger;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

/*
 * Passenger Interface
 */
public interface PassengerInterface {

    /**
     * Create a new passenger in DB
     * @param passenger
     */
    public void create(Passenger passenger) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Read a passenger from DB by users_pkey_driver
     * @param users_pkey_pas
     * @return
     */
    public Passenger read(int users_pkey_pas);

    /**
     * Read a passenger from DB by login
     * @param login
     * @return Passenger
     */
    public Passenger read(String login) throws TaxiException, Exception;

    /**
     * Update a passenger from DB
     * @param passenger
     */
    public void update(Passenger passenger);

    /**
     * Delete a Passenger from DB
     * @param passenger
     */
    public void delete(Passenger passenger);

    /**
     * Get all passengers from DB
     */
    public void getAll();
}
