package main.services;

import main.dao.TripImplementation;
import main.dao.TripInterface;
import main.pojo.Status;
import main.pojo.Trip;

import java.util.List;

/*
 * Trip Service Implementation
 */
public class TripServiceImplementation implements TripServiceInterface{

    public static TripInterface tripInterface = new TripImplementation();

    public List<Trip> readList(Status status) {
        return tripInterface.readList(status);
    }

    public List<Trip> readList(long driver_pkey) {
        return tripInterface.readList(driver_pkey);
    }

    public List<Trip> readList(long passenger_pkey, Status status) {
        return tripInterface.readList(passenger_pkey, status);
    }

    public List<Trip> readListExStatus(long passenger_pkey, Status status) {
        return tripInterface.readListExStatus(passenger_pkey, status);
    }

    public void create(Trip trip) {
        tripInterface.create(trip);
    }

    public void createABrandNew(long passenger_id, String from, String to, int price) {
        tripInterface.createABrandNew(passenger_id, from, to, price);
    }

    public void update(Trip trip) {
        tripInterface.update(trip);
    }

    public void updateReport(long trip_pkey, String report) {
        tripInterface.updateReport(trip_pkey, report);
    }

    public void updateStatus(long trip_pkey, Status status) {
        tripInterface.updateStatus(trip_pkey, status);
    }

    public void appointADriver(long trip_id, long users_pkey_driver, Status status) {
        tripInterface.appointADriver(trip_id, users_pkey_driver, status);
    }

    public void deleteByID(long trip_pkey){
        tripInterface.deleteByID(trip_pkey);
    };
}
