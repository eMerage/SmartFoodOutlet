package emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory;


import com.google.gson.annotations.SerializedName;

public class OrderHistoryData {

    @SerializedName("status")
    OrderHistoryStatus status;


    public OrderHistoryStatus getStatus() {
        return status;
    }

    public void setStatus(OrderHistoryStatus status) {
        this.status = status;
    }
}
