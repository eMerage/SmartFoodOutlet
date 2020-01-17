package emerge.project.onmealoutlet.ui.activity.login;


import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.pddstudio.preferences.encrypted.EncryptedPreferences;

import java.util.List;

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
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Himanshu on 4/5/2017.
 */

public class LoginInteractorImpil implements LoginInteractor {

    Realm realm;

    EncryptedPreferences encryptedPreferences;
    private static final String PUSH_TOKEN = "pushToken";
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
    Outlet checkLoginValidationOutletObject;
    User updatePushTokanUserObject=null;


    String pushToken ;
    UpdateToken updateToken =  new UpdateToken();


    @Override
    public void checkLoginValidation(final Context context, final String userName, String password, final OnLoginValidationFinishedListener onLoginValidationFinishedListener) {

        if (userName.isEmpty() || userName.equals("") || userName == null) {
            onLoginValidationFinishedListener.userNameEmpty();
        } else if (password.isEmpty() || password.equals("") || password == null) {
            onLoginValidationFinishedListener.passwordEmpty();
        } else {
            apiService.userLoginValidation(userName, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Outlet>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Outlet outletsList) {
                            checkLoginValidationOutletObject = outletsList;
                        }

                        @Override
                        public void onError(Throwable e) {
                            onLoginValidationFinishedListener.loginValidationFail("Invalid login details,Please try again");
                        }

                        @Override
                        public void onComplete() {
                            if (checkLoginValidationOutletObject.getOutletId() == 0) {
                                onLoginValidationFinishedListener.loginValidationFail("Invalid login details,Please try again");
                            } else {
                                saveOutlet(context, userName, checkLoginValidationOutletObject, onLoginValidationFinishedListener);
                            }
                        }
                    });


        }


    }


    private void saveOutlet(final Context context, final String userName, final Outlet outlet, final OnLoginValidationFinishedListener onLoginValidationFinishedListener) {

        realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                final Long userList = bgRealm.where(Outlet.class).count();
                Outlet outletDb = bgRealm.createObject(Outlet.class, (userList.intValue() + 1));
                outletDb.setOutletId(outlet.getOutletId());
                outletDb.setOutletname(outlet.getOutletname());
                outletDb.setUserID(outlet.getUserID());
                updatePushTokan(context, userName, onLoginValidationFinishedListener);

            }
        });

    }


    private void updatePushTokan(Context context, String userName, final OnLoginValidationFinishedListener onLoginValidationFinishedListener) {
        encryptedPreferences = new EncryptedPreferences.Builder(context).withEncryptionPassword("122547895511").build();
        String userPushTokenId = encryptedPreferences.getString(PUSH_TOKEN, "");

        try {

            apiService.getUserByEmail(userName, userPushTokenId)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<User>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                        @Override
                        public void onNext(User userList) {
                            updatePushTokanUserObject = userList;

                        }
                        @Override
                        public void onError(Throwable e) {
                            onLoginValidationFinishedListener.loginError("");
                        }
                        @Override
                        public void onComplete() {
                            if (updatePushTokanUserObject == null) {
                                onLoginValidationFinishedListener.loginError("");
                            } else {
                                onLoginValidationFinishedListener.loginValidationSuccessful();
                            }
                        }
                    });

        } catch (Exception ex) {
            onLoginValidationFinishedListener.loginError("");
        }


    }
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
