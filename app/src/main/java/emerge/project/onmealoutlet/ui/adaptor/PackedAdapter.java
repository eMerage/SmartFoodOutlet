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
public class PackedAdapter extends RecyclerView.Adapter<PackedAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Orders> ordersItems;

    HomePresenter homePresenter;

    public PackedAdapter(Context mContext, ArrayList<Orders> item, HomeView homeView) {
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


        final Orders orders =ordersItems.get(position);

        holder.textViewOrderNumber.setText(String.valueOf(orders.getOrderID()));
        holder.textViewQTY.setText(String.valueOf(orders.getOrderQty()));


        final String time;

        if (orders.getDeliveryTime().getTimeSlotID() == 0) {
            time = orders.getPickUpTime();
        } else {
            time = orders.getDeliveryTime().getTimeSlot();
        }

        holder.textViewTime.setText(time);



        if(orders.getPaymentType().equals("CH")){
            holder.textViewPaymenttype.setText("Cash");
        }else {
            holder.textViewPaymenttype.setText("Card");
        }


        holder.textViewRider.setText(orders.getRider().getName());


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
                homePresenter.getOrdersFullDetails(orders.getOrderID());

            }
        });


        holder.buttonDispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.updateOrderStatus(orders.getOrderID(),orders.getUserID(),"ODDS",orders.getDispatchType());

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


        @BindView(R.id.textView_ordernumber)
        TextView textViewOrderNumber;

        @BindView(R.id.textView_qy)
        TextView textViewQTY;


        @BindView(R.id.textView_time)
        TextView textViewTime;

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


}
