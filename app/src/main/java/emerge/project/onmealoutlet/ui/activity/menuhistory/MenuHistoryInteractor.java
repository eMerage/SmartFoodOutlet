package emerge.project.onmealoutlet.ui.activity.menuhistory;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.OutletSales;
import emerge.project.onmealoutlet.utils.entittes.v2.MenuHistory.MenuHistoryData;
import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryData;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface MenuHistoryInteractor {

    interface OnGetMenuHistoryFinishedListener {
        void getMenuHistory(MenuHistoryData menuItems);
        void getMenuHistoryFail(String msg);
    }
    void  getMenuHistory(String sDate,String eDate,OnGetMenuHistoryFinishedListener onGetMenuHistoryFinishedListener);



}
