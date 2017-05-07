package main.dao;

import main.DB.ConnectionToDB;
import main.Exception.TaxiException;
import main.pojo.Passenger;
import main.pojo.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;

/*
 * Implemntation of UserInterface for postgressql DB
 */
@Repository
public class PassengerImplementation implements PassengerInterface{

    public void create(Passenger passenger) throws TaxiException, InterruptedException, ExecutionException, SQLException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String qText = "INSERT INTO public.\"Passenger\"" +
                "(users_pkey_pas, full_name, birth)" +
                "  VALUES (" +
                Long.valueOf(passenger.getUsersPkey().getUsersPkey()) +", " +
                "'" + passenger.getFullName() + "' , " +
                "'" + format.format(passenger.getBirth()) + "')";
        ConnectionToDB.execute(qText);
    }

    public Passenger read(int users_pkey_pas) {
        return null;
    }

    public Passenger read(String login) throws Exception {
        Passenger passenger = null;
        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.toConnect();
        Statement st = connection.createStatement();
        String qtext = "SELECT * FROM PUBLIC.\"Passenger\" Ps LEFT JOIN PUBLIC.\"users\" Us\n" +
                "ON Us.users_pkey = Ps.users_pkey_pas\n" +
                "WHERE Us.login = '" + login + "' LIMIT 1";
        ResultSet resultSet = st.executeQuery(qtext);
        if (resultSet.next()) {
            passenger = new Passenger();
            User user = new User(resultSet.getLong("users_pkey_pas"), login);
            passenger.setUsersPkey(user);
            passenger.setFullName(resultSet.getString("full_name"));
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
