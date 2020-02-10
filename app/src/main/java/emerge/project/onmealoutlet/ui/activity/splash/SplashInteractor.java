package emerge.project.onmealoutlet.ui.activity.splash;


import android.content.Context;

import emerge.project.onmealoutlet.utils.entittes.v2.UpdateToken;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface SplashInteractor {



    interface OnUpdatePushTokenAndAppVersionFinishedListener {
        void updateStatus(Boolean status, UpdateToken updateToken);
    }
    void updatePushTokenAndAppVersion(Context con, OnUpdatePushTokenAndAppVersionFinishedListener onUpdatePushTokenAndAppVersionFinishedListener);

    interface OnCheckTrialVersionFinishedListener {
        void trialversion(Boolean status, String msg);
    }
    void checkTrialVersion(Context con, OnCheckTrialVersionFinishedListener onCheckTrialVersionFinishedListener);



}
