package main.dao;

import main.ConnectionToDB;
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

/*
 * Implemntation of UserInterface for postgressql DB
 */
@Repository
@Profiling
public class UserImplementation implements UserInterface {

    private static final org.apache.log4j.Logger logger = Logger.getLogger(UserImplementation.class);

    public void create(User user) throws TaxiException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        String qText = "INSERT INTO public.\"User\"(\n" +
                "  users_pkey, login, users_password, last_login, registration_date)\n" +
                "  VALUES (" +
                user.getUsersPkey() +", " +
                user.getLogin() + ", " +
                user.getUserPassword() + ", " +
                user.getLastLogin() + ", " +
                format.format( user.getRegistrationDate());
        ConnectionToDB.execute(qText);
    }

    public User createBrandNew(User user) throws TaxiException {
        try {

            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            String qTextEx = "SELECT * " +
                    " FROM " +
                    "  public.\"User\"" +
                    "WHERE \n" +
                    "  \"User\".login = '" + user.getLogin() + "' LIMIT 1;";
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery(qTextEx);
            if (resultSet.next()) {
                return null;
            };


            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            Date curDate = new Date();
            String qText = "INSERT INTO public.\"User\"(\n" +
                    "  login, users_password, last_login, registration_date)\n" +
                    "  VALUES (" +
                    "'" + user.getLogin() + "', " +
                    "" + user.getUserPassword() + ", " +
                    "'" + format.format(curDate) + "', " +
                    "'" + format.format(curDate) + "')";
            st.executeUpdate(qText);

            resultSet = st.executeQuery(qTextEx);
            resultSet.next();
                user.setUsersPkey(resultSet.getInt("users_pkey"));
        } catch (SQLException e) {
            logger.error(e);
            throw new TaxiException(e.getMessage());
        }
        return user;
    }

    public User read(int usersPkey) {
        return null;
    }

    public User read(String login, String userPasswordPre) throws TaxiException {
        int userPassword = code_pas(userPasswordPre, getRegDate(login));
        User user = null;

        try {
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            String qText = "SELECT * \n" +
                    "FROM \n" +
                    "  public.\"User\"\n" +
                    "WHERE \n" +
                    "  \"User\".login = '" + login + "' AND \n" +
                    "  \"User\".users_password = " + Integer.toString(userPassword) + " LIMIT 1;";
            ResultSet resultSet = st.executeQuery(qText);
            if (resultSet.next()) {
                user = new User(
                        resultSet.getInt("users_pkey"),
                        resultSet.getString("login"),
                        resultSet.getInt("users_password"),
                        resultSet.getDate("last_login"),
                        resultSet.getDate("registration_date"));
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new TaxiException(e.getMessage());
        }
        return user;
    }

    public void update(User journal) {

    }

    public void delete(User journal) {

    }

    public void getAll() {

    }

    public Date getRegDate(String login) throws TaxiException { //It's a fake
        Date date = null;
        try {
            date = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "28.12.2016" );
        } catch (ParseException e) {
            logger.error(e);
            throw new TaxiException(e.getMessage());
        }
        return date;
    }

    public UserRole getRole(String login) throws TaxiException {
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
        try {
            throw new ExceptionDBStructure("There exists login without role");
        } catch (ExceptionDBStructure exceptionDBStructure) {
            logger.error(exceptionDBStructure);
        }
        return null;
    }

    /**
     * The method codes a password to int. We need
     *
     * @param password
     * @param registration_date
     * @return encoded password
     */
    public static int code_pas(String password, Date registration_date) throws TaxiException {
        Calendar registration_date_calendar = new GregorianCalendar();
        registration_date_calendar.setTime(registration_date);

        int secret_number = 1;
        String file_name = "D:\\1.txt";
        try {
            secret_number = Integer.parseInt((new BufferedReader(new FileReader(file_name)).readLine()));
        } catch (IOException e) {
            logger.error(e);
            throw new TaxiException(e.getMessage());
        }
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

    public static void main(String args[]){
        Number n = new Integer(1);
        int y = (n.intValue());
    }
}
