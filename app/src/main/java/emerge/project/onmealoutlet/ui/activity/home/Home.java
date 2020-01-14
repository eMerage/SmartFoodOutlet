package emerge.project.onmealoutlet.ui.activity.home;

import android.Manifest;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.servies.network.NetworkAvailability;
import emerge.project.onmealoutlet.servies.print.PrintUtility;
import emerge.project.onmealoutlet.ui.activity.login.Login;
import emerge.project.onmealoutlet.ui.activity.menuhistory.MenuHistory;
import emerge.project.onmealoutlet.ui.activity.menus.MenusActivity;
import emerge.project.onmealoutlet.ui.activity.orderHistory.OrderHistory;
import emerge.project.onmealoutlet.ui.activity.settings.Setting;
import emerge.project.onmealoutlet.ui.adaptor.DeliveryRidersAdapter;
import emerge.project.onmealoutlet.ui.adaptor.DeliveryTimeSlotsAdapter;
import emerge.project.onmealoutlet.ui.adaptor.DispatchAdapter;
import emerge.project.onmealoutlet.ui.adaptor.MenuAdapter;
import emerge.project.onmealoutlet.ui.adaptor.PackedAdapter;
import emerge.project.onmealoutlet.ui.adaptor.PendingAdapter;
import emerge.project.onmealoutlet.ui.adaptor.ProcessAdapter;
import emerge.project.onmealoutlet.ui.adaptor.SliderMenuAdapter;

import emerge.project.onmealoutlet.utils.entittes.DeliveryRiders;
import emerge.project.onmealoutlet.utils.entittes.Menus;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.SliderMenuItems;
import emerge.project.onmealoutlet.utils.entittes.TimeSlots;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrderMenus;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrdersData;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrdersList;


public class Home extends FragmentActivity implements HomeView {


    @BindView(R.id.listview_slider)
    ListView listViewSlider;


    @BindView(R.id.spinner_timeslotes)
    Spinner spinnerTimeslotes;
    @BindView(R.id.spinner_riders)
    Spinner spinnerRiders;

    @BindView(R.id.relativelayout_time)
    RelativeLayout relativelayoutTime;


    @BindView(R.id.drawer_layout)
    DrawerLayout dLayout;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;


    @BindView(R.id.button_pending)
    Button buttonPending;

    @BindView(R.id.button_process)
    Button buttonProcess;

    @BindView(R.id.button_pack)
    Button buttonPack;

    @BindView(R.id.button_dispatch)
    Button buttonDispatch;


    @BindView(R.id.textView_message)
    TextView textViewMessage;
    @BindView(R.id.button_retry)
    Button buttonRetry;


    @BindView(R.id.textview_version)
    TextView textviewVersion;


    @BindView(R.id.recyclerview_main)
    RecyclerView recyclerviewMain;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeContainer;


    @BindView(R.id.textView_total)
    TextView textViewTotal;

    @BindView(R.id.textView_totalQty)
    TextView textViewTotalQty;


    @BindView(R.id.textview_outletname)
    TextView textviewOutletName;


    static Home instance;

    private static final int PERMISSIONS_REQUEST_ACCESS_BLUTOOTH = 101;


    PendingAdapter pendingAdapter;
    ProcessAdapter processAdapter;
    PackedAdapter packedAdapter;
    DispatchAdapter dispatchAdapter;

    MenuAdapter menuAdapter;

    ArrayList<SliderMenuItems> sliderItems;

    HomePresenter homePresenter;

    SliderMenuAdapter sliderMenuAdapter;


    int selectDeliveryTimeSlotId = 0;
    int selectriderId = 0;
    String dispathType = "";


