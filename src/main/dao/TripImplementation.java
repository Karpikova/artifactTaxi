package main.dao;

import main.ConnectionToDB;
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
import java.util.Date;
import java.util.List;

/*
 * Implementation of UserInterface for postgressql DB
 */
public class TripImplementation implements TripInterface {

    static {
        PropertyConfigurator.configure(LoginServlet.class.getClassLoader()
                .getResource("log4j.properties"));
    }

    private static final org.apache.log4j.Logger logger = Logger.getLogger(TripImplementation.class);

    public  void create(Trip trip) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
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
        ConnectionToDB.executeQuery(qtext);
    }

    public void createABrandNew(long passenger_id, String from, String to, int price) {
        Date curDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String qtext = "INSERT INTO public.\"Trip\"(" +
                "passenger_pkey, address_from, address_to, date_start, " +
                "date_change, price, status)\n" +
                "  VALUES (" +
                passenger_id +", " +
                "'" + from + "', " +
                "'" + to + "', " +
                "'" + format.format(curDate) + "', " +
                "'" + format.format(curDate) + "', " +
                price + ", " +
                "'" + Status.Created.value() + "')";
        ConnectionToDB.executeQuery(qtext);
    }

    private Trip createATripByResultSet(ResultSet resultSet) throws SQLException {
        User user_dr = new User(resultSet.getInt("driver_pkey"));
        User user_pas = new User(resultSet.getInt("passenger_pkey"));
        Driver driver = new Driver(user_dr);
        Passenger passenger = new Passenger(user_pas);
        return new Trip(resultSet.getInt("trips_pkey"),
                driver,
                passenger,
                resultSet.getString("address_from"),
                resultSet.getString("address_to"),
                resultSet.getDate("date_start"),
                resultSet.getDate("date_change"),
                resultSet.getInt("price"),
                Status.valueOf(resultSet.getString("status")),
                resultSet.getBoolean("estimate"),
                resultSet.getString("report"));
    }

    public Trip readList(int trips_pkey) {
        return null;
    }

    public List<Trip> readList(Status status) {
        String qtext = "SELECT * FROM public.\"Trip\" WHERE " +
                "status = '" + status.value() + "' ORDER BY date_start";
        return readList(qtext);
    }

    public List<Trip> readList(long driver_pkey) {
        String qtext = "SELECT * FROM public.\"Trip\" WHERE " +
                "driver_pkey = '" + driver_pkey + "' ORDER BY date_start";
        return readList(qtext);
    }

    public List<Trip> readList(long passenger_pkey, Status status) {
        String qtext = "SELECT * FROM public.\"Trip\" WHERE " +
                "passenger_pkey = " + passenger_pkey + "" +
                " AND status = '" + status.value() + "'" +
                " ORDER BY date_start";
        return readList(qtext);
    }

    public List<Trip> readListExStatus(long passenger_pkey, Status status) {
        String qtext = "SELECT * FROM public.\"Trip\" WHERE " +
                "passenger_pkey = " + passenger_pkey + "" +
                " AND status <> '" + status.value() + "'" +
                " ORDER BY date_start";
        return readList(qtext);
    }

    private List<Trip> readList(String qtext) {
        List<Trip> trips = new ArrayList<Trip>();
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(qtext);
            while (resultSet.next()) {
                Trip trip = createATripByResultSet(resultSet);
                trips.add(trip);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }

    public void update(Trip trip) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
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
        ConnectionToDB.executeQuery(qtext);
    }

    public void updateReport(long trip_pkey, String report) {
        String qtext = "UPDATE public.\"Trip\"" +
                "SET " +
                " report = '" + report + "'" +
                "WHERE trips_pkey = " + Long.valueOf(trip_pkey);
        ConnectionToDB.executeQuery(qtext);
    }

    public void updateStatus(long trip_pkey, Status status) {
        String qtext = "UPDATE public.\"Trip\"" +
                "SET " +
                " status = '" + status.value() + "'" +
                "WHERE trips_pkey = " + Long.valueOf(trip_pkey);
        ConnectionToDB.executeQuery(qtext);
    }


    public void appointADriver(long trips_pkey, long users_pkey_driver, Status status) {
        String qtext = "UPDATE public.\"Trip\"" +
                " SET " +
                " status = '" + status.value() + "'" +
                " ,driver_pkey = " + users_pkey_driver +
                " WHERE trips_pkey = " + Long.toString(trips_pkey);
        ConnectionToDB.executeQuery(qtext);
    }

    public void delete(Trip trip) {

    }

    public void deleteByID(long trip_pkey) {
        String qtext = "DELETE FROM \"Trip\"" +
                " WHERE trips_pkey = " + trip_pkey;
        ConnectionToDB.executeQuery(qtext);
    }

    public void getAll() {

    }

    public void updateStatus(Status status, long trip_id) {

    }


}
