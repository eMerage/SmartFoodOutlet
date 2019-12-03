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
public class ProcessAdapter extends RecyclerView.Adapter<ProcessAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Orders> ordersItems;

    HomePresenter homePresenter;

    public ProcessAdapter(Context mContext, ArrayList<Orders> item, HomeView homeView) {
        this.mContext = mContext;
        this.ordersItems = item;

        homePresenter = new HomePresenterImpli(homeView);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_orders_process, parent, false);

        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Orders orders =ordersItems.get(position);


        holder.textViewOrderNumber.setText(String.valueOf(orders.getOrderID()));
        holder.textViewQTY.setText(String.valueOf(orders.getOrderQty()));
        holder.textViewNote.setText(orders.getOrderNote());


        holder.relativelayouMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               homePresenter.getOrdersFullDetails(orders.getOrderID());

            }
        });


        holder.buttonPack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.updateOrderStatus(orders.getOrderID(),orders.getUserID(),"ODPK",orders.getDispatchType());

            }
        });

        holder.buttonPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homePresenter.printOrders(orders.getOrderID(),0);

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

        @BindView(R.id.textView_note)
        TextView textViewNote;

        @BindView(R.id.button_print)
        Button buttonPrint;

        @BindView(R.id.button_pack)
        Button buttonPack;


        @BindView(R.id.relativelayout_main)
        RelativeLayout relativelayouMain;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }


}
