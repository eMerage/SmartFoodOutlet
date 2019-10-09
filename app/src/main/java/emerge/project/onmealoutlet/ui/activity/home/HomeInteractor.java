package emerge.project.onmealoutlet.ui.activity.home;


import android.os.Bundle;

import java.util.ArrayList;


import emerge.project.onmealoutlet.utils.entittes.DeliveryRiders;
import emerge.project.onmealoutlet.utils.entittes.Menus;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.SliderMenuItems;
import emerge.project.onmealoutlet.utils.entittes.TimeSlots;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface HomeInteractor {

    interface OnHomeNavigationItemFinishedListener {
        void onsetAllNavigationItems();
    }
    void setAllNavigationItem(ArrayList<SliderMenuItems> navigationDrawerItems, OnHomeNavigationItemFinishedListener onHomeNavigationItemFinishedListener);


    interface OngetDeliveryTimeSlotsFinishedListener {
        void noTimeSlotsAvailable();

        void timeSlots(ArrayList<TimeSlots> timeSlots);
    }

    void getDeliveryTimeSlots(String status, OngetDeliveryTimeSlotsFinishedListener ongetDeliveryTimeSlotsFinishedListener);


    interface OngetDeliveryRidersFinishedListener {
        void noRidersAvailable();

        void ridersList(ArrayList<DeliveryRiders> deliveryRiders);
    }

    void getDeliveryRiders(String status, OngetDeliveryRidersFinishedListener ongetDeliveryRidersFinishedListener);


    interface OnLogOutFinishedListener {
        void logoutSuccess();

    }

    void logOut(OnLogOutFinishedListener onLogOutFinishedListener);



    interface OnGetOrdersFullDetailsFinishedListener {

        void ordersFullDetailsFeedStart();

        void noOrdersFullDetailsAvailable();

        void ordersFullDetailsTimeOut(int orderId);

        void ordersFullDetails(ArrayList<Menus> menusArrayList);
    }

    void getOrdersFullDetails(int orderId, OnGetOrdersFullDetailsFinishedListener onGetOrdersFullDetailsFinishedListener);


    interface OnUpdateOrderStatusFinishedListener {
        void updateOrderStatusStart();
        void updateOrderStatusSuccessful(int orderCurrentStatus);
        void updateOrderStatusFail(int orderId, int userID, String statusCode,String msg);
    }
    void updateOrderStatus(int orderId, int userID, String statusCode, OnUpdateOrderStatusFinishedListener onUpdateOrderStatusFinishedListener);




    interface OnPrintOrdersFinishedListener {
        void printOrdersStart();
        void printOrdersSuccessful(Bundle bundleToView);
        void printOrdersFail(String msg);
        void printOrdersTimeOut(int orderId);
    }
    void printOrders(int orderId, int printType, OnPrintOrdersFinishedListener onPrintOrdersFinishedListener);




    interface OnGetOrdersFinishedListener {
        void ordersList(ArrayList<Orders> ordersArrayList);
        void noOrdersList();
        void ordersTimeOut();
    }
    void getOrders(String statusCode, int timeSlotId, int riderId, String dispatcType, OnGetOrdersFinishedListener onGetOrdersFinishedListener);



    interface OnGetIncomeFinishedListener {
        void income(String total, String qty);
        void noincome();
        void incomeTimeOut();
    }
    void getIncome(OnGetIncomeFinishedListener onGetIncomeFinishedListener);



    interface OnGetOutletNameFinishedListener {
        void outlatname(String name);
    }
    void getOutletName(OnGetOutletNameFinishedListener onGetOutletNameFinishedListener);




}
