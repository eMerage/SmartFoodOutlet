package emerge.project.onmealoutlet.ui.adaptor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.utils.entittes.SliderMenuItems;


/**
 * Created by Himanshu on 4/9/2015.
 */
public class SliderMenuAdapter extends BaseAdapter {

    Context mContext;
    ArrayList<SliderMenuItems> menuItems;


    @BindView(R.id.textView_slidermenu_name)
    TextView txtName;

    @BindView(R.id.imageView_slider_menu_icon)
    ImageView imgViewIcon;

    public SliderMenuAdapter(Context context, ArrayList<SliderMenuItems> item) {
        mContext = context;
        menuItems = item;

    }

    @Override
    public int getCount() {
        return menuItems.size();
    }

    @Override
    public Object getItem(int i) {
        return menuItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        View view;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview_slider_menu, null);


        } else {
            view = convertView;

        }

        ButterKnife.bind(this, view);


        txtName.setText( menuItems.get(position).getmTitle());
        imgViewIcon.setImageResource(menuItems.get(position).getmIcon());



        return view;
    }





}
