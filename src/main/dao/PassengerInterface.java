package main.dao;

import main.pojo.Driver;
import main.pojo.Passenger;

/*
 * Passenger Interface
 */
public interface PassengerInterface {

    /**
     * Create a new passenger in DB
     * @param passenger
     */
    public void create(Passenger passenger);

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
    public Passenger read(String login);

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
