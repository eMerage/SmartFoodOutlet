package emerge.project.onmealoutlet.ui.activity.orderHistory;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;


/**
 * Created by Himanshu on 4/4/2017.
 */

public interface OrderHistoryInteractor {

    interface OnGetOrderHistoryFinishedListener {
        void getOrderHistory(ArrayList<Orders> OrderItems);
        void getOrderHistoryFail(String msg);
    }
    void  getOrderHistory(String sDate,String eDate, String dispatcType,OnGetOrderHistoryFinishedListener onGetOrderHistoryFinishedListener);



    interface OnGetOrderHistorySalseFinishedListener {
        void getOrderHistorySalse(OutletSales outletSales);
        void getOrderHistoryFailSalse(String msg);
    }
    void  getOrderHistorySalse(String sDate,String eDate,OnGetOrderHistorySalseFinishedListener onGetOrderHistorySalseFinishedListener);





}
