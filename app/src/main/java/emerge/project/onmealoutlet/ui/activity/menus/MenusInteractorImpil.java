package emerge.project.onmealoutlet.ui.activity.menus;


import java.util.ArrayList;
import java.util.List;

import emerge.project.onmealoutlet.data.db.Outlet;
import emerge.project.onmealoutlet.servies.api.ApiClient;
import emerge.project.onmealoutlet.servies.api.ApiInterface;

import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;


/**
 * Created by Himanshu on 4/5/2017.
 */

public class MenusInteractorImpil implements MenusInteractor {

    Realm realm = Realm.getDefaultInstance();
    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

    Outlet outlet = realm.where(Outlet.class).findFirst();
    List<MenuCategoryItems> menuCategory;
    List<MenuItems> menuItemsList;
    int updateAvailableQtyRes=0;



    @Override
    public void getMenuCategory(final OnGetMenuCategoryFinishedListener onGetMenuCategoryFinishedListener) {


        final ArrayList<MenuCategoryItems> menuCategoryItemsArrayList = new ArrayList<MenuCategoryItems>();
        try {
            apiService.getMenuCategoriesForOutlet(outlet.getOutletId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<MenuCategoryItems>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(List<MenuCategoryItems> menucategory) {
                            menuCategory = menucategory;
                        }

                        @Override
                        public void onError(Throwable e) {
                            onGetMenuCategoryFinishedListener.menuCategoryFail("Something went wrong, Please try again");
                        }

                        @Override
                        public void onComplete() {
                            if (menuCategory.isEmpty()) {
                                onGetMenuCategoryFinishedListener.menuCategoryFail("Something went wrong, Please try again");
                            } else {
                                for (int i = 0; i < menuCategory.size(); i++) {
                                    if (i == 0) {
                                        menuCategoryItemsArrayList.add(new MenuCategoryItems(menuCategory.get(i).getMenuCategoryID(), menuCategory.get(i).getMenuCategory(), true));
                                    } else {
                                        menuCategoryItemsArrayList.add(new MenuCategoryItems(menuCategory.get(i).getMenuCategoryID(), menuCategory.get(i).getMenuCategory(), false));
                                    }
                                }
                                onGetMenuCategoryFinishedListener.menuCategory(menuCategoryItemsArrayList);

                            }

                        }
                    });
        } catch (Exception ex) {
            onGetMenuCategoryFinishedListener.menuCategoryFail("Something went wrong, Please try again");
        }

    }

    @Override
    public void getMainFoodByOutlet(final String menuCategoryID, final OnMainFoodByOutletListener onMainFoodByOutletListener) {


        onMainFoodByOutletListener.getMainFoodByOutletLoadStart();

        final ArrayList<MenuItems> menuItemsArrayList = new ArrayList<MenuItems>();

        try {
            apiService.getMainFoodByOutlet(outlet.getOutletId(),Integer.parseInt(menuCategoryID))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<MenuItems>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                        @Override
                        public void onNext(List<MenuItems> items) {
                            menuItemsList = items;
                        }

                        @Override
                        public void onError(Throwable e) {
                            onMainFoodByOutletListener.getMainFoodByOutletLoadFail("Something went wrong, Please try again", menuCategoryID);
                        }

                        @Override
                        public void onComplete() {
                            if (menuItemsList.isEmpty()) {
                                onMainFoodByOutletListener.getMainFoodByOutletLoadEmpty();
                            } else {
                                for (int i = 0; i < menuItemsList.size(); i++) {
                                    menuItemsArrayList.add(new MenuItems(menuItemsList.get(i).getMenuId(),menuItemsList.get(i).getFoodId(),menuItemsList.get(i).getFoodName(),
                                            menuItemsList.get(i).getFoodCoverImage(),menuItemsList.get(i).getMaxAvailableQty()));
                                }
                                onMainFoodByOutletListener.getMainFoodByOutletSuccess(menuItemsArrayList);

                            }

                        }
                    });

        } catch (Exception ex) {
            onMainFoodByOutletListener.getMainFoodByOutletLoadFail("Something went wrong, Please try again",menuCategoryID);
        }


    }

    @Override
    public void updateAvailableQty(final int outletMenuTitleID, final int availableQty, final OnUpdateAvailableQtyFinishedListener onUpdateAvailableQtyFinishedListener) {

        onUpdateAvailableQtyFinishedListener.updateAvailableStart();

        try {
            apiService.updateAvailableQty(outletMenuTitleID,availableQty)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }
                        @Override
                        public void onNext(Integer resp) {
                            updateAvailableQtyRes=resp;
                        }

                        @Override
                        public void onError(Throwable e) {
                            onUpdateAvailableQtyFinishedListener.updateAvailableFail("Something went wrong, Please try again",outletMenuTitleID,availableQty);
                        }

                        @Override
                        public void onComplete() {
                            if(updateAvailableQtyRes==1){
                                onUpdateAvailableQtyFinishedListener.updateAvailableSuccess();
                            }else {
                                onUpdateAvailableQtyFinishedListener.updateAvailableFail("Something went wrong, Please try again",outletMenuTitleID,availableQty);
                            }

                        }
                    });

        } catch (Exception ex) {
            onUpdateAvailableQtyFinishedListener.updateAvailableFail("Something went wrong, Please try again",outletMenuTitleID,availableQty);
        }





    }
}