    String status;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_home);

        ButterKnife.bind(this);
        instance = this;

        textviewVersion.setText(getApplicationVersion());

        status = "ODPN";

        homePresenter = new HomePresenterImpli(this);

        setSliderMenu();

        homePresenter.getOutletName();
        homePresenter.getDeliveryTimeSlots(status);
        homePresenter.getDeliveryRiders(status);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerviewMain.setLayoutManager(mLayoutManager);
        recyclerviewMain.setItemAnimator(new DefaultItemAnimator());
        recyclerviewMain.setNestedScrollingEnabled(true);


        bloackUserInteraction();
        progressBar.setVisibility(View.VISIBLE);

        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);
        homePresenter.getIncome();


        dispathType = "";

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_BLUTOOTH);
        } else {

        }


        listViewSlider.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {

                } else if (position == 1) {

                } else if (position == 2) {

                    Intent intent = new Intent(Home.this, MenusActivity.class);
                    startActivity(intent);
                    finish();

                    dLayout.closeDrawers();
                } else if (position == 3) {
                    Intent intent = new Intent(Home.this, Setting.class);
                    startActivity(intent);
                    finish();
                    dLayout.closeDrawers();
                } else if (position == 4) {
                    Intent intent = new Intent(Home.this, MenuHistory.class);
                    startActivity(intent);
                    finish();
                    dLayout.closeDrawers();
                } else if (position == 5) {
                    Intent intent = new Intent(Home.this, OrderHistory.class);
                    startActivity(intent);
                    finish();

                    
                    dLayout.closeDrawers();
                } else if (position == 6) {
                    homePresenter.logOut();
                }
            }
        });


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                homePresenter.getIncome();
                homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        homePresenter.getIncome();
        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);

    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radio_delivery:
                if (checked)
                    dispathType = "D";
                break;
            case R.id.radio_pickup:
                if (checked)
                    dispathType = "P";
                break;
            case R.id.radio_dinein:
                if (checked)
                    dispathType = "T";
                break;
        }
    }


    @OnClick(R.id.button_filter_fn)
    public void onClickFilteDone(View view) {
        bloackUserInteraction();
        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);
    }


    public void refrashOrdersWhenOrderProsess() {
        bloackUserInteraction();
        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);

    }


    @OnClick(R.id.button_clear)
    public void onClickFilteClear(View view) {

        selectDeliveryTimeSlotId = 0;
        selectriderId = 0;
        dispathType = "";

        radiogroup.clearCheck();

        bloackUserInteraction();
        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);

    }


    @OnClick(R.id.button_filter)
    public void onClickFiltering(View view) {
        progressBar.setVisibility(View.VISIBLE);
        bloackUserInteraction();
        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);

        dLayout.closeDrawers();
    }


    @OnClick(R.id.button_pending)
    public void onClickPending(View view) {


        status = "ODPN";


        homePresenter.getDeliveryTimeSlots(status);
        homePresenter.getDeliveryRiders(status);

        progressBar.setVisibility(View.VISIBLE);
        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);


        bloackUserInteraction();

        buttonPending.setBackgroundColor(getResources().getColor(R.color.app_color_red));
        buttonProcess.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
        buttonPack.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
        buttonDispatch.setBackgroundColor(getResources().getColor(R.color.app_btn_background));


    }

    @OnClick(R.id.button_process)
    public void onClickProcess(View view) {

        status = "ODPR";

        homePresenter.getDeliveryTimeSlots(status);
        homePresenter.getDeliveryRiders(status);


        progressBar.setVisibility(View.VISIBLE);
        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);

        bloackUserInteraction();

        buttonPending.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
        buttonProcess.setBackgroundColor(getResources().getColor(R.color.app_color_red));
        buttonPack.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
        buttonDispatch.setBackgroundColor(getResources().getColor(R.color.app_btn_background));

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit!");
        alertDialogBuilder.setMessage("Do you really want to exit ?");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        alertDialogBuilder.show();

    }

    @OnClick(R.id.button_pack)
    public void onClickPack(View view) {

        status = "ODPK";


        homePresenter.getDeliveryTimeSlots(status);
        homePresenter.getDeliveryRiders(status);
        progressBar.setVisibility(View.VISIBLE);

        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);


        bloackUserInteraction();

        buttonPending.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
        buttonProcess.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
        buttonPack.setBackgroundColor(getResources().getColor(R.color.app_color_red));
        buttonDispatch.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
    }

    @OnClick(R.id.button_dispatch)
    public void onClickDispatch(View view) {

        status = "ODDS";
        homePresenter.getDeliveryTimeSlots(status);
        homePresenter.getDeliveryRiders(status);

        progressBar.setVisibility(View.VISIBLE);
        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);


        bloackUserInteraction();

        buttonPending.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
        buttonProcess.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
        buttonPack.setBackgroundColor(getResources().getColor(R.color.app_btn_background));
        buttonDispatch.setBackgroundColor(getResources().getColor(R.color.app_color_red));
    }


    @OnClick(R.id.relativeLayout_slider_menu)
    public void onClickSliderMenue(View view) {
        dLayout.openDrawer(Gravity.LEFT);
    }

    @OnClick(R.id.relativelayout_filtermenu)
    public void onClickFilterMenue(View view) {
        dLayout.openDrawer(Gravity.RIGHT);
    }

    @Override
    public void onFinishedNavigationItems() {
        listViewSlider.setAdapter(sliderMenuAdapter);
    }


    @Override
    public void showNoTimeSlotsAvailable() {
        spinnerTimeslotes.setVisibility(View.GONE);
        Toast.makeText(this, "No time slots available", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showTimeSlots(final ArrayList<TimeSlots> timeSlots) {
        spinnerTimeslotes.setVisibility(View.VISIBLE);


        DeliveryTimeSlotsAdapter deliveryTimeSlotsAdapter = new DeliveryTimeSlotsAdapter(this, timeSlots);
        spinnerTimeslotes.setAdapter(deliveryTimeSlotsAdapter);


        spinnerTimeslotes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                selectDeliveryTimeSlotId = timeSlots.get(position).getSlotID();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void showNoRidersAvailable() {
        spinnerRiders.setVisibility(View.GONE);
        Toast.makeText(this, "No Riders available", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showRidersList(final ArrayList<DeliveryRiders> deliveryRiders) {

        spinnerRiders.setVisibility(View.VISIBLE);

        DeliveryRidersAdapter deliveryRidersAdapter = new DeliveryRidersAdapter(this, deliveryRiders);
        spinnerRiders.setAdapter(deliveryRidersAdapter);


        spinnerRiders.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectriderId = deliveryRiders.get(position).getRiderID();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }



    @Override
    public void ordersFullDetails(ArrayList<OrderMenus> menusArrayList) {
        progressBar.setVisibility(View.GONE);

        unBloackUserInteraction();

        Dialog dialogBox;
        dialogBox = new Dialog(this);
        dialogBox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogBox.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialogBox.setContentView(R.layout.dialog_cart_subitems);
        dialogBox.setCancelable(true);


        RecyclerView recyclerViewOrderSubitems = (RecyclerView) dialogBox.findViewById(R.id.recyclerview_subcart_items);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewOrderSubitems.setLayoutManager(layoutManager);
        recyclerViewOrderSubitems.setItemAnimator(new DefaultItemAnimator());
        recyclerViewOrderSubitems.setNestedScrollingEnabled(true);


        menuAdapter = new MenuAdapter(this, menusArrayList);
        recyclerViewOrderSubitems.setAdapter(menuAdapter);



        dialogBox.show();

    }

    @Override
    public void updateOrderStatusSuccessful(int orderCurrentStatus, int orderId, String dispatchType) {
        progressBar.setVisibility(View.GONE);
        unBloackUserInteraction();
        homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);

        if (orderCurrentStatus == 3) {
            if ((dispatchType.equals("T")) || (dispatchType.equals("P"))) {
                homePresenter.updateOrderStatus(orderId, "ODCP", dispatchType);
            } else {

            }
        } else {

        }

    }

    @Override
    public void updateOrderStatusFail(final int orderId, final String statusCode, String msg, final String dispatchType) {

        progressBar.setVisibility(View.GONE);
        unBloackUserInteraction();

        try {

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("Warning");
            alertDialogBuilder.setMessage(msg);
            alertDialogBuilder.setPositiveButton("Re-Try",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            if (NetworkAvailability.isNetworkAvailable(getApplicationContext())) {
                                homePresenter.updateOrderStatus(orderId, statusCode, dispatchType);
                            } else {

                                Toast.makeText(Home.this, "No Internet Access, Please try again", Toast.LENGTH_SHORT).show();

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

        //    errorHandlingUI("Update Fail", true, false, 0);

    }

    @Override
    public void updateOrderStatusStart() {
        progressBar.setVisibility(View.VISIBLE);
        bloackUserInteraction();

    }


    // OrdersStatusupdate//


    @Override
    public void printOrdersStart() {
        progressBar.setVisibility(View.VISIBLE);
        bloackUserInteraction();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void printOrdersSuccessful(Bundle bundleToView) {
        progressBar.setVisibility(View.GONE);
        unBloackUserInteraction();


        Intent activityIntent = new Intent(getApplicationContext(), PrintUtility.class);
        activityIntent.putExtras(bundleToView);
        startActivityForResult(activityIntent, 0);


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_BLUTOOTH:
                if (grantResults.length > 0) {
                    boolean readSMSAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if (readSMSAccepted) {

                    } else {
                        if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)) {
                            showMessageOKCancel("You need to allow access to both the permissions",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_BLUTOOTH);

                                            }
                                        }
                                    });
                            return;
                        }
                    }
                }

        }
    }

    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new android.support.v7.app.AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }


    @Override
    public void printOrdersFail(String msg) {
        progressBar.setVisibility(View.GONE);
        unBloackUserInteraction();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();

    }

    @Override
    public void printOrdersTimeOut(int orderId) {

        progressBar.setVisibility(View.GONE);
        unBloackUserInteraction();
        Toast.makeText(this, "Print fail ,Try again", Toast.LENGTH_LONG).show();

    }


    @Override
    public void ordersList(OrdersData ordersArrayList) {

        swipeContainer.setRefreshing(false);

        progressBar.setVisibility(View.GONE);
        unBloackUserInteraction();





        if(ordersArrayList.getStatus()){

            errorHandlingUI("", false, false, 0);


            if (status.equals("ODPN")) {
                pendingAdapter = new PendingAdapter(this, ordersArrayList.getOrdersList(), this);
                recyclerviewMain.setAdapter(pendingAdapter);

            } else if (status.equals("ODPR")) {
                processAdapter = new ProcessAdapter(this, ordersArrayList.getOrdersList(), this);
                recyclerviewMain.setAdapter(processAdapter);

            } else if (status.equals("ODPK")) {
                packedAdapter = new PackedAdapter(this, ordersArrayList.getOrdersList(), this);
                recyclerviewMain.setAdapter(packedAdapter);
            } else if (status.equals("ODDS")) {
                dispatchAdapter = new DispatchAdapter(this, ordersArrayList.getOrdersList(), this);
                recyclerviewMain.setAdapter(dispatchAdapter);
            }


        }else {
            errorHandlingUI(ordersArrayList.getError().getErrDescription(), true, true, 0);

            final ArrayList<OrdersList> ordersEmptyList = new ArrayList<OrdersList>();

           if (status.equals("ODPN")) {
                pendingAdapter = new PendingAdapter(this, ordersEmptyList, this);
                recyclerviewMain.setAdapter(pendingAdapter);

            } else if (status.equals("ODPR")) {
                processAdapter = new ProcessAdapter(this, ordersEmptyList, this);
                recyclerviewMain.setAdapter(processAdapter);


            } else if (status.equals("ODPK")) {
                packedAdapter = new PackedAdapter(this, ordersEmptyList, this);
                recyclerviewMain.setAdapter(packedAdapter);
            } else if (status.equals("ODDS")) {
                dispatchAdapter = new DispatchAdapter(this, ordersEmptyList, this);
                recyclerviewMain.setAdapter(dispatchAdapter);
            }

        }






    }

