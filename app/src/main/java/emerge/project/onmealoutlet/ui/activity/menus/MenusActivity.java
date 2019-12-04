package emerge.project.onmealoutlet.ui.activity.menus;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.tuyenmonkey.mkloader.MKLoader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.servies.network.NetworkAvailability;
import emerge.project.onmealoutlet.ui.activity.home.Home;
import emerge.project.onmealoutlet.ui.activity.splash.Splash;
import emerge.project.onmealoutlet.ui.adaptor.MenuCategoryAdapter;
import emerge.project.onmealoutlet.ui.adaptor.MenuUpdateAdapter;
import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;

public class MenusActivity extends Activity implements MenusView{


    @BindView(R.id.recyclerView_menu_cat)
    RecyclerView recyclerViewMenuCat;


    @BindView(R.id.recyclerView_menu)
    RecyclerView recyclerViewMenu;

    @BindView(R.id.proprogressview)
    MKLoader proprogressview;

    MenusPresenter menuPresenter;
    MenuCategoryAdapter menuCategoryAdapter;


    MenuUpdateAdapter menuUpdateAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menus);
        ButterKnife.bind(this);



        menuPresenter = new MenusPresenterImpli(this);

        setMenuCatRecycalView();
        setMenuRecycalView();


        if (NetworkAvailability.isNetworkAvailable(getApplicationContext())) {
            proprogressview.setVisibility(View.VISIBLE);
            bloackUserInteraction();
            menuPresenter.getMenuCategory();

        } else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Warning");
            alertDialogBuilder.setMessage("No Internet Access, Please try again ");
            alertDialogBuilder.setPositiveButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
            alertDialogBuilder.show();
        }

    }





    private void setMenuCatRecycalView() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewMenuCat.setLayoutManager(layoutManager);
        recyclerViewMenuCat.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMenuCat.setNestedScrollingEnabled(false);


    }


    private void setMenuRecycalView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewMenu.setLayoutManager(layoutManager);
        recyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMenu.setNestedScrollingEnabled(false);

    }


    private void bloackUserInteraction() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }



    private void unBloackUserInteraction() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }



    @OnClick(R.id.relativeLayout_slider_menu)
    public void onClickBackMenu(View view) {

        final Intent intent;
        intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();

    }



    @Override
    public void menuCategory(ArrayList<MenuCategoryItems> menuCategoryItems) {
        proprogressview.setVisibility(View.GONE);
        unBloackUserInteraction();
        menuCategoryAdapter = new MenuCategoryAdapter(this, menuCategoryItems, this);
        recyclerViewMenuCat.setAdapter(menuCategoryAdapter);
    }

    @Override
    public void menuCategoryFail(String msg) {
        unBloackUserInteraction();
        proprogressview.setVisibility(View.GONE);
        try {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Warning");
            alertDialogBuilder.setMessage(msg);
            alertDialogBuilder.setPositiveButton("Re-Try",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (NetworkAvailability.isNetworkAvailable(getApplicationContext())) {
                                proprogressview.setVisibility(View.VISIBLE);
                                bloackUserInteraction();
                                menuPresenter.getMenuCategory();
                            } else {
                                Toast.makeText(MenusActivity.this, "No Internet Access, Please try again", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
            alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });
            alertDialogBuilder.show();

        } catch (WindowManager.BadTokenException e) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }





    @Override
    public void getMainFoodByOutletLoadStart() {
        proprogressview.setVisibility(View.VISIBLE);
        bloackUserInteraction();

    }


    @Override
    public void getMainFoodByOutletSuccess(ArrayList<MenuItems> menuItems) {

        proprogressview.setVisibility(View.GONE);
        unBloackUserInteraction();
        menuUpdateAdapter = new MenuUpdateAdapter(this, menuItems,this);
        recyclerViewMenu.setAdapter(menuUpdateAdapter);

    }

    @Override
    public void getMainFoodByOutletLoadFail(String msg, final String menuCategoryID) {
        proprogressview.setVisibility(View.GONE);
        unBloackUserInteraction();


        try {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Warning");
            alertDialogBuilder.setMessage(msg);
            alertDialogBuilder.setPositiveButton("Re-Try",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (NetworkAvailability.isNetworkAvailable(getApplicationContext())) {
                                proprogressview.setVisibility(View.VISIBLE);
                                bloackUserInteraction();menuPresenter.getMainFoodByOutlet(menuCategoryID);
                            } else {
                                Toast.makeText(MenusActivity.this, "No Internet Access, Please try again", Toast.LENGTH_SHORT).show();

                            }


                        }
                    });
            alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });
            alertDialogBuilder.show();
        } catch (WindowManager.BadTokenException e) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    @Override
    public void getMainFoodByOutletLoadEmpty() {
        proprogressview.setVisibility(View.GONE);
        unBloackUserInteraction();
        Toast.makeText(this, "No Items", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAvailableStart() {
        proprogressview.setVisibility(View.VISIBLE);
        bloackUserInteraction();
    }


    @Override
    public void updateAvailableSuccess() {

        proprogressview.setVisibility(View.GONE);
        unBloackUserInteraction();
        Toast.makeText(this, "Update successfully completed ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateAvailableFail(String msg, final int outletMenuTitleID, final int availableQty) {

        proprogressview.setVisibility(View.GONE);
        unBloackUserInteraction();
        try {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Warning");
            alertDialogBuilder.setMessage(msg);
            alertDialogBuilder.setPositiveButton("Re-Try",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (NetworkAvailability.isNetworkAvailable(getApplicationContext())) {
                                proprogressview.setVisibility(View.VISIBLE);
                                bloackUserInteraction();
                                menuPresenter.updateAvailableQty(outletMenuTitleID, availableQty);
                            } else {
                                Toast.makeText(MenusActivity.this, "No Internet Access, Please try again", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
            alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    return;
                }
            });
            alertDialogBuilder.show();
        } catch (WindowManager.BadTokenException e) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }


    }
}
