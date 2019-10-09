package emerge.project.onmealoutlet.ui.activity.settings;


import java.util.ArrayList;
import java.util.List;

import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.servies.api.ApiClient;
import emerge.project.onmealoutlet.servies.api.ApiInterface;
import emerge.project.onmealoutlet.ui.activity.menus.MenusInteractor;
import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


/**
 * Created by Himanshu on 4/5/2017.
 */

public class SettingsInteractorImpil implements SettingsInteractor {

    Realm realm = Realm.getDefaultInstance();
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    Outlet outlet = realm.where(Outlet.class).findFirst();


    Outlet outletObject;
    int outletTimeUpdate=0;

    @Override
    public void getOutletDetails(final OnGetOutletDetailsFinishedListener onGetOutletDetailsFinishedListener) {
        try {
            apiService.getOutletDetails(outlet.getOutletId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Outlet>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                        @Override
                        public void onNext(Outlet items) {
                            outletObject = items;
                        }

                        @Override
                        public void onError(Throwable e) {
                            onGetOutletDetailsFinishedListener.getOutletDetailsFail(String.valueOf(R.string.server_error_msg));
                        }

                        @Override
                        public void onComplete() {
                            onGetOutletDetailsFinishedListener.getOutletDetails(outletObject);
                        }
                    });

        } catch (Exception ex) {
           onGetOutletDetailsFinishedListener.getOutletDetailsFail(String.valueOf(R.string.server_error_msg));
        }

    }

    @Override
    public void updateTimeInterval(final int time, final OnUpdateTimeIntervalFinishedListener onUpdateTimeIntervalFinishedListener) {
        if(time == 0){
            onUpdateTimeIntervalFinishedListener.updateTimeIntervalFail("Please add time");
        }else {
            try {
                apiService.updateOutletPickupInterval(outlet.getOutletId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Integer>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }
                            @Override
                            public void onNext(Integer items) {
                                outletTimeUpdate = items;
                            }

                            @Override
                            public void onError(Throwable e) {
                                onUpdateTimeIntervalFinishedListener.updateTimeIntervalFail(String.valueOf(R.string.server_error_msg));
                            }

                            @Override
                            public void onComplete() {
                                if(outletTimeUpdate==0){

                                }else {
                                    onUpdateTimeIntervalFinishedListener.updateTimeIntervalSuccess(time);
                                }
                            }
                        });

            } catch (Exception ex) {
                onUpdateTimeIntervalFinishedListener.updateTimeIntervalFail(String.valueOf(R.string.server_error_msg));
            }

        }



    }
}
