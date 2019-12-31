package emerge.project.onmealoutlet.ui.activity.menuhistory;


import java.util.ArrayList;

import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.servies.api.ApiClient;
import emerge.project.onmealoutlet.servies.api.ApiInterface;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;
import emerge.project.onmealoutlet.utils.entittes.v2.MenuHistory.MenuHistoryData;
import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryData;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


/**
 * Created by Himanshu on 4/5/2017.
 */

public class MenuHistoryInteractorImpil implements MenuHistoryInteractor {

    Realm realm = Realm.getDefaultInstance();
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    Outlet outlet = realm.where(Outlet.class).findFirst();


    MenuHistoryData menuHistoryData =  new MenuHistoryData();
    @Override
    public void getMenuHistory(String sDate, String eDate, final OnGetMenuHistoryFinishedListener onGetMenuHistoryFinishedListener) {

        if(eDate.equals("")){
            eDate = sDate;
        }else {

        }

        if ((sDate.equals("")) || (eDate.equals("")) ){
            onGetMenuHistoryFinishedListener.getMenuHistoryFail("Selected date can not be empty");
        }else if((sDate == null)  || (eDate == null)){
            onGetMenuHistoryFinishedListener.getMenuHistoryFail("Selected date can not be empty");
        }else {
            try {
                apiService.getMenuSaleForOutlet(outlet.getOutletId(),sDate, eDate,"")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<MenuHistoryData>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(MenuHistoryData outletsales) {
                                menuHistoryData = outletsales;
                            }

                            @Override
                            public void onError(Throwable e) {
                                onGetMenuHistoryFinishedListener.getMenuHistoryFail("Something went wrong, Please try again "+e.toString());
                            }

                            @Override
                            public void onComplete() {
                                if(menuHistoryData.getStatus().getTotalQty() ==0){
                                    onGetMenuHistoryFinishedListener.getMenuHistoryFail("No History for selected Date,Please check the date");

                                }else {
                                    onGetMenuHistoryFinishedListener.getMenuHistory(menuHistoryData);
                                }

                            }
                        });
            } catch (Exception ex) {
                onGetMenuHistoryFinishedListener.getMenuHistoryFail("Something went wrong, Please try again ex"+ex.toString());
            }

        }

    }

}
