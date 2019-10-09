package emerge.project.onmealoutlet.ui.activity.settings;


import java.util.ArrayList;

import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface SettingsInteractor {

    interface OnGetOutletDetailsFinishedListener {
        void getOutletDetails(Outlet outletItems);
        void getOutletDetailsFail(String msg);
    }
    void getOutletDetails( OnGetOutletDetailsFinishedListener onGetOutletDetailsFinishedListener);



    interface OnUpdateTimeIntervalFinishedListener {
        void updateTimeIntervalSuccess(int time);
        void updateTimeIntervalFail(String msg);
    }
    void updateTimeInterval(int time, OnUpdateTimeIntervalFinishedListener onUpdateTimeIntervalFinishedListener);




}
