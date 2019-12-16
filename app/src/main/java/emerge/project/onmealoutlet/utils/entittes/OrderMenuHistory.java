package emerge.project.onmealoutlet.utils.entittes;

import com.google.gson.annotations.SerializedName;

public class OrderMenuHistory {

    @SerializedName("name")
    String name;

    @SerializedName("menuQty")
    Double menuQty;

    @SerializedName("menuPrice")
    Double menuPrice;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMenuQty() {
        return menuQty;
    }

    public void setMenuQty(Double menuQty) {
        this.menuQty = menuQty;
    }

    public Double getMenuPrice() {
        return menuPrice;
    }

    public void setMenuPrice(Double menuPrice) {
        this.menuPrice = menuPrice;
    }
}
