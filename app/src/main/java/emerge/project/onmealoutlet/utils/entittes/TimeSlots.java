package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class TimeSlots implements Serializable {

    @SerializedName("timeSlotID")
    int slotID;

    @SerializedName("timeSlot")
    String slotsName;


    public TimeSlots(int slotID, String slotsName) {
        this.slotID = slotID;
        this.slotsName = slotsName;
    }


    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public String getSlotsName() {
        return slotsName;
    }

    public void setSlotsName(String slotsName) {
        this.slotsName = slotsName;
    }
}
