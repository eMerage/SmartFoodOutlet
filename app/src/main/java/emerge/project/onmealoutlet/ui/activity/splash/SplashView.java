package emerge.project.onmealoutlet.ui.activity.splash;


import emerge.project.onmealoutlet.utils.entittes.v2.UpdateToken;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface SplashView {

    void updateStatus(Boolean status, UpdateToken updateToken);
    void trialversion(Boolean status, String msg);



}
