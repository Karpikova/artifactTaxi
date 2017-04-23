package main.dao;

import main.ConnectionToDB;
import main.pojo.Driver;
import main.pojo.Passenger;
import main.pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/*
 * Implemntation of UserInterface for postgressql DB
 */
public class PassengerImplementation implements PassengerInterface{

    public void create(Passenger passenger) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String qText = "INSERT INTO public.\"Passenger\"(\n" +
                "(users_pkey_driver, full_name, birth)" +
                "  VALUES (" +
                passenger.getUsersPkey() +", " +
                passenger.getFullName() + ", " +
                format.format(passenger.getBirth());
        ConnectionToDB.executeQuery(qText);
    }

    public Passenger read(int users_pkey_pas) {
        return null;
    }

    public Passenger read(String login) {
        Passenger passenger = null;
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            String qtext = "SELECT * FROM PUBLIC.\"Passenger\" Ps LEFT JOIN PUBLIC.\"User\" Us\n" +
                    "ON Us.users_pkey = Ps.users_pkey_pas\n" +
                    "WHERE Us.login = '"+ login +"' LIMIT 1";
            ResultSet resultSet = st.executeQuery(qtext);
            if (resultSet.next()) {
                passenger = new Passenger();
                User user = new User(resultSet.getLong("users_pkey_pas"), login);
                passenger.setUsersPkey(user);
                passenger.setFullName(resultSet.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return passenger;
    }

    public void update(Passenger passenger) {

    }

    public void delete(Passenger passenger) {

    }

    public void getAll() {

    }
}
