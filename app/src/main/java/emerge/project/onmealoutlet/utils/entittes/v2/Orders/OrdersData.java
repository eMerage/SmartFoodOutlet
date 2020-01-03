package emerge.project.onmealoutlet.utils.entittes.v2.Orders;


import com.google.gson.annotations.SerializedName;

import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryStatus;

public class OrdersData {

    @SerializedName("status")
    OrdersStatus status = new OrdersStatus();


    public OrdersStatus getStatus() {
        return status;
    }

    public void setStatus(OrdersStatus status) {
        this.status = status;
    }
}
