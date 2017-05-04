package main.services;

import main.Exception.TaxiException;
import main.dao.TripInterface;
import main.pojo.Status;
import main.pojo.Trip;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Trip Service Implementation
 */
@Service
public class TripServiceImplementation implements TripServiceInterface{

    private static final org.apache.log4j.Logger logger = Logger.getLogger(TripServiceImplementation.class);

    @Autowired
    private TripInterface tripInterface;// = new TripImplementation();

    public List<Trip> readList(Status status) throws TaxiException {
        return tripInterface.readList(status);
    }

    public List<Trip> readList(long driver_pkey) throws TaxiException {
        return tripInterface.readList(driver_pkey);
    }

    public List<Trip> readList(long passenger_pkey, Status status) throws TaxiException {
        return tripInterface.readList(passenger_pkey, status);
    }

    public List<Trip> readHistoryListOfPassenger(long passenger_pkey) throws TaxiException {
        return tripInterface.readHistoryListOfPassenger(passenger_pkey);
    }

    public void create(Trip trip) throws TaxiException {
        tripInterface.create(trip);
    }

    public void createABrandNew(long passenger_id, String from, String to, int price) throws TaxiException {
        tripInterface.createABrandNew(passenger_id, from, to, price);
    }

    public void update(Trip trip) throws TaxiException {
        tripInterface.update(trip);
    }

    public void updateReport(long trip_pkey, String report) throws TaxiException {
        tripInterface.updateReport(trip_pkey, report);
    }

    public void updateStatus(long trip_pkey, Status status) throws TaxiException {
        tripInterface.updateStatus(trip_pkey, status);
    }

    public void appointADriver(long trip_id, long users_pkey_driver, Status status) throws TaxiException {
        tripInterface.appointADriver(trip_id, users_pkey_driver, status);
    }

    public void deleteByID(long trip_pkey) throws TaxiException {
        tripInterface.deleteByID(trip_pkey);
    };
}
