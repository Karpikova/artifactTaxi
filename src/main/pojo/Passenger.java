package main.pojo;

import javax.xml.bind.annotation.*;
import java.util.Date;

/**
 * Taxi Passenger
 */
public class Passenger {

    protected User usersPkey_pas;
    protected String fullName;
    protected Date birth;

    public Passenger() {
    }

    public Passenger(User usersPkey_pas) {
        this.usersPkey_pas = usersPkey_pas;
    }

    public Passenger(User usersPkey_pas, String fullName, Date birth) {
        this.usersPkey_pas = usersPkey_pas;
        this.fullName = fullName;
        this.birth = birth;
    }

    /**
     * Gets the value of the usersPkey property.
     *
     * @return
     *     possible object is
     *     {@link User }
     *
     */
    public User getUsersPkey() {
        return usersPkey_pas;
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
        this.usersPkey_pas = value;
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
