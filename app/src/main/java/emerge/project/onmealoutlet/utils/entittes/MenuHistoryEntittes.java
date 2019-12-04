package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MenuHistoryEntittes implements Serializable {



    @SerializedName("name")
    String menuTitle;


    @SerializedName("menuPrice")
    Double menuPrice;

    @SerializedName("maxOrderQty")
    int maxOrderQty;



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


}
