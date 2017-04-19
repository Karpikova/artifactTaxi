package main.model.dao;

import main.model.pojo.Passenger;

/*
 * Passenger Interface
 */
public interface PassengerInterface {

    public Passenger create(Passenger journal);

    public Passenger read(int users_pkey_pas);

    public void update(Passenger journal);

    public void delete(Passenger journal);

    public void getAll();
}
