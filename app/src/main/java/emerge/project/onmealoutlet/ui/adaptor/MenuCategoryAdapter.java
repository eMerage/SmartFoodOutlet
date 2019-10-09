package emerge.project.onmealoutlet.ui.adaptor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.servies.network.NetworkAvailability;
import emerge.project.onmealoutlet.ui.activity.menus.MenusActivity;
import emerge.project.onmealoutlet.ui.activity.menus.MenusPresenter;
import emerge.project.onmealoutlet.ui.activity.menus.MenusPresenterImpli;
import emerge.project.onmealoutlet.ui.activity.menus.MenusView;
import emerge.project.onmealoutlet.utils.entittes.MenuCategoryItems;


/**
 * Created by Himanshu on 4/10/2015.
 */
public class MenuCategoryAdapter extends RecyclerView.Adapter<MenuCategoryAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<MenuCategoryItems> menuCategoryItems;

    MenusPresenter menuPresenter;


    int status =0;
    public MenuCategoryAdapter(Context mContext, ArrayList<MenuCategoryItems> item, MenusView menuView) {
        this.mContext = mContext;
        this.menuCategoryItems = item;
        menuPresenter = new MenusPresenterImpli(menuView);
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_menu_cat, parent, false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final MenuCategoryItems categoryItems = menuCategoryItems.get(position);



        holder.textCatName.setText(categoryItems.getMenuCategory());


        if(categoryItems.isSelect()){
            holder.textCatName.setTextColor(mContext.getResources().getColor(R.color.app_white));
            if(categoryItems.getMenuCategoryID().equals("0")){ }else {
                if(status==0){

                    if (NetworkAvailability.isNetworkAvailable(mContext)) {
                        menuPresenter.getMainFoodByOutlet(categoryItems.getMenuCategoryID());
                    } else {
                        Toast.makeText(mContext, "No Internet Access, Please try again", Toast.LENGTH_SHORT).show();
                    }

                }else {

                }

            }

        }else {
            holder.textCatName.setTextColor(mContext.getResources().getColor(R.color.menuIndicatorTextColor));

        }


        holder.relativeLayoutMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (MenuCategoryItems menuItems : menuCategoryItems) {
                    menuItems.setSelect(false);
                }
                menuCategoryItems.get(position).setSelect(true);
                notifyDataSetChanged();

                status ++;
                if(categoryItems.getMenuCategoryID().equals("0")){

                }else {
                    if (NetworkAvailability.isNetworkAvailable(mContext)) {
                        menuPresenter.getMainFoodByOutlet(menuCategoryItems.get(position).getMenuCategoryID());
                    } else {
                        Toast.makeText(mContext, "No Internet Access, Please try again", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });


    }

    @Override
    public int getItemCount() {
        return menuCategoryItems.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {

       @BindView(R.id.text_cat_name)
       TextView textCatName;



        @BindView(R.id.relativeLayout_main)
        RelativeLayout relativeLayoutMain;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }


}
