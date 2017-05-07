package main.services;


import main.Exception.TaxiException;
import main.pojo.Status;
import main.pojo.Trip;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;

/*
 * Trip Service Interface
 */
public interface TripServiceInterface {
    /**
     * Read all trips with status
     * @param status
     * @return
     */
    public List<Trip> readList(Status status) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Read all trips of driver
     * @param driver_pkey
     * @return
     */
    public List<Trip> readList(long driver_pkey) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Read all trips of passenger with Status
     * @param passenger_pkey
     * @return
     */
    public List<Trip> readList(long passenger_pkey, Status status) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Read all trips of passenger without Status
     * @param passenger_pkey
     * @return
     */
    public List<Trip> readHistoryListOfPassenger(long passenger_pkey) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Create a trip
     * @param trip
     */
    public void create(Trip trip) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Create a trip
     * @param trip
     */
    public void createABrandNew(long passenger_id, String from, String to, int price) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Updates data of trip
     * @param trip
     */
    public void update(Trip trip) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Update a trip report
     * @param trip_pkey
     * @param report
     */
    public void updateReport(long trip_pkey, String report) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Update a trip status
     * @param trip_pkey
     * @param status
     */
    public void updateStatus(long trip_pkey, Status status) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Updates trip status
     * @param trip_id
     * @param status
     */
    public void appointADriver(long trip_id, long users_pkey_driver, Status status) throws TaxiException, InterruptedException, ExecutionException, SQLException;

    /**
     * Delete trip by ID
     * @param trip_pkey
     */
    public void deleteByID(long trip_pkey) throws TaxiException, InterruptedException, ExecutionException, SQLException;

}
