package emerge.project.onmealoutlet.ui.activity.orderHistory;


import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryData;


/**
 * Created by Himanshu on 4/4/2017.
 */

public interface OrderHistoryInteractor {

    interface OnGetOrderHistoryFinishedListener {
        void getOrderHistory(OrderHistoryData OrderItems);
        void getOrderHistoryFail(String msg);
    }
    void  getOrderHistory(String sDate,String eDate, String dispatcType,OnGetOrderHistoryFinishedListener onGetOrderHistoryFinishedListener);



}
