package emerge.project.onmealoutlet.ui.activity.menus;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface MenusInteractor {


    interface OnGetMenuCategoryFinishedListener {
        void menuCategory(ArrayList<MenuCategoryItems> menuCategoryItems);
        void menuCategoryFail(String msg);
    }
    void getMenuCategory( OnGetMenuCategoryFinishedListener onGetMenuCategoryFinishedListener);



    interface OnMainFoodByOutletListener {
        void getMainFoodByOutletLoadStart();
        void getMainFoodByOutletSuccess(ArrayList<MenuItems> menuItems);
        void getMainFoodByOutletLoadFail(String msg, String menuCategoryID);
        void getMainFoodByOutletLoadEmpty();
    }
    void getMainFoodByOutlet(String menuCategoryID, OnMainFoodByOutletListener onMainFoodByOutletListener);



    interface OnUpdateAvailableQtyFinishedListener {
        void updateAvailableStart();
        void updateAvailableSuccess();
        void updateAvailableFail(String msg,int outletMenuTitleID,int availableQty);
    }
    void updateAvailableQty(int outletMenuTitleID,int availableQty, OnUpdateAvailableQtyFinishedListener onUpdateAvailableQtyFinishedListener);



}
