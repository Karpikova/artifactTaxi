package main.model.pojo;

/**
 * Status of trip
 */
public enum Status {

    CREATED("Created"),
    APPOINTED("Appointed"),
    IN_PROCESS("In_process"),
    EXCECUTED("Excecuted"),
    PAID_BY_DRIVER("Paid_by_driver"),
    CLOSED("Closed");
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
