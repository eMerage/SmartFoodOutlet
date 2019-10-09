package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DeliveryRiders implements Serializable {



    @SerializedName("id")
    int riderID;

    @SerializedName("name")
    String riderName;


    public DeliveryRiders(int riderID, String riderName) {
        this.riderID = riderID;
        this.riderName = riderName;
    }


    public int getRiderID() {
        return riderID;
    }

    public void setRiderID(int riderID) {
        this.riderID = riderID;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }
}
