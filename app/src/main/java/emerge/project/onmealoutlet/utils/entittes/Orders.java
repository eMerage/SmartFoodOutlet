package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Orders implements Serializable {

    @SerializedName("orderID")
    int orderID;

    @SerializedName("orderDate")
    String orderDate;


    @SerializedName("userID")
    int userID;


    @SerializedName("orderTotal")
    Double orderTotal;

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


    @SerializedName("orderMenus")
    ArrayList<MenuItems> menuItems = new ArrayList<>();


    boolean isCuntDownExp = false;



    public Orders() {
    }



    public Orders(int orderID, String orderDate, int userID, Double orderTotal, int orderQty, String dispatchType, String pickUpTime,
                  Rider rider, DeliveryTime deliveryTime,String promocode,String promotitle,String ordernote,String payment) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.userID = userID;
        this.orderTotal = orderTotal;
        this.orderQty = orderQty;
        this.dispatchType = dispatchType;
        this.pickUpTime =pickUpTime;
        this.rider = rider;
        this.deliveryTime = deliveryTime;
        this.promoCode = promocode;
        this.promoTitle = promotitle;
        this.orderNote = ordernote;
        this.paymentType = payment;
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
}
