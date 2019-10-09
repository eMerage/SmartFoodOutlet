package emerge.project.onmealoutlet.ui.activity.home;


import android.os.Bundle;

import java.util.ArrayList;


import emerge.project.onmealoutlet.utils.entittes.DeliveryRiders;
import emerge.project.onmealoutlet.utils.entittes.Menus;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.TimeSlots;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface HomeView {

    void onFinishedNavigationItems();
    void showNoTimeSlotsAvailable();
    void showTimeSlots(ArrayList<TimeSlots> timeSlots);


    void showNoRidersAvailable();
    void showRidersList(ArrayList<DeliveryRiders> deliveryRiders);




    void ordersFullDetailsFeedStart();
    void noOrdersFullDetailsAvailable();
    void ordersFullDetailsTimeOut(int orderId);
    void ordersFullDetails(ArrayList<Menus> menusArrayList);



    void  updateOrderStatusSuccessful(int orderCurrentStatus);
    void updateOrderStatusFail(int orderId, int userID, String statusCode,String msg);
    void updateOrderStatusStart();





    void printOrdersStart();
    void printOrdersSuccessful(Bundle bundleToView);
    void printOrdersFail(String msg);
    void printOrdersTimeOut(int orderId);






    void ordersList(ArrayList<Orders> ordersArrayList);
    void noOrdersList();
    void ordersTimeOut();




    void income(String total, String qty);
    void noincome();
    void incomeTimeOut();



    void logoutSuccess();


    void outlatname(String name);


}
