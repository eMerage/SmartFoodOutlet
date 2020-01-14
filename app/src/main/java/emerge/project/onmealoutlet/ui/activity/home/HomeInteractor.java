package emerge.project.onmealoutlet.ui.activity.home;


import android.os.Bundle;

import java.util.ArrayList;


import emerge.project.onmealoutlet.utils.entittes.DeliveryRiders;
import emerge.project.onmealoutlet.utils.entittes.Menus;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.SliderMenuItems;
import emerge.project.onmealoutlet.utils.entittes.TimeSlots;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrderMenus;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrdersData;

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
        void ordersFullDetails(ArrayList<OrderMenus> menusArrayList);
    }

    void getOrdersFullDetails(ArrayList<OrderMenus> menusArrayList, OnGetOrdersFullDetailsFinishedListener onGetOrdersFullDetailsFinishedListener);



    interface OnUpdateOrderStatusFinishedListener {
        void updateOrderStatusStart();
        void updateOrderStatusSuccessful(int orderCurrentStatus,int orderId,String dispatchType );
        void updateOrderStatusFail(int orderId, String statusCode,String msg,String dispatchType);
    }
    void updateOrderStatus(int orderId, String statusCode,String dispatchType ,OnUpdateOrderStatusFinishedListener onUpdateOrderStatusFinishedListener);




    interface OnPrintOrdersFinishedListener {
        void printOrdersStart();
        void printOrdersSuccessful(Bundle bundleToView);
        void printOrdersFail(String msg);
        void printOrdersTimeOut(int orderId);
    }
    void printOrders(int orderId, int printType, OnPrintOrdersFinishedListener onPrintOrdersFinishedListener);




    interface OnGetOrdersFinishedListener {
        void ordersList(OrdersData ordersArrayList);
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
