package emerge.project.onmealoutlet.ui.adaptor;


import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.utils.entittes.Foods;
import emerge.project.onmealoutlet.utils.entittes.Menus;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrderMenuDetails;
import emerge.project.onmealoutlet.utils.entittes.v2.Orders.OrderMenus;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<OrderMenus> menusItems;


    public MenuAdapter(Context mContext, ArrayList<OrderMenus> item) {
        this.mContext = mContext;
        this.menusItems = item;


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_order_items, parent, false);

        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final OrderMenus menusList =menusItems.get(position);



        holder.textViewMenuNumber.setText(menusList.getMenuID());
        holder.textViewMenuName.setText(menusList.getName());
        holder.textViewMenuSize.setText(String.valueOf(menusList.getMenuSizeCode()));
        holder.textViewAmount.setText(String.valueOf(menusList.getMenuPrice()));
        holder.textViewQuantity.setText(String.valueOf(menusList.getMenuQty()));



        if (holder.linearLayoutChild.getVisibility() == View.VISIBLE) {
            holder.linearLayoutChild.setVisibility(View.GONE);
        } else {
            holder.linearLayoutChild.setVisibility(View.VISIBLE);

            setSubItems(mContext,holder,menusList.getOrderMenuDetails());

        }



    }

    @Override
    public int getItemCount() {
        return menusItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.textView_menu_number)
        TextView textViewMenuNumber;

        @BindView(R.id.textView_menuName)
        TextView textViewMenuName;

        @BindView(R.id.textView_menu_size)
        TextView textViewMenuSize;

        @BindView(R.id.textView_amount)
        TextView textViewAmount;

        @BindView(R.id.textView_quantity)
        TextView textViewQuantity;






        @BindView(R.id.ll_child_items)
        LinearLayout linearLayoutChild;

        @BindView(R.id.relativelayout_main)
        RelativeLayout relativelayouMain;


        @BindView(R.id.recyclerView_sub_items)
        RecyclerView recyclerViewSubItems;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }


    public void setSubItems(Context context, MyViewHolder holder, ArrayList<OrderMenuDetails> foodsArrayList){


        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recyclerViewSubItems.setLayoutManager(layoutManager);
        holder.recyclerViewSubItems.setItemAnimator(new DefaultItemAnimator());
        holder.recyclerViewSubItems.setNestedScrollingEnabled(true);


        FoodAdapter foodAdapter = new FoodAdapter(context,foodsArrayList);
        holder.recyclerViewSubItems.setAdapter(foodAdapter);





    }


}
