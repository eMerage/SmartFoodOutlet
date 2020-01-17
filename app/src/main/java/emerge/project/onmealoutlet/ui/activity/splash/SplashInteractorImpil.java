package emerge.project.onmealoutlet.ui.activity.splash;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.JsonObject;


import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.servies.api.ApiClient;
import emerge.project.onmealoutlet.servies.api.ApiInterface;
import emerge.project.onmealoutlet.utils.entittes.User;
import emerge.project.onmealoutlet.utils.entittes.v2.ErrorObject;
import emerge.project.onmealoutlet.utils.entittes.v2.UpdateToken;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Himanshu on 4/5/2017.
 */

public class SplashInteractorImpil implements SplashInteractor {

    Realm realm;

    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



    String pushToken ;
    UpdateToken updateToken =  new UpdateToken();

    @Override
    public void updatePushTokenAndAppVersion(Context con, final OnUpdatePushTokenAndAppVersionFinishedListener onUpdatePushTokenAndAppVersionFinishedListener) {
        realm = Realm.getDefaultInstance();


        int versionCode = 1;

        try {
            PackageInfo pInfo = con.getPackageManager().getPackageInfo(con.getPackageName(), 0);
            versionCode = pInfo.versionCode;

        } catch(PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }

        int userID = 0;
        try {
            Outlet outlet = realm.where(Outlet.class).findFirst();
            userID = outlet.getUserID();
        }catch (Exception ex){

        }


        final int finalVersionCode = versionCode;
        final int finalUserID = userID;
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }else {
                            pushToken= task.getResult().getToken();
                        }
                        updateTokenToServer(finalUserID, finalVersionCode,pushToken,onUpdatePushTokenAndAppVersionFinishedListener);

                    }
                });

    }


    private void updateTokenToServer(int userID,int versionCode,String token, final OnUpdatePushTokenAndAppVersionFinishedListener onUpdatePushTokenAndAppVersionFinishedListener){


        final ErrorObject errorObject =  new ErrorObject();
        errorObject.setErrCode("CE");
        errorObject.setErrDescription("Communication error, Please try again");



        try {
            apiService.saveMealTimeUserPushToken(userID,token,versionCode,"O","A")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<UpdateToken>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(UpdateToken respond) {
                            updateToken = respond;

                            System.out.println("qqqqqqqqqqqqq updateToken): "+updateToken.getError().getErrDescription());

                        }

                        @Override
                        public void onError(Throwable e) {
                            updateToken.setError(errorObject);
                            onUpdatePushTokenAndAppVersionFinishedListener.updateStatus(false,updateToken);

                        }

                        @Override
                        public void onComplete() {

                            onUpdatePushTokenAndAppVersionFinishedListener.updateStatus(updateToken.isStatus(),updateToken);

                        }
                    });

        } catch (Exception ex) {
             updateToken.setError(errorObject);
            onUpdatePushTokenAndAppVersionFinishedListener.updateStatus(false,updateToken);
        }


    }
}


