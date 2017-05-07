package main.dao;

import main.DB.ConnectionToDB;
import main.Exception.ExceptionDBStructure;
import main.Exception.TaxiException;
import main.beans.Profiling;
import main.pojo.Driver;
import main.pojo.Passenger;
import main.pojo.User;
import main.pojo.UserRole;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ExecutionException;

/*
 * Implemntation of UserInterface for postgressql DB
 */
@Repository
@Profiling
public class UserImplementation implements UserInterface {

    public void create(User user) throws TaxiException, InterruptedException, ExecutionException, SQLException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String qText = "INSERT INTO public.\"users\"(\n" +
                "  users_pkey, login, password, last_login, registration_date)\n" +
                "  VALUES (" +
                user.getUsersPkey() +", " +
                user.getLogin() + ", " +
                user.getUserPasswordCrypto() + ", " +
                user.getLastLogin() + ", " +
                format.format( user.getRegistrationDate());
        ConnectionToDB.execute(qText);
    }

    public User createBrandNew(User user) throws TaxiException, SQLException, ExecutionException, InterruptedException {

        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.toConnect();
        String qTextEx = "SELECT * " +
                " FROM " +
                "  public.\"users\"" +
                "WHERE \n" +
                "  \"users\".login = '" + user.getLogin() + "' LIMIT 1;";
        Statement st = connection.createStatement();
        ResultSet resultSet = st.executeQuery(qTextEx);
        if (resultSet.next()) {
            return null;
        }

        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        Date curDate = new Date();
        String qText = "INSERT INTO public.\"users\"(\n" +
                "  login, last_login, registration_date, password, role)\n" +
                "  VALUES (" +
                "'" + user.getLogin() + "', " +
                "'" + format.format(curDate) + "', " +
                "'" + format.format(curDate) + "' ," +
                "'" + user.getUserPasswordCrypto() + "', " +
                "'ROLE_USER')";
        st.executeUpdate(qText);

        resultSet = st.executeQuery(qTextEx);
        resultSet.next();
        user.setUsersPkey(resultSet.getInt("users_pkey"));

        return user;
    }

    public User read(int usersPkey) {
        return null;
    }

    public User read(String login) throws TaxiException, ExecutionException, InterruptedException, ParseException, SQLException, IOException {
        User user = null;
        ConnectionToDB connectionToDB = new ConnectionToDB();
        Connection connection = connectionToDB.toConnect();
        Statement st = connection.createStatement();
        String qText = "SELECT * \n" +
                "FROM \n" +
                "  public.\"users\"\n" +
                "WHERE \n" +
                "  \"users\".login = '" + login + "' AND \n" +
                " LIMIT 1;";
        ResultSet resultSet = st.executeQuery(qText);
        if (resultSet.next()) {
            user = new User(
                    resultSet.getInt("users_pkey"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getDate("last_login"),
                    resultSet.getDate("registration_date"));
        }
        return user;
    }

    public void update(User journal) {

    }

    public void delete(User journal) {

    }

    public void getAll() {

    }

    public Date getRegDate(String login) throws TaxiException, ParseException { //It's a fake
        Date date = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "28.12.2016" );
        return date;
    }

    public UserRole getRole(String login) throws Exception {
        UserRole user = null;
        DriverInterface driverInterface = new DriverImplementation();
        Driver driver = driverInterface.read(login);
        if (driver!=null){
            return UserRole.Driver;
        }
        PassengerInterface passengerInterface = new PassengerImplementation();
        Passenger passenger = passengerInterface.read(login);
        if (passenger!=null){
            return UserRole.Passenger;
        }

        throw new ExceptionDBStructure("There exists login without role");

    }

    /**
     * The method codes a password to int. We need
     *
     * @param password
     * @param registration_date
     * @return encoded password
     */
    public static int code_pas(String password, Date registration_date) throws TaxiException, IOException {
        Calendar registration_date_calendar = new GregorianCalendar();
        registration_date_calendar.setTime(registration_date);

        int secret_number = 1;
        String file_name = "D:\\1.txt";

        secret_number = Integer.parseInt((new BufferedReader(new FileReader(file_name)).readLine()));

        int result = (1 + (registration_date_calendar.get(Calendar.MONTH)))
                * (registration_date_calendar.get(Calendar.YEAR))
                * (registration_date_calendar.get(Calendar.DAY_OF_MONTH))
                * secret_number;
        for (char symbol :
                password.toCharArray()) {
            if (result > 32000) {//чтобы не произошло переполнение int
                result = (int) Math.sqrt((double) result);
            }
            result = result * (int) symbol;
        }
        return result;
    }

}
