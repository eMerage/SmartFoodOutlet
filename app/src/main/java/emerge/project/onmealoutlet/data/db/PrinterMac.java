package emerge.project.onmealoutlet.data.db;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PrinterMac extends RealmObject {


    @PrimaryKey
    long rowid;

    String macAddress;


    public long getRowid() {
        return rowid;
    }

    public void setRowid(long rowid) {
        this.rowid = rowid;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }
}
