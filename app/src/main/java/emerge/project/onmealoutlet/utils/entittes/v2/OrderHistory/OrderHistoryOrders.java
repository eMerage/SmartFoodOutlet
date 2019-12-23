package emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderHistoryOrders {


    @SerializedName("orderID")
    int orderID;

    @SerializedName("orderDate")
    String orderDate;


    @SerializedName("paymentTypeCode")
    String paymentTypeCode;


    @SerializedName("dispatchType")
    String dispatchType;


    @SerializedName("orderTotalWithoutDeliveryCost")
    Double orderTotalWithoutDeliveryCost;

    @SerializedName("orderMenus")
    ArrayList<OrderHistoryOrdersMenus> orderMenus = new ArrayList<OrderHistoryOrdersMenus>();


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    public Double getOrderTotalWithoutDeliveryCost() {
        return orderTotalWithoutDeliveryCost;
    }

    public void setOrderTotalWithoutDeliveryCost(Double orderTotalWithoutDeliveryCost) {
        this.orderTotalWithoutDeliveryCost = orderTotalWithoutDeliveryCost;
    }

    public ArrayList<OrderHistoryOrdersMenus> getOrderMenus() {
        return orderMenus;
    }

    public void setOrderMenus(ArrayList<OrderHistoryOrdersMenus> orderMenus) {
        this.orderMenus = orderMenus;
    }
}
