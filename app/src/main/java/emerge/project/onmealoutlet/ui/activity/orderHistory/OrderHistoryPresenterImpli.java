package emerge.project.onmealoutlet.ui.activity.orderHistory;


import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryData;

/**
 * Created by Himanshu on 4/4/2017.
 */

public class OrderHistoryPresenterImpli implements OrderHistoryPresenter,
        OrderHistoryInteractor.OnGetOrderHistoryFinishedListener{


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
    public void getOrderHistory(OrderHistoryData OrderItems) {
        orderHistoryView.getOrderHistory(OrderItems);
    }

    @Override
    public void getOrderHistoryFail(String msg) {
        orderHistoryView.getOrderHistoryFail(msg);
    }




}
