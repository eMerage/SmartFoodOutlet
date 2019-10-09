package emerge.project.onmealoutlet.ui.activity.menuhistory;


import java.util.ArrayList;

import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.ui.activity.settings.SettingsInteractor;
import emerge.project.onmealoutlet.ui.activity.settings.SettingsInteractorImpil;
import emerge.project.onmealoutlet.ui.activity.settings.SettingsPresenter;
import emerge.project.onmealoutlet.ui.activity.settings.SettingsView;
import emerge.project.onmealoutlet.utils.entittes.MenuHistoryEntittes;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;

/**
 * Created by Himanshu on 4/4/2017.
 */

public class MenuHistoryPresenterImpli implements MenuHistoryPresenter,
        MenuHistoryInteractor.OnGetMenuHistoryFinishedListener,
MenuHistoryInteractor.OnGetOrderHistorySalseFinishedListener{


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
    public void getMenuHistory(ArrayList<MenuHistoryEntittes> menuItems) {
        menuHistoryView.getMenuHistory(menuItems);
    }

    @Override
    public void getMenuHistoryFail(String msg) {
        menuHistoryView.getMenuHistoryFail(msg);
    }

    @Override
    public void getOrderHistorySalse(String sDate, String eDate) {
        menuHistoryInteractor.getOrderHistorySalse(sDate,eDate,this);
    }

    @Override
    public void getOrderHistorySalse(OutletSales outletSales) {
        menuHistoryView.getOrderHistorySalse(outletSales);
    }

    @Override
    public void getOrderHistoryFailSalse(String msg) {
        menuHistoryView.getOrderHistoryFailSalse(msg);
    }
}
