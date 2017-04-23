package main.pojo;

/**
 * Status of trip
 */
public enum Status {

    Created("Created"),
    Appointed("Appointed"),
    In_process("In_process"),
    Excecuted("Excecuted"),
    Paid_by_driver("Paid_by_driver"),
    Cancelled("Cancelled");
    private final String value;

    Status(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Status fromValue(String v) {
        for (Status c: Status.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
