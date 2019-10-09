package emerge.project.onmealoutlet.ui.activity.menus;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;

/**
 * Created by Himanshu on 4/4/2017.
 */

public class MenusPresenterImpli implements MenusPresenter,
        MenusInteractor.OnGetMenuCategoryFinishedListener,
MenusInteractor.OnMainFoodByOutletListener,
        MenusInteractor.OnUpdateAvailableQtyFinishedListener{


    private MenusView menusView;
    MenusInteractor menusInteractor;


    public MenusPresenterImpli(MenusView menuview) {
        this.menusView = menuview;
        this.menusInteractor = new MenusInteractorImpil();

    }


    @Override
    public void getMenuCategory() {
        menusInteractor.getMenuCategory(this);
    }


    @Override
    public void menuCategory(ArrayList<MenuCategoryItems> menuCategoryItems) {
        menusView.menuCategory(menuCategoryItems);
    }

    @Override
    public void menuCategoryFail(String msg) {
        menusView.menuCategoryFail(msg);
    }






    @Override
    public void getMainFoodByOutlet(String menuCategoryID) {
        menusInteractor.getMainFoodByOutlet(menuCategoryID,this);
    }



    @Override
    public void getMainFoodByOutletLoadStart() {
        menusView.getMainFoodByOutletLoadStart();
    }

    @Override
    public void getMainFoodByOutletSuccess(ArrayList<MenuItems> menuItems) {
        menusView.getMainFoodByOutletSuccess(menuItems);
    }

    @Override
    public void getMainFoodByOutletLoadFail(String msg,String menuCategoryID) {
        menusView.getMainFoodByOutletLoadFail(msg,menuCategoryID);
    }

    @Override
    public void getMainFoodByOutletLoadEmpty() {
        menusView.getMainFoodByOutletLoadEmpty();
    }








    @Override
    public void updateAvailableQty(int outletMenuTitleID, int availableQty) {
        menusInteractor.updateAvailableQty(outletMenuTitleID,availableQty,this);
    }


    @Override
    public void updateAvailableStart() {
        menusView.updateAvailableStart();
    }

    @Override
    public void updateAvailableSuccess() {
        menusView.updateAvailableSuccess();
    }

    @Override
    public void updateAvailableFail(String msg, int outletMenuTitleID, int availableQty) {
        menusView.updateAvailableFail(msg,outletMenuTitleID,availableQty);
    }
}
