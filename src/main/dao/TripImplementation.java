package main.dao;

import main.ConnectionToDB;
import main.controllers.DriverMainServlet;
import main.controllers.LoginServlet;
import main.pojo.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/*
 * Implementation of UserInterface for postgressql DB
 */
public class TripImplementation implements TripInterface {

    public static void main(String[] args) {
        User user = null;
        String login = "Katusha";
        int userPassword = 959136;

    }

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }
    private static final org.apache.log4j.Logger logger = Logger.getLogger(TripImplementation.class);


    public  void create(Trip trip) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            String qtext = "INSERT INTO public.\"Trip\"(" +
                    "trips_pkey, driver_pkey, passenger_pkey, address_from, address_to, date_start, " +
                    "date_change, price, status, estimate, report)\n" +
                    "  VALUES (" +
                    trip.gettrips_pkey() +", " +
                    trip.getDriverPkey().getUsersPkey().getUsersPkey() + ", " +
                    trip.getPassengerPkey().getUsersPkey().getUsersPkey() + ", '" +
                    trip.getAddressFrom() + "' , '" +
                    trip.getAddressTo() + "', '" +
                    format.format(trip.getDateStart()) + "' , '" +
                    format.format(trip.getDateChange()) + "' , " +
                    Integer.toString(trip.getPrice()) + ", '" +
                    trip.getStatus().value() + "', " +
                    trip.isEstimate() + ", '" +
                    trip.getReport() +"' )";
            st.executeQuery(qtext);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Trip readList(int trips_pkey) {
        return null;
    }

    public List<Trip> readList(Status status) {
        List<Trip> trips = new ArrayList<Trip>();
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            String qtext = "SELECT * FROM public.\"Trip\" WHERE " +
                    "status = '" + status.value() + "' ORDER BY date_start";
            ResultSet resultSet = st.executeQuery(qtext);
            while (resultSet.next()) {
                User user_dr = new User(resultSet.getInt("driver_pkey"));
                User user_pas = new User(resultSet.getInt("passenger_pkey"));
                Driver driver = new Driver(user_dr);
                Passenger passenger = new Passenger(user_pas);
                Trip trip = new Trip(resultSet.getInt("trips_pkey"),
                        driver,
                        passenger,
                        resultSet.getString("address_from"),
                        resultSet.getString("address_to"),
                        resultSet.getInt("price"));
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    };

    public void update(Trip trip) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            String qtext = "UPDATE public.\"Trip\"" +
                    "SET " +
                    " driver_pkey = " + trip.getDriverPkey().getUsersPkey().getUsersPkey() +
                    " ,passenger_pkey = " + trip.getPassengerPkey().getUsersPkey().getUsersPkey() +
                    " ,address_from = '" + trip.getAddressFrom() + "'" +
                    " ,address_to = '" + trip.getAddressTo() + "'" +
                    " ,date_start = " + format.format(trip.getDateStart()) +
                    " ,date_change = " + format.format(trip.getDateStart()) +
                    " ,price = " + Integer.toString(trip.getPrice()) +
                    " ,status = '" + trip.getStatus().value() + "'" +
                    " ,estimate = " + trip.isEstimate() +
                    " ,report = '" + trip.getReport() + "'" +
                    "WHERE trips_pkey = " + trip.gettrips_pkey();
            st.executeQuery(qtext);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void appointADriver(long trips_pkey, long users_pkey_driver, Status status) {
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            String qtext = "UPDATE public.\"Trip\"" +
                    " SET " +
                    " status = '" + status.value() + "'" +
                    " ,driver_pkey = " + users_pkey_driver +
                    " WHERE trips_pkey = " + Long.toString(trips_pkey);
            logger.info(qtext);
            st.executeQuery(qtext);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Trip trip) {

    }

    public void getAll() {

    }

    public void updateStatus(Status status, long trip_id) {

    }

}
