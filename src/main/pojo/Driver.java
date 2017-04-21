package main.pojo;

import main.ConnectionToDB;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Taxi Driver
 */
public class Driver {

    protected User usersPkey_driver;
    protected String fullName;
    protected String carNumber;
    protected String carDescription;
    protected String passport;
    protected Date birth;

    /**
     * Gets the value of the usersPkey property.
     *
     * @return
     *     possible object is
     *     {@link User }
     *
     */

    public User getUsersPkey() {
        return usersPkey_driver;
    }

    public Driver() {
    }

    public Driver(User usersPkey_driver) {
        this.usersPkey_driver = usersPkey_driver;
    }

    public Driver(User usersPkey_driver, String fullName, String carNumber,
                  String carDescription, String passport, Date birth) {
        this.usersPkey_driver = usersPkey_driver;
        this.fullName = fullName;
        this.carNumber = carNumber;
        this.carDescription = carDescription;
        this.passport = passport;
        this.birth = birth;
    }

    /**
     * Sets the value of the usersPkey property.
     *
     * @param value
     *     allowed object is
     *     {@link User }
     *
     */
    public void setUsersPkey(User value) {
        this.usersPkey_driver = value;
    }

    /**
     * Gets the value of the fullName property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets the value of the fullName property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setFullName(String value) {
        this.fullName = value;
    }

    /**
     * Gets the value of the carNumber property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCarNumber() {
        return carNumber;
    }

    /**
     * Sets the value of the carNumber property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCarNumber(String value) {
        this.carNumber = value;
    }

    /**
     * Gets the value of the carDescription property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getCarDescription() {
        return carDescription;
    }

    /**
     * Sets the value of the carDescription property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setCarDescription(String value) {
        this.carDescription = value;
    }

    /**
     * Gets the value of the passport property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Sets the value of the passport property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setPassport(String value) {
        this.passport = value;
    }

    /**
     * Gets the value of the birth property.
     *
     * @return
     *     possible object is
     *     {@link Date }
     *
     */
    public Date getBirth() {
        return birth;
    }

    /**
     * Sets the value of the birth property.
     *
     * @param value
     *     allowed object is
     *     {@link Date }
     *
     */
    public void setBirth(Date value) {
        this.birth = value;
    }

}
