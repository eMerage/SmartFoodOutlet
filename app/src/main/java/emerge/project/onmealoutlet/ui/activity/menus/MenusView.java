package emerge.project.onmealoutlet.ui.activity.menus;


import java.util.ArrayList;

import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;

/**
 * Created by Himanshu on 4/4/2017.
 */

public interface MenusView {


    void menuCategory(ArrayList<MenuCategoryItems> menuCategoryItems);

    void menuCategoryFail(String msg);


    void getMainFoodByOutletLoadStart();

    void getMainFoodByOutletSuccess(ArrayList<MenuItems> menuItems);

    void getMainFoodByOutletLoadFail(String msg, String menuCategoryID);

    void getMainFoodByOutletLoadEmpty();


    void updateAvailableStart();
    void updateAvailableSuccess();
    void updateAvailableFail(String msg,int outletMenuTitleID,int availableQty);



}
