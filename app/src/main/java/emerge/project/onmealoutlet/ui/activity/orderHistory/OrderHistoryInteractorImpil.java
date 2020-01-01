package emerge.project.onmealoutlet.ui.activity.orderHistory;


import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.servies.api.ApiClient;
import emerge.project.onmealoutlet.servies.api.ApiInterface;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;
import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryData;
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

    OrderHistoryData ordersArrayList = new OrderHistoryData();


    @Override
    public void getOrderHistory(String sDate, String eDate, String dispatch, final OnGetOrderHistoryFinishedListener onGetOrderHistoryFinishedListener) {

        if(eDate.equals("")){
            eDate = sDate;
        }

        if((sDate.equals(""))  ||  (sDate == null)){
            onGetOrderHistoryFinishedListener.getOrderHistoryFail("Please select date aging");
        }else {
            try {
                apiService.getOrdersForOutletBetweenDates(outlet.getOutletId(), "ODCP", sDate, eDate,"")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<OrderHistoryData>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(OrderHistoryData ordersList) {
                                ordersArrayList = ordersList;
                            }

                            @Override
                            public void onError(Throwable e) {
                                onGetOrderHistoryFinishedListener.getOrderHistoryFail("Something went wrong, Please try again");

                            }

                            @Override
                            public void onComplete() {
                                if(ordersArrayList.getStatus().getTotalQty()==0){
                                    onGetOrderHistoryFinishedListener.getOrderHistoryFail("No History for selected Date,Please check the date");
                                }else {
                                    onGetOrderHistoryFinishedListener.getOrderHistory(ordersArrayList);
                                }

                            }
                        });
            } catch (Exception ex) {
                onGetOrderHistoryFinishedListener.getOrderHistoryFail("Something went wrong, Please try again");
            }

        }

    }
}
