package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MenuHistoryEntittes implements Serializable {

    @SerializedName("outletMenuTitleID")
    int outletMenuTitleID;


    @SerializedName("menuCategoryID")
    int menuCategoryID;


    @SerializedName("menuCategory")
    String menuCategory;


    @SerializedName("menuTitleID")
    int menuTitleID;

    @SerializedName("menuTitle")
    String menuTitle;


    @SerializedName("menuPrice")
    Double menuPrice;

    @SerializedName("maxOrderQty")
    int maxOrderQty;


    @SerializedName("description")
    String description;


    @SerializedName("imageUrl")
    String imageUrl;


    public int getOutletMenuTitleID() {
        return outletMenuTitleID;
    }

    public void setOutletMenuTitleID(int outletMenuTitleID) {
        this.outletMenuTitleID = outletMenuTitleID;
    }

    public int getMenuCategoryID() {
        return menuCategoryID;
    }

    public void setMenuCategoryID(int menuCategoryID) {
        this.menuCategoryID = menuCategoryID;
    }

    public String getMenuCategory() {
        return menuCategory;
    }

    public void setMenuCategory(String menuCategory) {
        this.menuCategory = menuCategory;
    }

    public int getMenuTitleID() {
        return menuTitleID;
    }

    public void setMenuTitleID(int menuTitleID) {
        this.menuTitleID = menuTitleID;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public Double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Double menuPrice) {
        this.menuPrice = menuPrice;
    }

    public int getMaxOrderQty() {
        return maxOrderQty;
    }

    public void setMaxOrderQty(int maxOrderQty) {
        this.maxOrderQty = maxOrderQty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
