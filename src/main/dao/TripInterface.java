package main.dao;

import main.pojo.Status;
import main.pojo.Trip;

import java.util.List;

/*
 * Trip Interface
 */
public interface TripInterface {

    /**
     * Create a trip
     * @param trip
     */
    public void create(Trip trip);


    /**
     * Read all trips with status
     * @param status
     * @return
     */
    public List<Trip> readList(Status status);

    /**
     * Update a trip
     * @param trip
     */
    public void update(Trip trip);

    /**
     * Update Status of trip and appoints a driver
     * @param trips_pkey
     * @param status
     */
    public void appointADriver(long trips_pkey, long users_pkey_driver, Status status);

    /**
     * Delete a trip
     * @param trip
     */
    public void delete(Trip trip);

    /**
     * Get all trips
     */
    public void getAll();

}
