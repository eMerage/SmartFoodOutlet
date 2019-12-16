package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderHistoryEntitte {

    @SerializedName("orderID")
    int orderID;

    @SerializedName("orderDate")
    String orderDate;

    @SerializedName("paymentTypeCode")
    String paymentTypeCode;

    @SerializedName("dispatchType")
    String dispatchType;

    @SerializedName("orderMenus")
    ArrayList<OrderMenuHistory> orderMenuHistory =  new ArrayList<>();


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

    public ArrayList<OrderMenuHistory> getOrderMenuHistory() {
        return orderMenuHistory;
    }

    public void setOrderMenuHistory(ArrayList<OrderMenuHistory> orderMenuHistory) {
        this.orderMenuHistory = orderMenuHistory;
    }
}
