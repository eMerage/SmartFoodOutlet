package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MenuItems implements Serializable {

    @SerializedName("menuTitleID")
    int menuId;

    @SerializedName("outletMenuTitleID")
    int foodId;

    @SerializedName("name")
    String foodName;

        @SerializedName("imageUrl")
    String foodCoverImage;

    @SerializedName("maxAvailableQty")
    int maxAvailableQty;


    @SerializedName("menuQty")
    int menuQty;

    @SerializedName("menuPrice")
    Double menuPrice;

    @SerializedName("menuSizeCode")
    String menuSizeCode;


    int totalQty;


    int totalValue;


    public MenuItems(int menuId, int foodId, String foodName, String foodCoverImage, int maxAvailableQty) {
        this.menuId = menuId;
        this.foodId = foodId;
        this.foodName = foodName;
        this.foodCoverImage = foodCoverImage;
        this.maxAvailableQty = maxAvailableQty;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodCoverImage() {
        return foodCoverImage;
    }

    public void setFoodCoverImage(String foodCoverImage) {
        this.foodCoverImage = foodCoverImage;
    }

    public int getMaxAvailableQty() {
        return maxAvailableQty;
    }

    public void setMaxAvailableQty(int maxAvailableQty) {
        this.maxAvailableQty = maxAvailableQty;
    }

    public int getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(int totalQty) {
        this.totalQty = totalQty;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }


    public int getMenuQty() {
        return menuQty;
    }

    public void setMenuQty(int menuQty) {
        this.menuQty = menuQty;
    }

    public Double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Double menuPrice) {
        this.menuPrice = menuPrice;
    }

    public String getMenuSizeCode() {
        return menuSizeCode;
    }

    public void setMenuSizeCode(String menuSizeCode) {
        this.menuSizeCode = menuSizeCode;
    }
}
