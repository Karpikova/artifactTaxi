package main.services;


import main.pojo.Status;
import main.pojo.Trip;

import java.util.List;

/*
 * Trip Service Interface
 */
public interface TripServiceInterface {
    /**
     * Read all trips with status
     * @param status
     * @return
     */
    public List<Trip> readList(Status status);

    /**
     * Read all trips of driver
     * @param driver_pkey
     * @return
     */
    public List<Trip> readList(long driver_pkey);

    /**
     * Read all trips of passenger with Status
     * @param passenger_pkey
     * @return
     */
    public List<Trip> readList(long passenger_pkey, Status status);

    /**
     * Read all trips of passenger without Status
     * @param passenger_pkey
     * @return
     */
    public List<Trip> readListExStatus(long passenger_pkey, Status status);

    /**
     * Create a trip
     * @param trip
     */
    public void create(Trip trip);

    /**
     * Create a trip
     * @param trip
     */
    public void createABrandNew(long passenger_id, String from, String to, int price);

    /**
     * Updates data of trip
     * @param trip
     */
    public void update(Trip trip);

    /**
     * Update a trip report
     * @param trip_pkey
     * @param report
     */
    public void updateReport(long trip_pkey, String report);

    /**
     * Update a trip status
     * @param trip_pkey
     * @param status
     */
    public void updateStatus(long trip_pkey, Status status);

    /**
     * Updates trip status
     * @param trip_id
     * @param status
     */
    public void appointADriver(long trip_id, long users_pkey_driver, Status status);

    /**
     * Delete trip by ID
     * @param trip_pkey
     */
    public void deleteByID(long trip_pkey);

}
