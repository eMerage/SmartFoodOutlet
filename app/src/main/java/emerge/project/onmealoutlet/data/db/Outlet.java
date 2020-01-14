package emerge.project.onmealoutlet.data.db;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Outlet extends RealmObject implements Serializable {


    @PrimaryKey
    long rowid;

    @SerializedName("outletID")
    int outletId;

    @SerializedName("name")
    String outletname;


    @SerializedName("pickupTimeInterval")
    int pickupTimeInterval;


    @SerializedName("id")
    int userID;



    public int getOutletId() {
        return outletId;
    }

    public void setOutletId(int outletId) {
        this.outletId = outletId;
    }

    public String getOutletname() {
        return outletname;
    }

    public void setOutletname(String outletname) {
        this.outletname = outletname;
    }

    public int getPickupTimeInterval() {
        return pickupTimeInterval;
    }

    public void setPickupTimeInterval(int pickupTimeInterval) {
        this.pickupTimeInterval = pickupTimeInterval;
    }

    public long getRowid() {
        return rowid;
    }

    public void setRowid(long rowid) {
        this.rowid = rowid;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