/*    @Override
    public void noOrdersList() {
        swipeContainer.setRefreshing(false);
        progressBar.setVisibility(View.GONE);
        unBloackUserInteraction();
        errorHandlingUI("No Orders", true, false, 0);

        final ArrayList<Orders> ordersArrayList = new ArrayList<Orders>();

        if (status.equals("ODPN")) {
            pendingAdapter = new PendingAdapter(this, ordersArrayList, this);
            recyclerviewMain.setAdapter(pendingAdapter);

        } else if (status.equals("ODPR")) {
            processAdapter = new ProcessAdapter(this, ordersArrayList, this);
            recyclerviewMain.setAdapter(processAdapter);


        } else if (status.equals("ODPK")) {
            packedAdapter = new PackedAdapter(this, ordersArrayList, this);
            recyclerviewMain.setAdapter(packedAdapter);
        } else if (status.equals("ODDS")) {
            dispatchAdapter = new DispatchAdapter(this, ordersArrayList, this);
            recyclerviewMain.setAdapter(dispatchAdapter);
        }


    }*/

    @Override
    public void ordersTimeOut() {
        progressBar.setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);
        unBloackUserInteraction();
        errorHandlingUI("Connection lost, Please try again", true, true, 0);


        final ArrayList<OrdersList> ordersEmptyList = new ArrayList<OrdersList>();

        if (status.equals("ODPN")) {
            pendingAdapter = new PendingAdapter(this, ordersEmptyList, this);
            recyclerviewMain.setAdapter(pendingAdapter);

        } else if (status.equals("ODPR")) {
            processAdapter = new ProcessAdapter(this, ordersEmptyList, this);
            recyclerviewMain.setAdapter(processAdapter);


        } else if (status.equals("ODPK")) {
            packedAdapter = new PackedAdapter(this, ordersEmptyList, this);
            recyclerviewMain.setAdapter(packedAdapter);
        } else if (status.equals("ODDS")) {
            dispatchAdapter = new DispatchAdapter(this, ordersEmptyList, this);
            recyclerviewMain.setAdapter(dispatchAdapter);
        }
    }


    @Override
    public void income(String total, String qty) {
        textViewTotal.setText(total + " LKR");
        textViewTotalQty.setText(qty);

    }

    @Override
    public void noincome() {
        textViewTotal.setText("0.00 LKR");
        textViewTotalQty.setText("0");
    }

    @Override
    public void incomeTimeOut() {

    }


    @Override
    public void logoutSuccess() {

        Intent intent = new Intent(Home.this, Login.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void outlatname(String name) {
        textviewOutletName.setText(name);
    }

    private void setSliderMenu() {
        sliderItems = new ArrayList<SliderMenuItems>();
        sliderMenuAdapter = new SliderMenuAdapter(this, sliderItems);
        homePresenter.setAllNavagationItem(sliderItems);

    }


    private void bloackUserInteraction() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }

    private void unBloackUserInteraction() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }


    private void errorHandlingUI(String msg, boolean isTextViewVisible, boolean isButtonVisible, final int orderId) {

        textViewMessage.setText(msg);

        if (isTextViewVisible) {
            textViewMessage.setVisibility(View.VISIBLE);
        } else {
            textViewMessage.setVisibility(View.GONE);
        }

        if (isButtonVisible) {
            buttonRetry.setVisibility(View.VISIBLE);
        } else {
            buttonRetry.setVisibility(View.GONE);
        }


        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewMessage.setVisibility(View.GONE);
                buttonRetry.setVisibility(View.GONE);

                progressBar.setVisibility(View.VISIBLE);
                bloackUserInteraction();
                homePresenter.getOrders(status, selectDeliveryTimeSlotId, selectriderId, dispathType);

            }
        });


    }

    public String getApplicationVersion() {
        String version;
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            version = "1.0.0";
            e.printStackTrace();
        }
        version = pInfo.versionName;
        return version;
    }


}
