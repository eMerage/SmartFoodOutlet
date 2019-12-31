package emerge.project.onmealoutlet.ui.adaptor;


import android.content.Context;
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
import emerge.project.onmealoutlet.utils.entittes.v2.MenuHistory.MenuHistoryOrdersMenus;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class MenuHistoryAdapter extends RecyclerView.Adapter<MenuHistoryAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<MenuHistoryOrdersMenus> menusItems;



    public MenuHistoryAdapter(Context mContext, ArrayList<MenuHistoryOrdersMenus> item) {
        this.mContext = mContext;
        this.menusItems = item;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menu_history, parent, false);

        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final MenuHistoryOrdersMenus menusList =menusItems.get(position);


        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.US);
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();

        symbols.setGroupingSeparator(',');
        formatter.setDecimalFormatSymbols(symbols);


        holder.textViewMenuName.setText(menusList.getName());
        holder.textViewMenuQty.setText(formatter.format(menusList.getMaxOrderQty()));
        holder.textViewMenuValue.setText(formatter.format(menusList.getMenuPrice()));





    }

    @Override
    public int getItemCount() {
        return menusItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.textView_menuName)
        TextView textViewMenuName;


        @BindView(R.id.textView_tot_quntity)
        TextView textViewMenuQty;


        @BindView(R.id.textView_tot_value)
        TextView textViewMenuValue;



        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }




}
