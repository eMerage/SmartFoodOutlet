package emerge.project.onmealoutlet.ui.adaptor;


import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

import emerge.project.onmealoutlet.R;

import emerge.project.onmealoutlet.ui.activity.home.HomePresenter;
import emerge.project.onmealoutlet.ui.activity.home.HomePresenterImpli;
import emerge.project.onmealoutlet.ui.activity.home.HomeView;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrdersList;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class PackedAdapter extends RecyclerView.Adapter<PackedAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<OrdersList> ordersItems;

    HomePresenter homePresenter;
    CountDownTimer cTimer;

    public PackedAdapter(Context mContext, ArrayList<OrdersList> item, HomeView homeView) {
        this.mContext = mContext;
        this.ordersItems = item;
        homePresenter = new HomePresenterImpli(homeView);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_orders_pack, parent, false);

        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        final OrdersList orders =ordersItems.get(position);

        holder.textViewOrderNumber.setText(String.valueOf(orders.getOrderID()));
        holder.textViewQTY.setText(String.valueOf(orders.getOrderQty()));


        final String time;
        long remTime ;

        if (orders.getDeliveryTime().getTimeSlotID() == 0) {
            time = orders.getPickUpTime();
            remTime=getRemaningMin(time);
        } else {
            time = orders.getDeliveryTime().getTimeSlot();
            remTime=getRemaningMinDelivery(orders.getDeliveryTime().getTimeFrom(),orders.getDeliveryTime().getTimeTo());
        }

        holder.textViewTime.setText(time);



        if(orders.getPaymentType().equals("CH")){
            holder.textViewPaymenttype.setText("Cash");
        }else {
            holder.textViewPaymenttype.setText("Card");
        }


        holder.textViewRider.setText(orders.getRideName());


        cTimer=  new CountDownTimer(remTime, 1000) {

            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;

                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

                holder.textViewCountdownTime.setText(hms);//set text

                if(millisUntilFinished<420000){

                    if(orders.isCuntDownExp()){

                    }else {
                        holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.app_color_light_red));
                        orders.setCuntDownExp(true);


                        notifyDataSetChanged();
                    }


                }else {


                }


            }
            public void onFinish() {
                holder.textViewTime.setText(time);
                holder.textViewCountdownTime.setText("Time passed");//set text
                holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.app_color_light_red));
            }
        }.start();






        if(String.valueOf(orders.getDispatchType()).equals("D")){
            holder.textViewDispatchType.setText("Delivery");
        }else if(String.valueOf(orders.getDispatchType()).equals("P")){
            holder.textViewDispatchType.setText("Pickup");
        }else if(String.valueOf(orders.getDispatchType()).equals("T")){
            holder.textViewDispatchType.setText("Dine In");
        }


        holder.textView_price.setText(String.valueOf(orders.getOrderTotal()));


        String promo;

        if (orders.getPromoTitle().equals("")) {
            promo = "no Promo";
        } else {
            promo = orders.getPromoTitle();
        }


        holder.textViewPromotype.setText(promo);
        holder.textViewNote.setText(orders.getOrderNote());


       holder.textViewDiscount.setText(String.valueOf(orders.getPromoDiscountValue()));



        holder.relativelayouMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.getOrdersFullDetails(orders.getMenuItems());

            }
        });


        holder.buttonDispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cTimer.cancel();
                homePresenter.updateOrderStatus(orders.getOrderID(),"ODDS",orders.getDispatchType());

            }
        });

        holder.buttonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.printOrders(orders.getOrderID(),1);

            }
        });

    }

    @Override
    public int getItemCount() {
        return ordersItems.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_view)
        CardView cardView;

        @BindView(R.id.textView_ordernumber)
        TextView textViewOrderNumber;

        @BindView(R.id.textView_qy)
        TextView textViewQTY;


        @BindView(R.id.textView_time)
        TextView textViewTime;
        @BindView(R.id.textView_countdown_time)
        TextView textViewCountdownTime;

        @BindView(R.id.textView_rider)
        TextView textViewRider;

        @BindView(R.id.textView_dispatchtype)
        TextView textViewDispatchType;

        @BindView(R.id.textView_paymenttype)
        TextView textViewPaymenttype;

        @BindView(R.id.textView_price)
        TextView textView_price;




        @BindView(R.id.button_print)
        Button buttonPrint;

        @BindView(R.id.button_dispatch)
        Button buttonDispatch;


        @BindView(R.id.textView_note)
        TextView textViewNote;


        @BindView(R.id.textView_promotype)
        TextView textViewPromotype;

        @BindView(R.id.relativelayout_main)
        RelativeLayout relativelayouMain;



        @BindView(R.id.textView_discount)
        TextView textViewDiscount;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }

    public long getRemaningMin(String time) {

        Calendar c = Calendar.getInstance();
        String splitTime[] = time.split(":");
        Calendar cal = Calendar.getInstance();
        cal.set(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DATE),
                Integer.parseInt(splitTime[0]),
                Integer.parseInt(splitTime[1]));


        Date now = new Date();

        String remaining1 = DateUtils.formatElapsedTime((cal.getTime().getTime() - now.getTime()) / 1000); // Remaining time to seconds


        long millSecond = 0;

        String[] splitRemaningTime = remaining1.split(":");
        if(splitRemaningTime.length==3){
            long mill =( Long.parseLong(splitRemaningTime[1])  +( Long.parseLong(splitRemaningTime[0])*60)) *60000 ;
            millSecond = mill ;
        }else if(Integer.parseInt(splitRemaningTime[0]) == 0){
            millSecond = 0;
        } else {
            long mill = (Long.parseLong(splitRemaningTime[0])) * 60000 ;
            millSecond = mill;
        }
        return millSecond;
    }


    public long getRemaningMinDelivery(String stime,String eTime) {

        Calendar c = Calendar.getInstance();
        String splitStartTime[] = stime.split(":");
        String splitEndTime[] = eTime.split(":");


        Calendar cal = Calendar.getInstance();
        cal.set(c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DATE),
                Integer.parseInt(splitStartTime[0]),
                Integer.parseInt(splitEndTime[0]));


        Date now = new Date();

        String remaining1 = DateUtils.formatElapsedTime((cal.getTime().getTime() - now.getTime()) / 1000); // Remaining time to seconds


        long millSecond = 0;

        String[] splitRemaningTime = remaining1.split(":");
        if(splitRemaningTime.length==3){
            long mill =( Long.parseLong(splitRemaningTime[1])  +( Long.parseLong(splitRemaningTime[0])*60)) *60000 ;
            millSecond = mill ;
        }else if(Integer.parseInt(splitRemaningTime[0]) == 0){
            millSecond = 0;
        } else {
            long mill = (Long.parseLong(splitRemaningTime[0])) * 60000 ;
            millSecond = mill;
        }
        return millSecond;
    }
}
