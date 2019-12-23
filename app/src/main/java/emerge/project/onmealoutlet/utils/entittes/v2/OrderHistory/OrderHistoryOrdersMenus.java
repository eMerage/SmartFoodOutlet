package emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory;

import com.google.gson.annotations.SerializedName;

public class OrderHistoryOrdersMenus {


    @SerializedName("name")
    String name;

    @SerializedName("menuQty")
    int menuQty;

    @SerializedName("menuPrice")
    Double menuPrice;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
