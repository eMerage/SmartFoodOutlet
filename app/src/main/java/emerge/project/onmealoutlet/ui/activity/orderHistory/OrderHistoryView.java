package emerge.project.onmealoutlet.ui.activity.orderHistory;


import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryData;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface OrderHistoryView {


        void getOrderHistory(OrderHistoryData OrderItems);
        void getOrderHistoryFail(String msg);





}
