package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeliveryTime implements Serializable {

    @SerializedName("timeSlotID")
    int timeSlotID;

    @SerializedName("timeSlot")
    String timeSlot;


    @SerializedName("timeFrom")
    String timeFrom;

    @SerializedName("timeTo")
    String timeTo;



    public int getTimeSlotID() {
        return timeSlotID;
    }

    public void setTimeSlotID(int timeSlotID) {
        this.timeSlotID = timeSlotID;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }


    public String getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(String timeFrom) {
        this.timeFrom = timeFrom;
    }

    public String getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(String timeTo) {
        this.timeTo = timeTo;
    }
}
