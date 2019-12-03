package emerge.project.onmealoutlet.ui.activity.home;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.SliderMenuItems;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface HomePresenter {

    void setAllNavagationItem(ArrayList<SliderMenuItems> navigationDrawerItems);
    void getDeliveryTimeSlots(String status);
    void getDeliveryRiders(String status);



    void getOrdersFullDetails(int orderId);



    void updateOrderStatus(int orderId, int userID, String statusCode,String dispatchType);




    void printOrders(int orderId, int printType);



    void getOrders(String statusCode, int timeSlotId, int riderId, String dispatcType);



    void getIncome();



    void logOut();



    void getOutletName();



}
