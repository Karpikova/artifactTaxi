package main.dao;

import main.ConnectionToDB;
import main.Exception.ExceptionDBStructure;
import main.pojo.Driver;
import main.pojo.Passenger;
import main.pojo.User;
import main.pojo.UserRole;

import javax.xml.datatype.XMLGregorianCalendar;
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
public class UserImplementation implements UserInterface {

    public void create(User user) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            ConnectionToDB connectionToDB = new ConnectionToDB();
            Connection connection = connectionToDB.toConnect();
            Statement st = connection.createStatement();
            ResultSet resultSet = st.executeQuery("INSERT INTO public.\"User\"(\n" +
                    "  users_pkey, login, users_password, last_login, registration_date)\n" +
                    "  VALUES (" +
                            user.getUsersPkey() +", " +
                            user.getLogin() + ", " +
                            user.getUserPassword() + ", " +
                            user.getLastLogin() + ", " +
                            format.format( user.getRegistrationDate())
                            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User read(int usersPkey) {
        return null;
    }

    public User read(String login, String userPasswordPre){
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
            e.printStackTrace();
        }
        return user;
    }

    public void update(User journal) {

    }

    public void delete(User journal) {

    }

    public void getAll() {

    }

    public Date getRegDate(String login) { //It's a fake
        Date date = null;
        try {
            date = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "28.12.2016" );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public UserRole getRole(String login) {
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
            exceptionDBStructure.printStackTrace();
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
    public int code_pas(String password, Date registration_date) {
        Calendar registration_date_calendar = new GregorianCalendar();
        registration_date_calendar.setTime(registration_date);

        int secret_number = 1;
        String file_name = "D:\\1.txt";
        try {
            secret_number = Integer.parseInt((new BufferedReader(new FileReader(file_name)).readLine()));

        } catch (IOException e) {

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
}
