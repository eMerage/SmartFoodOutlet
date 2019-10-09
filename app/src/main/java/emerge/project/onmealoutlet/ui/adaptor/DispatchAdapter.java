package emerge.project.onmealoutlet.ui.adaptor;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

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
public class DispatchAdapter extends RecyclerView.Adapter<DispatchAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Orders> ordersItems;

    HomePresenter homePresenter;

    public DispatchAdapter(Context mContext, ArrayList<Orders> item, HomeView homeView) {
        this.mContext = mContext;
        this.ordersItems = item;


        homePresenter = new HomePresenterImpli(homeView);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_orders_dispatch, parent, false);

        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {



        final Orders orders = ordersItems.get(position);




        holder.textViewOrderNumber.setText(String.valueOf(orders.getOrderID()));

        if(orders.getDeliveryTime().getTimeSlotID()==0){
            holder.textViewTime.setText(orders.getPickUpTime());
        }else {
            holder.textViewTime.setText(orders.getDeliveryTime().getTimeSlot());
        }

        holder.textViewRider.setText(orders.getRider().getName());

        holder.textViewAmount.setText(String.valueOf(orders.getOrderTotal()));
        holder.textViewQty.setText(String.valueOf(orders.getOrderQty()));

        holder.textViewDispatchType.setText(String.valueOf(orders.getDispatchType()));

        String promo ;

        if(orders.getPromoTitle().equals("")){
            promo="no Promo";
        }else {
            promo=orders.getPromoTitle();
        }


        holder.textViewPromotype.setText(promo);

        holder.textViewNote.setText(orders.getOrderNote());

        holder.buttonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.printOrders(orders.getOrderID(),1);
            }
        });


        holder.relativelayouMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.getOrdersFullDetails(orders.getOrderID());

            }
        });


        holder.buttonProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // homePresenter.updateOrderStatus(orders.getOrderID(),orders.getUserID(),"ODDS");
            }
        });


    }

    @Override
    public int getItemCount() {
        return ordersItems.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {


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


}
