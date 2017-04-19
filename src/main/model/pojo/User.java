package main.model.pojo;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

/**
 * User of BD
 */
public class User {

    protected long usersPkey;
    protected String login;
    protected int userPassword;
    protected Date lastLogin;
    protected Date registrationDate;

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
