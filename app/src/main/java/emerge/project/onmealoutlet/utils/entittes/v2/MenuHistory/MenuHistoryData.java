package emerge.project.onmealoutlet.utils.entittes.v2.MenuHistory;

import com.google.gson.annotations.SerializedName;

import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryStatus;

public class MenuHistoryData {

    @SerializedName("status")
    MenuHistoryStatus status =  new MenuHistoryStatus();

    public MenuHistoryStatus getStatus() {
        return status;
    }

    public void setStatus(MenuHistoryStatus status) {
        this.status = status;
    }
}
