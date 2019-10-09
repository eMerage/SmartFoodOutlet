package emerge.project.onmealoutlet.ui.activity.menus;




/**
 * Created by Himanshu on 4/4/2017.
 */

public interface MenusPresenter {


    void getMenuCategory();
    void getMainFoodByOutlet(String menuCategoryID);
    void updateAvailableQty(int outletMenuTitleID,int availableQty);



}
