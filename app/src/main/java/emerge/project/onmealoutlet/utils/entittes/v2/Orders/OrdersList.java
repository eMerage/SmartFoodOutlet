package emerge.project.onmealoutlet.utils.entittes.v2.Orders;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.DeliveryTime;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import emerge.project.onmealoutlet.utils.entittes.Rider;

public class OrdersList {

    @SerializedName("orderID")
    int orderID;

    @SerializedName("orderDate")
    String orderDate;


    @SerializedName("userID")
    int userID;


    @SerializedName("orderTotal")
    Double orderTotal;


    @SerializedName("orderTotalWithoutDeliveryCost")
    Double orderTotalWithoutDeliveryCost;

    @SerializedName("orderQty")
    int orderQty;

    @SerializedName("dispatchType")
    String dispatchType;

    @SerializedName("pickUpTime")
    String pickUpTime;

    @SerializedName("promoCode")
    String promoCode;

    @SerializedName("promoTitle")
    String promoTitle;

    @SerializedName("rider")
    Rider rider =  new Rider();

    @SerializedName("deliveryTime")
    DeliveryTime deliveryTime =  new DeliveryTime();


    @SerializedName("orderNote")
    String orderNote;

    @SerializedName("paymentTypeCode")
    String paymentType;

    @SerializedName("promoDiscountValue")
    Double promoDiscountValue;


    @SerializedName("orderMenus")
    ArrayList<MenuItems> menuItems = new ArrayList<>();


    boolean isCuntDownExp = false;



    public OrdersList() {
    }


    public Double getPromoDiscountValue() {
        return promoDiscountValue;
    }

    public void setPromoDiscountValue(Double promoDiscountValue) {
        this.promoDiscountValue = promoDiscountValue;
    }

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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
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


    public String getPickUpTime() {
        return pickUpTime;
    }

    public void setPickUpTime(String pickUpTime) {
        this.pickUpTime = pickUpTime;
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

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }


    public DeliveryTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(DeliveryTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getOrderNote() {
        return orderNote;
    }

    public void setOrderNote(String orderNote) {
        this.orderNote = orderNote;
    }


    public boolean isCuntDownExp() {
        return isCuntDownExp;
    }

    public void setCuntDownExp(boolean cuntDownExp) {
        isCuntDownExp = cuntDownExp;
    }


    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public ArrayList<MenuItems> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItems> menuItems) {
        this.menuItems = menuItems;
    }

    public Double getOrderTotalWithoutDeliveryCost() {
        return orderTotalWithoutDeliveryCost;
    }

    public void setOrderTotalWithoutDeliveryCost(Double orderTotalWithoutDeliveryCost) {
        this.orderTotalWithoutDeliveryCost = orderTotalWithoutDeliveryCost;
    }
}
