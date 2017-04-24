package main.dao;

import main.Exception.TaxiException;
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
    public void create(Trip trip) throws TaxiException;

    /**
     * Create a trip
     * @param trip
     */
    public void createABrandNew(long passenger_id, String from, String to, int price) throws TaxiException;

    /**
     * Read all trips with status
     * @param status
     * @return
     */
    public List<Trip> readList(Status status) throws TaxiException;

    /**
     * Read all trips of driver
     * @param driver_pkey
     * @return
     */
    public List<Trip> readList(long driver_pkey) throws TaxiException;

    /**
     * Read all trips of passenger with Status
     * @param passenger_pkey
     * @return
     */
    public List<Trip> readList(long passenger_pkey, Status status) throws TaxiException;

    /**
     * Read all trips of passenger without Status
     * @param passenger_pkey
     * @return
     */
    public List<Trip> readListExStatus(long passenger_pkey, Status status) throws TaxiException;

    /**
     * Update a trip
     * @param trip
     */
    public void update(Trip trip) throws TaxiException;

    /**
     * Update a trip report
     * @param trip_pkey
     * @param report
     */
    public void updateReport(long trip_pkey, String report) throws TaxiException;

    /**
     * Update a trip status
     * @param trip_pkey
     * @param status
     */
    public void updateStatus(long trip_pkey, Status status) throws TaxiException;

    /**
     * Update Status of trip and appoints a driver
     * @param trips_pkey
     * @param status
     */
    public void appointADriver(long trips_pkey, long users_pkey_driver, Status status) throws TaxiException;

    /**
     * Delete a trip
     * @param trip
     */
    public void delete(Trip trip);

    /**
     * Delete trip by ID
     * @param trip_pkey
     */
    public void deleteByID(long trip_pkey) throws TaxiException;

    /**
     * Get all trips
     */
    public void getAll();

}
