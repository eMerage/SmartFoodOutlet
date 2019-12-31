package emerge.project.onmealoutlet.utils.entittes.v2.MenuHistory;

import com.google.gson.annotations.SerializedName;

public class MenuHistoryOrdersMenus {

    @SerializedName("name")
    String name;

    @SerializedName("maxOrderQty")
    int maxOrderQty;

    @SerializedName("menuPrice")
    Double menuPrice;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxOrderQty() {
        return maxOrderQty;
    }

    public void setMaxOrderQty(int maxOrderQty) {
        this.maxOrderQty = maxOrderQty;
    }

    public Double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Double menuPrice) {
        this.menuPrice = menuPrice;
    }
}
