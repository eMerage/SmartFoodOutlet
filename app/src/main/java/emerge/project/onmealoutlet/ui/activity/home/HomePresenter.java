package emerge.project.onmealoutlet.ui.activity.home;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.SliderMenuItems;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrderMenus;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface HomePresenter {

    void setAllNavagationItem(ArrayList<SliderMenuItems> navigationDrawerItems);
    void getDeliveryTimeSlots(String status);
    void getDeliveryRiders(String status);



    void getOrdersFullDetails(ArrayList<OrderMenus> orderMenus);



    void updateOrderStatus(int orderId, String statusCode,String dispatchType);




    void printOrders(int orderId, int printType);



    void getOrders(String statusCode, int timeSlotId, int riderId, String dispatcType);



    void getIncome();



    void logOut();



    void getOutletName();



}
