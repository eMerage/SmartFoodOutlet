package emerge.project.onmealoutlet.ui.activity.orderHistory;


import java.util.ArrayList;
import java.util.List;

import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.servies.api.ApiClient;
import emerge.project.onmealoutlet.servies.api.ApiInterface;
import emerge.project.onmealoutlet.ui.activity.menuhistory.MenuHistoryInteractor;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


/**
 * Created by Himanshu on 4/5/2017.
 */

public class OrderHistoryInteractorImpil implements OrderHistoryInteractor {

    Realm realm = Realm.getDefaultInstance();
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    Outlet outlet = realm.where(Outlet.class).findFirst();

    ArrayList<Orders> ordersArrayList = new ArrayList<Orders>();

    OutletSales outletSales = new OutletSales();

    @Override
    public void getOrderHistory(String sDate, String eDate, String dispatcType, final OnGetOrderHistoryFinishedListener onGetOrderHistoryFinishedListener) {

        if(eDate.equals("")){
            eDate = sDate;
        }else {

        }

        try {
            apiService.getOrdersForOutletBetweenDates(outlet.getOutletId(), "ODCP", sDate, eDate,"")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<ArrayList<Orders>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ArrayList<Orders> ordersList) {
                            ordersArrayList = ordersList;
                        }

                        @Override
                        public void onError(Throwable e) {
                            onGetOrderHistoryFinishedListener.getOrderHistoryFail("Something went wrong, Please try again");

                        }

                        @Override
                        public void onComplete() {
                            onGetOrderHistoryFinishedListener.getOrderHistory(ordersArrayList);
                            System.out.println("ordersArrayList : "+ordersArrayList.size());

                        }
                    });
        } catch (Exception ex) {
            onGetOrderHistoryFinishedListener.getOrderHistoryFail("Something went wrong, Please try again");
        }
    }

    @Override
    public void getOrderHistorySalse(String sDate, String eDate, final OnGetOrderHistorySalseFinishedListener onGetOrderHistorySalseFinishedListener) {
        if(eDate.equals("")){
            eDate = sDate;
        }else {

        }

        try {
            apiService.GetTotalOutletSale(outlet.getOutletId(),sDate, eDate )
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<OutletSales>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(OutletSales outletsales) {
                            outletSales = outletsales;
                        }

                        @Override
                        public void onError(Throwable e) {
                            onGetOrderHistorySalseFinishedListener.getOrderHistoryFailSalse("Something went wrong, Please try again");

                        }

                        @Override
                        public void onComplete() {
                            onGetOrderHistorySalseFinishedListener.getOrderHistorySalse(outletSales);


                        }
                    });
        } catch (Exception ex) {
            onGetOrderHistorySalseFinishedListener.getOrderHistoryFailSalse("Something went wrong, Please try again");
        }
    }
}
