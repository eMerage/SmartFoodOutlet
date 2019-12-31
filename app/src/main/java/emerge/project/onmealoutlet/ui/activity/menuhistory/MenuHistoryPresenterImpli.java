package emerge.project.onmealoutlet.ui.activity.menuhistory;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.OutletSales;
import emerge.project.onmealoutlet.utils.entittes.v2.MenuHistory.MenuHistoryData;
import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryData;

/**
 * Created by Himanshu on 4/4/2017.
 */

public class MenuHistoryPresenterImpli implements MenuHistoryPresenter,
        MenuHistoryInteractor.OnGetMenuHistoryFinishedListener{


    private MenuHistoryView menuHistoryView;
    MenuHistoryInteractor menuHistoryInteractor;





    public MenuHistoryPresenterImpli(MenuHistoryView sview) {
        this.menuHistoryView = sview;
        this.menuHistoryInteractor = new MenuHistoryInteractorImpil();

    }

    @Override
    public void getMenuHistory(String sDate,String eDate) {
        menuHistoryInteractor.getMenuHistory( sDate, eDate,this);
    }


    @Override
    public void getMenuHistory(MenuHistoryData menuItems) {
        menuHistoryView.getMenuHistory(menuItems);
    }

    @Override
    public void getMenuHistoryFail(String msg) {
        menuHistoryView.getMenuHistoryFail(msg);
    }


}
