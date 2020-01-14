package emerge.project.onmealoutlet.ui.activity.splash;


import android.content.Context;

import emerge.project.onmealoutlet.utils.entittes.v2.UpdateToken;

/**
 * Created by Himanshu on 4/4/2017.
 */

public class SplashPresenterImpli implements SplashPresenter,SplashInteractor.OnUpdatePushTokenAndAppVersionFinishedListener {


    private SplashView splashView;
    SplashInteractor landingInteractor;


    public SplashPresenterImpli(SplashView splashView) {
        this.splashView = splashView;
        this.landingInteractor = new SplashInteractorImpil();

    }





    @Override
    public void updatePushTokenAndAppVersion(Context con) {
        landingInteractor.updatePushTokenAndAppVersion( con,this);
    }



    @Override
    public void updateStatus(Boolean status, UpdateToken updateToken) {
        splashView.updateStatus(status,updateToken);
    }


}
