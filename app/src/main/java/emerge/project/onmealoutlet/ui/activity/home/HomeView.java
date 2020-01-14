package emerge.project.onmealoutlet.ui.activity.home;


import android.os.Bundle;

import java.util.ArrayList;


import emerge.project.onmealoutlet.utils.entittes.DeliveryRiders;
import emerge.project.onmealoutlet.utils.entittes.Menus;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.TimeSlots;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrderMenus;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrdersData;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface HomeView {

    void onFinishedNavigationItems();
    void showNoTimeSlotsAvailable();
    void showTimeSlots(ArrayList<TimeSlots> timeSlots);


    void showNoRidersAvailable();
    void showRidersList(ArrayList<DeliveryRiders> deliveryRiders);




    void ordersFullDetails(ArrayList<OrderMenus> ordermenus);



    void  updateOrderStatusSuccessful(int orderCurrentStatus,int orderId,String dispatchType);
    void updateOrderStatusFail(int orderId, String statusCode,String msg,String dispatchType);
    void updateOrderStatusStart();





    void printOrdersStart();
    void printOrdersSuccessful(Bundle bundleToView);
    void printOrdersFail(String msg);
    void printOrdersTimeOut(int orderId);






    void ordersList(OrdersData ordersArrayList);
    void ordersTimeOut();




    void income(String total, String qty);
    void noincome();
    void incomeTimeOut();



    void logoutSuccess();


    void outlatname(String name);


}
