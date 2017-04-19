package main.model.pojo;

import java.util.Date;

/**
 * Passenger of Taxi
 */
public class Passenger {

    protected User usersPkey_pas;
    protected String fullName;
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
