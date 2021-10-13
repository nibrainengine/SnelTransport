package CIMSOLUTIONS.SnelTransport.DTO;

import java.util.Date;

public class ScheduleDTO {

    //declare variables
    private Date startTime;
    private Date endTime;
    private int nrScheduledCouriers;

    public ScheduleDTO(Date startTime, Date endTime, int nrScheduledCouriers) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.nrScheduledCouriers = nrScheduledCouriers;
    }

    public ScheduleDTO() {
    }

    //getters and setters
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getNrScheduledCouriers() {
        return nrScheduledCouriers;
    }

    public void setNrScheduledCouriers(int nrScheduledCouriers) {
        this.nrScheduledCouriers = nrScheduledCouriers;
    }
}
