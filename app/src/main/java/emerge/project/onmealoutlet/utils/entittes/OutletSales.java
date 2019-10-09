package emerge.project.onmealoutlet.utils.entittes;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class OutletSales implements Serializable {

    @SerializedName("totalQty")
    Double totalQty;
    @SerializedName("totalValue")
    Double totalValue;
    @SerializedName("totalValueCash")
    Double totalValueCash;
    @SerializedName("totalValueCard")
    Double totalValueCard;


    public Double getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Double totalQty) {
        this.totalQty = totalQty;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getTotalValueCash() {
        return totalValueCash;
    }

    public void setTotalValueCash(Double totalValueCash) {
        this.totalValueCash = totalValueCash;
    }

    public Double getTotalValueCard() {
        return totalValueCard;
    }

    public void setTotalValueCard(Double totalValueCard) {
        this.totalValueCard = totalValueCard;
    }
}
