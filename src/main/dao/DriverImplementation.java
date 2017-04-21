package main.dao;

import com.sun.org.apache.regexp.internal.RE;
import main.ConnectionToDB;
import main.pojo.Driver;
import main.pojo.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/*
 * Implemntation of UserInterface for postgressql DB
 */
public class DriverImplementation implements DriverInterface {

    public void create(Driver driver) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            st.executeQuery("INSERT INTO public.\"Driver\"(\n" +
                    "(users_pkey_driver, full_name, car_number, car_description, passport, birth)" +
                    "  VALUES (" +
                    driver.getUsersPkey() +", " +
                    driver.getFullName() + ", " +
                    driver.getCarNumber() + ", " +
                    driver.getCarDescription() + ", " +
                    driver.getPassport() + ", " +
                    format.format(driver.getBirth())
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Driver read(int users_pkey_driver) {
        return null;
    }

    public Driver read(String login) {
        Driver driver = null;
        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            String qtext = "SELECT * FROM PUBLIC.\"Driver\" Dr LEFT JOIN PUBLIC.\"User\" Us\n" +
                    "ON Us.users_pkey = Dr.users_pkey_driver\n" +
                    "WHERE Us.login = '"+ login +"' LIMIT 1";
            ResultSet resultSet = st.executeQuery(qtext);
            if (resultSet.next()) {
                driver = new Driver();
                User user = new User(resultSet.getLong("users_pkey_driver"), login);
                driver.setUsersPkey(user);
                driver.setFullName(resultSet.getString("full_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return driver;
    }

    public void update(Driver driver) {

    }

    public void delete(Driver driver) {

    }

    public void getAll() {

    }
}
