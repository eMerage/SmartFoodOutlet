package emerge.project.onmealoutlet.ui.adaptor;


import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.servies.network.NetworkAvailability;
import emerge.project.onmealoutlet.ui.activity.menus.MenusPresenter;
import emerge.project.onmealoutlet.ui.activity.menus.MenusPresenterImpli;
import emerge.project.onmealoutlet.ui.activity.menus.MenusView;
import emerge.project.onmealoutlet.utils.entittes.Foods;
import emerge.project.onmealoutlet.utils.entittes.MenuItems;
import emerge.project.onmealoutlet.utils.entittes.Menus;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class MenuUpdateAdapter extends RecyclerView.Adapter<MenuUpdateAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<MenuItems> menusItems;

    MenusPresenter menuPresenter;

    public MenuUpdateAdapter(Context mContext, ArrayList<MenuItems> item, MenusView menuView) {
        this.mContext = mContext;
        this.menusItems = item;

        menuPresenter = new MenusPresenterImpli(menuView);

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menu_update, parent, false);

        return new MyViewHolder(itemView);


    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        final MenuItems menusList =menusItems.get(position);


        holder.textViewMenuName.setText(menusList.getFoodName());
        holder.textViewMenuQty.setText(String.valueOf(menusList.getMaxAvailableQty()));


        holder.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (NetworkAvailability.isNetworkAvailable(mContext)) {

                    int qty=0;
                    try {
                        qty = Integer.parseInt( holder.textViewMenuQty.getText().toString());
                        menuPresenter.updateAvailableQty(menusList.getFoodId(), qty);
                    }catch (NumberFormatException numex){
                        Toast.makeText(mContext, "Please enter the valid quantity", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(mContext, "No Internet Access, Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return menusItems.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {



        @BindView(R.id.textView_menuName)
        TextView textViewMenuName;

        @BindView(R.id.editText)
        EditText textViewMenuQty;

        @BindView(R.id.button)
        Button btnUpdate;





        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }




}
