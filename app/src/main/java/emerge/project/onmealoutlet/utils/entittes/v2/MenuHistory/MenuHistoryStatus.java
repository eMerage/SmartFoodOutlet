package emerge.project.onmealoutlet.utils.entittes.v2.MenuHistory;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MenuHistoryStatus {


    @SerializedName("totalQty")
    int totalQty;


    @SerializedName("totalValue")
    Double totalValue;

    @SerializedName("totalValueCash")
    Double totalValueCash;


    @SerializedName("totalValueCard")
    Double totValueCard;


    @SerializedName("orderMenus")
    ArrayList<MenuHistoryOrdersMenus> OrderHistoryOrders = new ArrayList<MenuHistoryOrdersMenus>();


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

    public ArrayList<MenuHistoryOrdersMenus> getOrderHistoryOrders() {
        return OrderHistoryOrders;
    }

    public void setOrderHistoryOrders(ArrayList<MenuHistoryOrdersMenus> orderHistoryOrders) {
        OrderHistoryOrders = orderHistoryOrders;
    }
}
