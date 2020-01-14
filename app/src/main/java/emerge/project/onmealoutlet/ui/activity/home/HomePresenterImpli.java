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

public class HomePresenterImpli implements HomePresenter,
        HomeInteractor.OnHomeNavigationItemFinishedListener,
        HomeInteractor.OngetDeliveryTimeSlotsFinishedListener,
        HomeInteractor.OngetDeliveryRidersFinishedListener,
        HomeInteractor.OnLogOutFinishedListener,
        HomeInteractor.OnGetOrdersFullDetailsFinishedListener,
        HomeInteractor.OnUpdateOrderStatusFinishedListener,
        HomeInteractor.OnPrintOrdersFinishedListener,
        HomeInteractor.OnGetOrdersFinishedListener,
        HomeInteractor.OnGetIncomeFinishedListener,
HomeInteractor.OnGetOutletNameFinishedListener{


    private HomeView homeView;

    HomeInteractor homeInteractor;


    public HomePresenterImpli(HomeView homeView) {
        this.homeView = homeView;
        this.homeInteractor = new HomeInteractorImpil();

    }


    @Override
    public void onsetAllNavigationItems() {
        homeView.onFinishedNavigationItems();
    }

    @Override
    public void setAllNavagationItem(ArrayList<SliderMenuItems> sliderMenuItems) {
        homeInteractor.setAllNavigationItem(sliderMenuItems, this);
    }


    @Override
    public void getDeliveryTimeSlots(String status) {
        homeInteractor.getDeliveryTimeSlots(status,this);
    }

    @Override
    public void noTimeSlotsAvailable() {
        homeView.showNoTimeSlotsAvailable();
    }

    @Override
    public void timeSlots(ArrayList<TimeSlots> timeSlots) {
        homeView.showTimeSlots(timeSlots);
    }


    @Override
    public void getDeliveryRiders(String status) {
        homeInteractor.getDeliveryRiders( status,this);
    }


    @Override
    public void noRidersAvailable() {
        homeView.showNoRidersAvailable();
    }

    @Override
    public void ridersList(ArrayList<DeliveryRiders> deliveryRiders) {
        homeView.showRidersList(deliveryRiders);
    }


    @Override
    public void logOut() {
        homeInteractor.logOut(this);
    }




    @Override
    public void logoutSuccess() {
        homeView.logoutSuccess();
    }








    @Override
    public void updateOrderStatus(int orderId, String statusCode,String dispatchType) {
        homeInteractor.updateOrderStatus(orderId, statusCode, dispatchType,this);
    }



    @Override
    public void updateOrderStatusStart() {
        homeView.updateOrderStatusStart();
    }

    @Override
    public void updateOrderStatusSuccessful(int orderCurrentStatus,int orderId,String dispatchType) {
        homeView.updateOrderStatusSuccessful(orderCurrentStatus, orderId, dispatchType);
    }

    @Override
    public void updateOrderStatusFail(int orderId, String statusCode,String msg,String dispatchType) {
        homeView.updateOrderStatusFail( orderId,  statusCode, msg, dispatchType);
    }




    @Override
    public void printOrders(int orderId,int printType) {
        homeInteractor.printOrders(orderId,printType,this);
    }



    @Override
    public void printOrdersStart() {
        homeView.printOrdersStart();
    }

    @Override
    public void printOrdersSuccessful(Bundle bundleToView) {
        homeView.printOrdersSuccessful(bundleToView);
    }

    @Override
    public void printOrdersFail(String msg) {
        homeView.printOrdersFail(msg);
    }

    @Override
    public void printOrdersTimeOut(int orderId) {
        homeView.printOrdersTimeOut(orderId);
    }


    @Override
    public void getOrders(String statusCode, int timeSlotId, int riderId, String dispatcType) {
        homeInteractor.getOrders(statusCode,timeSlotId,riderId,dispatcType,this);
    }



    @Override
    public void ordersList(OrdersData ordersArrayList) {
        homeView.ordersList(ordersArrayList);
    }



    @Override
    public void ordersTimeOut() {
        homeView.ordersTimeOut();
    }







    @Override
    public void getIncome() {
        homeInteractor.getIncome(this);
    }


    @Override
    public void income(String total, String qty) {
        homeView.income(total,qty);
    }

    @Override
    public void noincome() {
        homeView.noincome();
    }

    @Override
    public void incomeTimeOut() {
        homeView.incomeTimeOut();
    }









    @Override
    public void getOutletName() {
        homeInteractor.getOutletName(this);
    }

    @Override
    public void outlatname(String name) {
        homeView.outlatname(name);
    }




    @Override
    public void getOrdersFullDetails(ArrayList<OrderMenus> orderMenus) {
        homeInteractor.getOrdersFullDetails(orderMenus, this);
    }


    @Override
    public void ordersFullDetails(ArrayList<OrderMenus> menusArrayList) {
        homeView.ordersFullDetails(menusArrayList);
    }
}
