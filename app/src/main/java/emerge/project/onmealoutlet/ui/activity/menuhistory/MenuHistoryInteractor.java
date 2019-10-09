package emerge.project.onmealoutlet.ui.activity.menuhistory;


import java.util.ArrayList;

import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.ui.activity.orderHistory.OrderHistoryInteractor;
import emerge.project.onmealoutlet.utils.entittes.MenuHistoryEntittes;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface MenuHistoryInteractor {

    interface OnGetMenuHistoryFinishedListener {
        void getMenuHistory(ArrayList<MenuHistoryEntittes> menuItems);
        void getMenuHistoryFail(String msg);
    }
    void  getMenuHistory(String sDate,String eDate,OnGetMenuHistoryFinishedListener onGetMenuHistoryFinishedListener);


    interface OnGetOrderHistorySalseFinishedListener {
        void getOrderHistorySalse(OutletSales outletSales);
        void getOrderHistoryFailSalse(String msg);
    }
    void  getOrderHistorySalse(String sDate, String eDate,OnGetOrderHistorySalseFinishedListener onGetOrderHistorySalseFinishedListener);




}
