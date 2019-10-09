package emerge.project.onmealoutlet.ui.activity.orderHistory;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface OrderHistoryView {


        void getOrderHistory(ArrayList<Orders> OrderItems);
        void getOrderHistoryFail(String msg);




                void getOrderHistorySalse(OutletSales outletSales);
                void getOrderHistoryFailSalse(String msg);






}
