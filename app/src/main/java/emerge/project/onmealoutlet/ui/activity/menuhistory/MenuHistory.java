package emerge.project.onmealoutlet.ui.activity.menuhistory;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
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
import emerge.project.onmealoutlet.ui.adaptor.MenuHistoryAdapter;
import emerge.project.onmealoutlet.utils.entittes.OutletSales;
import emerge.project.onmealoutlet.utils.entittes.v2.MenuHistory.MenuHistoryData;
import emerge.project.onmealoutlet.utils.entittes.v2.MenuHistory.MenuHistoryOrdersMenus;

public class MenuHistory extends Activity implements MenuHistoryView {


    MenuHistoryPresenter menuHistoryPresenter;


    @BindView(R.id.recyclerView_menu_history)
    RecyclerView recyclerViewMenu;

    @BindView(R.id.proprogressview)
    MKLoader proprogressview;


    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeContainer;

    @BindView(R.id.imageView_calander)
    ImageView imageViewCalander;


    @BindView(R.id.textview_stat_date)
    TextView textviewStatDate;

    Dialog dialogCalander;


    @BindView(R.id.textView_tot_quntity)
    TextView textViewTotQuntity;

    @BindView(R.id.textView_tot_value)
    TextView textViewTotValue;

    @BindView(R.id.textView_tot_value_cash)
    TextView textViewCash;

    @BindView(R.id.textView_tot_value_cheque)
    TextView textViewCheque;


    String filterDateStart = "", filterDateEnd = "";


    MenuHistoryAdapter menuHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu_history);
        ButterKnife.bind(this);

        menuHistoryPresenter = new MenuHistoryPresenterImpli(this);



        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewMenu.setLayoutManager(layoutManager);
        recyclerViewMenu.setItemAnimator(new DefaultItemAnimator());
        recyclerViewMenu.setNestedScrollingEnabled(false);


        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                menuHistoryPresenter.getMenuHistory(filterDateStart,filterDateEnd);
            }
        });

        imageViewCalander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCalanderDilog();
            }
        });

    }



    @OnClick(R.id.relativeLayout_slider_menu)
    public void onClickBackMenu(View view) {
        final Intent intent;
        intent = new Intent(this, Home.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void getMenuHistory(MenuHistoryData menuItems) {

        swipeContainer.setRefreshing(false);
        proprogressview.setVisibility(View.GONE);

        menuHistoryAdapter = new MenuHistoryAdapter(this, menuItems.getStatus().getOrderHistoryOrders());
        recyclerViewMenu.setAdapter(menuHistoryAdapter);


        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(',');
        formatter.setDecimalFormatSymbols(symbols);

        textViewTotQuntity.setText(formatter.format(menuItems.getStatus().getTotalQty()));
        textViewTotValue.setText(formatter.format(menuItems.getStatus().getTotalValue()));
        textViewCash.setText(formatter.format(menuItems.getStatus().getTotalValueCash()));
        textViewCheque.setText(formatter.format(menuItems.getStatus().getTotValueCard()));



    }

    @Override
    public void getMenuHistoryFail(String msg) {
        swipeContainer.setRefreshing(false);
        proprogressview.setVisibility(View.GONE);



        menuHistoryAdapter = new MenuHistoryAdapter(this, new ArrayList<MenuHistoryOrdersMenus>());
        recyclerViewMenu.setAdapter(menuHistoryAdapter);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Error!");
        alertDialogBuilder.setMessage(msg+" Do you want to retry ?");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        proprogressview.setVisibility(View.VISIBLE);
                        menuHistoryPresenter.getMenuHistory(filterDateStart,filterDateEnd);

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
               menuHistoryPresenter.getMenuHistory(filterDateStart,filterDateEnd);
            }
        });


        dialogCalander.show();
    }

}
