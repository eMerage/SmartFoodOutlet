package emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory;



import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.MenuItems;

public class OrderHistoryStatus {


    @SerializedName("totalQty")
    int totalQty;


    @SerializedName("totalValue")
    Double totalValue;

    @SerializedName("totalValueCash")
    Double totalValueCash;


    @SerializedName("totValueCard")
    Double totValueCard;


    @SerializedName("order")
    ArrayList<OrderHistoryOrders> OrderHistoryOrders = new ArrayList<OrderHistoryOrders>();



    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
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

    public Double getTotValueCard() {
        return totValueCard;
    }

    public void setTotValueCard(Double totValueCard) {
        this.totValueCard = totValueCard;
    }

    public ArrayList<emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryOrders> getOrderHistoryOrders() {
        return OrderHistoryOrders;
    }

    public void setOrderHistoryOrders(ArrayList<emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryOrders> orderHistoryOrders) {
        OrderHistoryOrders = orderHistoryOrders;
    }
}
