package CIMSOLUTIONS.SnelTransport.DTO;

public class CancelCourierScheduleRequestDTO {
    //declare variables
    int courierScheduleId;
    String reason;

    //getters and setters
    public int getCourierScheduleId() {
        return courierScheduleId;
    }

    public void setCourierScheduleId(int courierScheduleId) {
        this.courierScheduleId = courierScheduleId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
