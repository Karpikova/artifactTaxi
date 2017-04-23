package main.pojo;

import main.dao.UserImplementation;
import main.services.UserServiceImplementation;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * User of BD
 */
public class User {

    protected long usersPkey;
    protected String login;
    protected int userPassword;
    protected Date lastLogin;
    protected Date registrationDate;

    public User(String login, String userPassword) {
        try {
            Date date = new SimpleDateFormat( "dd.MM.yyyy" ).parse( "28.12.2016" );
            this.login = login;
            this.userPassword = UserImplementation.code_pas(userPassword, date);
            this.registrationDate = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public User(long usersPkey) {
        this.usersPkey = usersPkey;
    }

    public User(String login, int userPassword) {
        this.login = login;
        this.userPassword = userPassword;
    }

    public User(long usersPkey, String login) {
        this.usersPkey = usersPkey;
        this.login = login;
    }

    public User(long usersPkey, String login, int userPassword, Date lastLogin, Date registrationDate) {
        this.usersPkey = usersPkey;
        this.login = login;
        this.userPassword = userPassword;
        this.lastLogin = lastLogin;
        this.registrationDate = registrationDate;
    }

    /**
     * Gets the value of the usersPkey property.
     *
     */
    public long getUsersPkey() {
        return usersPkey;
    }

    /**
     * Sets the value of the usersPkey property.
     *
     */
    public void setUsersPkey(long value) {
        this.usersPkey = value;
    }

    /**
     * Gets the value of the login property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of the login property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setLogin(String value) {
        this.login = value;
    }

    /**
     * Gets the value of the userPassword property.
     *
     */
    public int getUserPassword() {
        return userPassword;
    }

    /**
     * Sets the value of the userPassword property.
     *
     */
    public void setUserPassword(int value) {
        this.userPassword = value;
    }

    /**
     * Gets the value of the lastLogin property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * Sets the value of the lastLogin property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setLastLogin(Date value) {
        this.lastLogin = value;
    }

    /**
     * Gets the value of the registrationDate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * Sets the value of the registrationDate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    public void setRegistrationDate(Date value) {
        this.registrationDate = value;
    }

}
