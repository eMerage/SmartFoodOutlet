package emerge.project.onmealoutlet.utils.entittes.v2.Orders;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.DeliveryTime;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import emerge.project.onmealoutlet.utils.entittes.Rider;

public class OrdersList {

    @SerializedName("orderID")
    int orderID;


    @SerializedName("outlet")
    String outlet;

    @SerializedName("pickUpTime")
    String pickUpTime;


    @SerializedName("getDeliveryTime")
    DeliveryTime deliveryTime =  new DeliveryTime();


    @SerializedName("paymentTypeCode")
    String paymentType;

    @SerializedName("rideName")
    String rideName;

    @SerializedName("orderTotal")
    Double orderTotal;


    @SerializedName("orderTotalWithoutDeliveryCost")
    Double orderTotalWithoutDeliveryCost;

    @SerializedName("subTotal")
    Double subTotal;


    @SerializedName("deliveryCost")
    Double deliveryCost;

    @SerializedName("orderQty")
    int orderQty;

    @SerializedName("dispatchType")
    String dispatchType;


    @SerializedName("promoCode")
    String promoCode;

    @SerializedName("promoTitle")
    String promoTitle;

    @SerializedName("orderNote")
    String orderNote;


    @SerializedName("promoDiscountValue")
    String promoDiscountValue;



    @SerializedName("orderMenus")
    ArrayList<OrderMenus> menuItems = new ArrayList<>();


    boolean isCuntDownExp = false;

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public String getOutlet() {
        return outlet;
    }

    public void setOutlet(String outlet) {
        this.outlet = outlet;
    }

    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
    }

    public DeliveryTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(DeliveryTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getRideName() {
        return rideName;
    }

    public void setRideName(String rideName) {
        this.rideName = rideName;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public Double getOrderTotalWithoutDeliveryCost() {
        return orderTotalWithoutDeliveryCost;
    }

    public void setOrderTotalWithoutDeliveryCost(Double orderTotalWithoutDeliveryCost) {
        this.orderTotalWithoutDeliveryCost = orderTotalWithoutDeliveryCost;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public Double getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(Double deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getPromoTitle() {
        return promoTitle;
    }

    public void setPromoTitle(String promoTitle) {
        this.promoTitle = promoTitle;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }

    public ArrayList<OrderMenus> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<OrderMenus> menuItems) {
        this.menuItems = menuItems;
    }

    public boolean isCuntDownExp() {
        return isCuntDownExp;
    }

    public void setCuntDownExp(boolean cuntDownExp) {
        isCuntDownExp = cuntDownExp;
    }

    public String getPromoDiscountValue() {
        return promoDiscountValue;
    }

    public void setPromoDiscountValue(String promoDiscountValue) {
        this.promoDiscountValue = promoDiscountValue;
    }
}
