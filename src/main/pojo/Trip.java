package main.pojo;

import java.util.Date;

/**
 * Trip: a driver drives a passenger
 */
public class Trip {

    protected long trips_pkey;
    protected Driver driverPkey;
    protected Passenger passengerPkey;
    protected String addressFrom;
    protected String addressTo;
    protected Date dateStart;
    protected Date dateChange;
    protected int price;
    protected Status status;
    protected boolean estimate;
    protected String report;

    public Trip(long trips_pkey, Driver driverPkey, Passenger passengerPkey, String addressFrom,
                String addressTo, Date dateStart, Date dateChange, int price, Status status,
                boolean estimate, String report) {
        this.trips_pkey = trips_pkey;
        this.driverPkey = driverPkey;
        this.passengerPkey = passengerPkey;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
        this.dateStart = dateStart;
        this.dateChange = dateChange;
        this.price = price;
        this.status = status;
        this.estimate = estimate;
        this.report = report;
    }

    public Trip(long trips_pkey, Driver driver, Passenger passenger, String addressFrom, String addressTo, int price) {
        this.driverPkey = driver;
        this.passengerPkey = passenger;
        this.trips_pkey = trips_pkey;
        this.price = price;
        this.addressFrom = addressFrom;
        this.addressTo = addressTo;
    }

    /**
     * Gets the value of the tripsId property.
     *
     */
    public long gettrips_pkey() {
        return trips_pkey;
    }

    /**
     * Sets the value of the tripsId property.
     *
     */
    public void settrips_pkey(long value) {
        this.trips_pkey = value;
    }

    /**
     * Gets the value of the driverPkey property.
     *
     * @return
     *     possible object is
     *     {@link Driver }
     *
     */
    public Driver getDriverPkey() {
        return driverPkey;
    }

    /**
     * Sets the value of the driverPkey property.
     *
     * @param value
     *     allowed object is
     *     {@link Driver }
     *
     */
    public void setDriverPkey(Driver value) {
        this.driverPkey = value;
    }

    /**
     * Gets the value of the passengerPkey property.
     *
     * @return
     *     possible object is
     *     {@link Passenger }
     *
     */
    public Passenger getPassengerPkey() {
        return passengerPkey;
    }

    /**
     * Sets the value of the passengerPkey property.
     *
     * @param value
     *     allowed object is
     *     {@link Passenger }
     *
     */
    public void setPassengerPkey(Passenger value) {
        this.passengerPkey = value;
    }

    /**
     * Gets the value of the addressFrom property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAddressFrom() {
        return addressFrom;
    }

    /**
     * Sets the value of the addressFrom property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAddressFrom(String value) {
        this.addressFrom = value;
    }

    /**
     * Gets the value of the addressTo property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getAddressTo() {
        return addressTo;
    }

    /**
     * Sets the value of the addressTo property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setAddressTo(String value) {
        this.addressTo = value;
    }

    /**
     * Gets the value of the dateStart property.
     *
     * @return
     *     possible object is
     *     {@link Date }
     *
     */
    public Date getDateStart() {
        return dateStart;
    }

    /**
     * Sets the value of the dateStart property.
     *
     * @param value
     *     allowed object is
     *     {@link Date }
     *
     */
    public void setDateStart(Date value) {
        this.dateStart = value;
    }

    /**
     * Gets the value of the dateChange property.
     *
     * @return
     *     possible object is
     *     {@link Date }
     *
     */
    public Date getDateChange() {
        return dateChange;
    }

    /**
     * Sets the value of the dateChange property.
     *
     * @param value
     *     allowed object is
     *     {@link Date }
     *
     */
    public void setDateChange(Date value) {
        this.dateChange = value;
    }

    /**
     * Gets the value of the price property.
     *
     */
    public int getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     *
     */
    public void setPrice(int value) {
        this.price = value;
    }

    /**
     * Gets the value of the status property.
     *
     * @return
     *     possible object is
     *     {@link Status }
     *
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     *
     * @param value
     *     allowed object is
     *     {@link Status }
     *
     */
    public void setStatus(Status value) {
        this.status = value;
    }

    /**
     * Gets the value of the estimate property.
     *
     */
    //@XmlJavaTypeAdapter(BooleanAdapter.class)
    public boolean isEstimate() {
        return estimate;
    }

    /**
     * Sets the value of the estimate property.
     *
     */
    public void setEstimate(boolean value) {
        this.estimate = value;
    }

    /**
     * Gets the value of the report property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getReport() {
        if (report == null)
            return "";

        return report;
    }

    /**
     * Sets the value of the report property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setReport(String value) {
        this.report = value;
    }

}
