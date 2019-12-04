package emerge.project.onmealoutlet.ui.activity.orderHistory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.archit.calendardaterangepicker.customviews.DateRangeCalendarView;
import com.tuyenmonkey.mkloader.MKLoader;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.ui.activity.home.Home;
import emerge.project.onmealoutlet.ui.adaptor.OrderHistoryAdapter;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;

public class OrderHistory extends Activity implements OrderHistoryView {


    @BindView(R.id.recyclerView_order_history)
    RecyclerView recyclerViewOrder;

    @BindView(R.id.proprogressview_history)
    MKLoader proprogressview;


    @BindView(R.id.swiperefresh_orderhistory)
    SwipeRefreshLayout swipeContainer;


    @BindView(R.id.textView_tot_quntity)
    TextView textViewTotQuntity;

    @BindView(R.id.textView_tot_value)
    TextView textViewTotValue;

    @BindView(R.id.textView_tot_value_cash)
    TextView textViewCash;

    @BindView(R.id.textView_tot_value_cheque)
    TextView textViewCheque;


    @BindView(R.id.imageView_calander)
    ImageView imageViewCalander;


    @BindView(R.id.textview_stat_date)
    TextView textviewStatDate;




    Dialog dialogCalander;


    String filterDateStart = "", filterDateEnd = "";


    OrderHistoryPresenter orderHistoryPresenter;

    String dispathType = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_history);


        ButterKnife.bind(this);

        orderHistoryPresenter = new OrderHistoryPresenterImpli(this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewOrder.setLayoutManager(layoutManager);
        recyclerViewOrder.setItemAnimator(new DefaultItemAnimator());
        recyclerViewOrder.setNestedScrollingEnabled(false);


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                orderHistoryPresenter.getOrderHistory(filterDateStart, filterDateEnd,dispathType);
                orderHistoryPresenter.getOrderHistorySalse(filterDateStart, filterDateEnd);

            }
        });


        imageViewCalander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalanderDilog();
            }
        });


    }


    @Override
    public void getOrderHistory(ArrayList<Orders> orderItems) {

        OrderHistoryAdapter OrderHistoryAdapter = new OrderHistoryAdapter(this, orderItems);
        recyclerViewOrder.setAdapter(OrderHistoryAdapter);
        proprogressview.setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);

    }

    @Override
    public void getOrderHistoryFail(String msg) {

        swipeContainer.setRefreshing(false);
        proprogressview.setVisibility(View.GONE);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Error!");
        alertDialogBuilder.setMessage(msg + "Do you want to retry ?");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        proprogressview.setVisibility(View.VISIBLE);
                        orderHistoryPresenter.getOrderHistory(filterDateStart, filterDateEnd,dispathType);

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

    @Override
    public void getOrderHistorySalse(OutletSales outletSales) {
        proprogressview.setVisibility(View.GONE);
        swipeContainer.setRefreshing(false);


        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(',');
        formatter.setDecimalFormatSymbols(symbols);

        textViewTotQuntity.setText(formatter.format(outletSales.getTotalQty()));
        textViewTotValue.setText(formatter.format(outletSales.getTotalValue()));
        textViewCash.setText(formatter.format(outletSales.getTotalValueCash()));
        textViewCheque.setText(formatter.format(outletSales.getTotalValueCard()));


    }

    @Override
    public void getOrderHistoryFailSalse(String msg) {
        swipeContainer.setRefreshing(false);
        proprogressview.setVisibility(View.GONE);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Error!");
        alertDialogBuilder.setMessage(msg + " Do you want to retry ?");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        proprogressview.setVisibility(View.VISIBLE);
                        orderHistoryPresenter.getOrderHistorySalse(filterDateStart, filterDateEnd);

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


    @OnClick(R.id.relativeLayout_slider_menu)
    public void onClickBackMenu(View view) {
        final Intent intent;
        intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();

    }

    private void showCalanderDilog() {
        dialogCalander = new Dialog(this);
        dialogCalander.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogCalander.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialogCalander.setContentView(R.layout.dialog_data_calande);
        dialogCalander.setCancelable(true);


        final DateRangeCalendarView calendarView = (DateRangeCalendarView) dialogCalander.findViewById(R.id.calendarView);
        final Button button_select = (Button) dialogCalander.findViewById(R.id.button_select);


        String date = "Dates :";

        if (filterDateStart.isEmpty()) {
            date = "Date not selected yet";
        } else if (filterDateEnd.isEmpty()) {
            date = date + " " + filterDateStart;
        } else {
            date = date + " " + filterDateStart + " To " + filterDateEnd;
        }

        final SimpleDateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");

        textviewStatDate.setText(date);


        Date dateS = null;
        Date dateE = null;

        try {
            dateS = targetFormat.parse(filterDateStart);
            dateE = targetFormat.parse(filterDateEnd);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (filterDateStart.isEmpty()) {

        } else if (filterDateEnd.isEmpty()) {
            Calendar startSelectionDate = Calendar.getInstance();
            startSelectionDate.setTime(dateS);
            calendarView.setSelectedDateRange(startSelectionDate, startSelectionDate);

        } else {
            Calendar startSelectionDate = Calendar.getInstance();
            startSelectionDate.setTime(dateS);
            Calendar endSelectionDate = (Calendar) startSelectionDate.clone();
            endSelectionDate.setTime(dateE);
            calendarView.setSelectedDateRange(startSelectionDate, endSelectionDate);
        }


        calendarView.setCalendarListener(new DateRangeCalendarView.CalendarListener() {
            @Override
            public void onFirstDateSelected(Calendar startDate) {
                filterDateStart = targetFormat.format(startDate.getTime());
                textviewStatDate.setText("Date : " + filterDateStart);

            }

            @Override
            public void onDateRangeSelected(Calendar startDate, Calendar endDate) {
                filterDateStart = targetFormat.format(startDate.getTime());
                filterDateEnd = targetFormat.format(endDate.getTime());
                textviewStatDate.setText("Dates : " + filterDateStart + " To " + filterDateEnd);


            }
        });


        button_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCalander.dismiss();
                proprogressview.setVisibility(View.VISIBLE);


                orderHistoryPresenter.getOrderHistory(filterDateStart, filterDateEnd,dispathType);
                orderHistoryPresenter.getOrderHistorySalse(filterDateStart, filterDateEnd);
            }
        });


        dialogCalander.show();
    }


}
