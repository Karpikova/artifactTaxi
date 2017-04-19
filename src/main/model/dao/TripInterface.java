package main.model.dao;

import main.model.pojo.Trip;

/*
 * Passenger Interface
 */
public interface TripInterface {

    public Trip create(Trip journal);

    public Trip read(int trips_pkey);

    public void update(Trip journal);

    public void delete(Trip journal);

    public void getAll();
}
