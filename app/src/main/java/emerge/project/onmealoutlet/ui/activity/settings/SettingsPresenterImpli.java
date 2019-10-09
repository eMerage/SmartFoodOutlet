package emerge.project.onmealoutlet.ui.activity.settings;


import java.util.ArrayList;

import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.ui.activity.menus.MenusInteractor;
import emerge.project.onmealoutlet.ui.activity.menus.MenusInteractorImpil;
import emerge.project.onmealoutlet.ui.activity.menus.MenusPresenter;
import emerge.project.onmealoutlet.ui.activity.menus.MenusView;
import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;

/**
 * Created by Himanshu on 4/4/2017.
 */

public class SettingsPresenterImpli implements SettingsPresenter,
        SettingsInteractor.OnGetOutletDetailsFinishedListener,
SettingsInteractor.OnUpdateTimeIntervalFinishedListener{


    private SettingsView settingsView;
    SettingsInteractor settingsInteractor;





    public SettingsPresenterImpli(SettingsView sview) {
        this.settingsView = sview;
        this.settingsInteractor = new SettingsInteractorImpil();

    }



    @Override
    public void getOutletDetails() {
        settingsInteractor.getOutletDetails(this);
    }



    @Override
    public void getOutletDetails(Outlet outletItems) {
        settingsView.getOutletDetails(outletItems);
    }

    @Override
    public void getOutletDetailsFail(String msg) {
        settingsView.getOutletDetailsFail(msg);
    }







    @Override
    public void updateTimeInterval(int time) {
        settingsInteractor.updateTimeInterval(time,this);

    }



    @Override
    public void updateTimeIntervalSuccess(int time) {
        settingsView.updateTimeIntervalSuccess(time);
    }

    @Override
    public void updateTimeIntervalFail(String msg) {
        settingsView.updateTimeIntervalFail(msg);
    }
}
