package emerge.project.onmealoutlet.ui.adaptor;


import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


/**
 * Created by Himanshu on 4/10/2015.
 */
public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.MyViewHolder> {

    Context mContext;

    ArrayList<Orders> ordersItems;
    int counter;
    HomePresenter homePresenter;
    CountDownTimer cTimer;
    boolean isCollor=false;
    public PendingAdapter(Context mContext, ArrayList<Orders> item, HomeView homeView) {
        this.mContext = mContext;
        this.ordersItems = item;

        homePresenter = new HomePresenterImpli(homeView);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_orders_pending, parent, false);

        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final Orders orders = ordersItems.get(position);


        counter = 0;

        holder.textViewOrderNumber.setText(String.valueOf(orders.getOrderID()));

        final String time;
        long remTime ;

        if (orders.getDeliveryTime().getTimeSlotID() == 0) {
            time = orders.getPickUpTime();
             remTime=getRemaningMin(time);
        } else {
            time = orders.getDeliveryTime().getTimeSlot();
            remTime=getRemaningMinDelivery(orders.getDeliveryTime().getTimeFrom(),orders.getDeliveryTime().getTimeTo());
        }








        new CountDownTimer(remTime, 1000) {

            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;

                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                holder.textViewTime.setText(time+" "+hms);//set text

                if(millisUntilFinished<420000){

                    if(orders.isCuntDownExp()){

                    }else {
                        holder.cardView.setCardBackgroundColor(mContext.getResources().getColor(R.color.app_color_light_red));
                        orders.setCuntDownExp(true);

                        try {
                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                            Ringtone r = RingtoneManager.getRingtone(mContext, notification);
                            r.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        notifyDataSetChanged();
                    }


                }else {



                }




            }
            public void onFinish() {
                holder.textViewTime.setText(time);//set text
            }
        }.start();







        holder.textViewRider.setText(orders.getRider().getName());

        holder.textViewAmount.setText(String.valueOf(orders.getOrderTotal()));
        holder.textViewQty.setText(String.valueOf(orders.getOrderQty()));

        holder.textViewDispatchType.setText(String.valueOf(orders.getDispatchType()));

        String promo;

        if (orders.getPromoTitle().equals("")) {
            promo = "no Promo";
        } else {
            promo = orders.getPromoTitle();
        }


        holder.textViewPromotype.setText(promo);

        holder.textViewNote.setText(orders.getOrderNote());


        holder.buttonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.printOrders(orders.getOrderID(), 0);

            }
        });


        holder.relativelayouMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(mContext, "Please Process the Order to view order details ", Toast.LENGTH_SHORT).show();


               // homePresenter.getOrdersFullDetails(orders.getOrderID());

            }
        });


        holder.buttonProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.updateOrderStatus(orders.getOrderID(), orders.getUserID(), "ODPR");

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

        @BindView(R.id.textView_time)
        TextView textViewTime;

        @BindView(R.id.textView_rider)
        TextView textViewRider;

        @BindView(R.id.textView_amount)
        TextView textViewAmount;

        @BindView(R.id.textView_qty)
        TextView textViewQty;

        @BindView(R.id.textView_dispatchtype)
        TextView textViewDispatchType;

        @BindView(R.id.textView_promotype)
        TextView textViewPromotype;

        @BindView(R.id.textView_note)
        TextView textViewNote;


        @BindView(R.id.button_print)
        Button buttonPrint;

        @BindView(R.id.button_process)
        Button buttonProcess;

        @BindView(R.id.relativelayout_main)
        RelativeLayout relativelayouMain;


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

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {

        super.onDetachedFromRecyclerView(recyclerView);
    }
}
