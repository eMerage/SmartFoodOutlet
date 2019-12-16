package emerge.project.onmealoutlet.ui.activity.orderHistory;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.OrderHistoryEntitte;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;

/**
 * Created by Himanshu on 4/4/2017.
 */

public class OrderHistoryPresenterImpli implements OrderHistoryPresenter,
        OrderHistoryInteractor.OnGetOrderHistoryFinishedListener,
        OrderHistoryInteractor.OnGetOrderHistorySalseFinishedListener{


    private OrderHistoryView orderHistoryView;
    OrderHistoryInteractor orderHistoryInteractor;





    public OrderHistoryPresenterImpli(OrderHistoryView sview) {
        this.orderHistoryView = sview;
        this.orderHistoryInteractor = new OrderHistoryInteractorImpil();

    }



    @Override
    public void getOrderHistory(String sDate,String eDate,String dispatcType) {
        orderHistoryInteractor.getOrderHistory(sDate,eDate,dispatcType,this);
    }



    @Override
    public void getOrderHistory(ArrayList<OrderHistoryEntitte> OrderItems) {
        orderHistoryView.getOrderHistory(OrderItems);
    }

    @Override
    public void getOrderHistoryFail(String msg) {
        orderHistoryView.getOrderHistoryFail(msg);
    }




    @Override
    public void getOrderHistorySalse(String sDate, String eDate) {
        orderHistoryInteractor.getOrderHistorySalse(sDate,eDate,this);
    }



    @Override
    public void getOrderHistorySalse(OutletSales outletSales) {
        orderHistoryView.getOrderHistorySalse(outletSales);
    }

    @Override
    public void getOrderHistoryFailSalse(String msg) {
        orderHistoryView.getOrderHistoryFailSalse(msg);
    }
}
