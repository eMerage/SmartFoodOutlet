package emerge.project.onmealoutlet.ui.adaptor;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import emerge.project.onmealoutlet.utils.entittes.OrderMenuHistory;
import emerge.project.onmealoutlet.utils.entittes.Orders;
import emerge.project.onmealoutlet.utils.entittes.v2.OrderHistory.OrderHistoryOrdersMenus;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class OrderHistoryDetialsAdapter extends RecyclerView.Adapter<OrderHistoryDetialsAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<OrderHistoryOrdersMenus> menuItems;


    int status = 0;

    public OrderHistoryDetialsAdapter(Context mContext, ArrayList<OrderHistoryOrdersMenus> item) {
        this.mContext = mContext;
        this.menuItems = item;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_history_items, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final OrderHistoryOrdersMenus menuItemsObject = menuItems.get(position);




        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(',');
        formatter.setDecimalFormatSymbols(symbols);


         holder.textviewFoodname.setText(menuItemsObject.getName());
         holder.textviewQuantity.setText(formatter.format(menuItemsObject.getMenuQty()));
         holder.textviewValue.setText(formatter.format(menuItemsObject.getMenuPrice()));



    }


    @Override
    public int getItemCount() {
        return menuItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textview_history_items_foodname)
        TextView textviewFoodname;
        @BindView(R.id.textview_history_items_quantity)
        TextView textviewQuantity;
        @BindView(R.id.textview_history_items_value)
        TextView textviewValue;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }


}
