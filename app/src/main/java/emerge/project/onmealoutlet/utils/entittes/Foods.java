package emerge.project.onmealoutlet.utils.entittes;

import android.os.Parcel;
import android.os.Parcelable;

public class Foods implements Parcelable {

    String cartID;
    String outletMenuName;
    int qty;
    boolean isBaseFood;
    String foodItemCategory;
    String foodItemTypeCode;


    public Foods(String cartID, String outletMenuName, int qty) {
        this.cartID = cartID;
        this.outletMenuName = outletMenuName;
        this.qty = qty;
    }

    public Foods(String cartID, String outletMenuName, int qty, boolean isBaseFood, String foodItemCategory,String foodItemTypeCode) {
        this.cartID = cartID;
        this.outletMenuName = outletMenuName;
        this.qty = qty;
        this.isBaseFood = isBaseFood;
        this.foodItemCategory = foodItemCategory;
        this.foodItemTypeCode =foodItemTypeCode;
    }


    protected Foods(Parcel in) {
        cartID = in.readString();
        outletMenuName = in.readString();
        qty = in.readInt();
        isBaseFood = in.readByte() != 0;
        foodItemCategory = in.readString();
        foodItemTypeCode = in.readString();
    }

    public static final Creator<Foods> CREATOR = new Creator<Foods>() {
        @Override
        public Foods createFromParcel(Parcel in) {
            return new Foods(in);
        }

        @Override
        public Foods[] newArray(int size) {
            return new Foods[size];
        }
    };

    public String getCartID() {
        return cartID;
    }

    public void setCartID(String cartID) {
        this.cartID = cartID;
    }

    public String getOutletMenuName() {
        return outletMenuName;
    }

    public void setOutletMenuName(String outletMenuName) {
        this.outletMenuName = outletMenuName;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public boolean isBaseFood() {
        return isBaseFood;
    }

    public void setBaseFood(boolean baseFood) {
        isBaseFood = baseFood;
    }

    public String getFoodItemCategory() {
        return foodItemCategory;
    }

    public String getFoodItemTypeCode() {
        return foodItemTypeCode;
    }

    public void setFoodItemTypeCode(String foodItemTypeCode) {
        this.foodItemTypeCode = foodItemTypeCode;
    }

    public void setFoodItemCategory(String foodItemCategory) {
        this.foodItemCategory = foodItemCategory;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cartID);
        dest.writeString(outletMenuName);
        dest.writeInt(qty);
        dest.writeByte((byte) (isBaseFood ? 1 : 0));
        dest.writeString(foodItemCategory);
        dest.writeString(foodItemTypeCode);
    }
}
