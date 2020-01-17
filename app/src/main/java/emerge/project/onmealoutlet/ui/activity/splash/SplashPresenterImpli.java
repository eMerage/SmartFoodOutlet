package emerge.project.onmealoutlet.ui.activity.splash;


import android.content.Context;

import emerge.project.onmealoutlet.utils.entittes.v2.UpdateToken;

/**
 * Created by Himanshu on 4/4/2017.
 */

public class SplashPresenterImpli implements SplashPresenter,SplashInteractor.OnUpdatePushTokenAndAppVersionFinishedListener {


    private SplashView splashView;
    SplashInteractor splashInteractor;


    public SplashPresenterImpli(SplashView splashView) {
        this.splashView = splashView;
        this.splashInteractor = new SplashInteractorImpil();

    }





    @Override
    public void updatePushTokenAndAppVersion(Context con) {
        splashInteractor.updatePushTokenAndAppVersion( con,this);
    }



    @Override
    public void updateStatus(Boolean status, UpdateToken updateToken) {
        splashView.updateStatus(status,updateToken);
    }


}
