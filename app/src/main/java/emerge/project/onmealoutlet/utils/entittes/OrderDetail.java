package emerge.project.onmealoutlet.utils.entittes;

import android.os.Parcel;
import android.os.Parcelable;

public class OrderDetail implements Parcelable {

    int orderId;
    int qty;

    double totalprice;
    double subTotal;
    double dilivery;

    String riderName;
    int riderID;

    String dispatchType;
    String date;

    String paymentTypeCode;
    String time;



    public OrderDetail() {
    }


    protected OrderDetail(Parcel in) {
        orderId = in.readInt();
        qty = in.readInt();
        totalprice = in.readDouble();
        subTotal = in.readDouble();
        dilivery = in.readDouble();
        riderName = in.readString();
        riderID = in.readInt();
        dispatchType = in.readString();
        date = in.readString();
        paymentTypeCode= in.readString();
        time= in.readString();
    }

    public static final Creator<OrderDetail> CREATOR = new Creator<OrderDetail>() {
        @Override
        public OrderDetail createFromParcel(Parcel in) {
            return new OrderDetail(in);
        }

        @Override
        public OrderDetail[] newArray(int size) {
            return new OrderDetail[size];
        }
    };

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getDilivery() {
        return dilivery;
    }

    public void setDilivery(double dilivery) {
        this.dilivery = dilivery;
    }

    public String getRiderName() {
        return riderName;
    }

    public void setRiderName(String riderName) {
        this.riderName = riderName;
    }

    public int getRiderID() {
        return riderID;
    }

    public void setRiderID(int riderID) {
        this.riderID = riderID;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPaymentTypeCode() {
        return paymentTypeCode;
    }

    public void setPaymentTypeCode(String paymentTypeCode) {
        this.paymentTypeCode = paymentTypeCode;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(orderId);
        dest.writeInt(qty);
        dest.writeDouble(totalprice);
        dest.writeDouble(subTotal);
        dest.writeDouble(dilivery);
        dest.writeString(riderName);
        dest.writeInt(riderID);
        dest.writeString(dispatchType);
        dest.writeString(date);
        dest.writeString(paymentTypeCode);
        dest.writeString(time);
    }
}
