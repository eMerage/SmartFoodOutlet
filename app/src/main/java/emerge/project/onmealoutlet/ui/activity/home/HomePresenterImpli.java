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
    public void getOrdersFullDetails(int orderId) {
        homeInteractor.getOrdersFullDetails(orderId, this);
    }


    @Override
    public void ordersFullDetailsFeedStart() {
        homeView.ordersFullDetailsFeedStart();
    }

    @Override
    public void noOrdersFullDetailsAvailable() {
        homeView.noOrdersFullDetailsAvailable();
    }

    @Override
    public void ordersFullDetailsTimeOut(int orderId) {
        homeView.ordersFullDetailsTimeOut(orderId);
    }

    @Override
    public void ordersFullDetails(ArrayList<Menus> menusArrayList) {
        homeView.ordersFullDetails(menusArrayList);
    }


    @Override
    public void updateOrderStatus(int orderId, int userID, String statusCode) {
        homeInteractor.updateOrderStatus(orderId, userID, statusCode, this);
    }



    @Override
    public void updateOrderStatusStart() {
        homeView.updateOrderStatusStart();
    }

    @Override
    public void updateOrderStatusSuccessful(int orderCurrentStatus) {
        homeView.updateOrderStatusSuccessful(orderCurrentStatus);
    }

    @Override
    public void updateOrderStatusFail(int orderId, int userID, String statusCode,String msg) {
        homeView.updateOrderStatusFail( orderId,  userID,  statusCode, msg);
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
    public void ordersList(ArrayList<Orders> ordersArrayList) {
        homeView.ordersList(ordersArrayList);
    }

    @Override
    public void noOrdersList() {
        homeView.noOrdersList();
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
}
