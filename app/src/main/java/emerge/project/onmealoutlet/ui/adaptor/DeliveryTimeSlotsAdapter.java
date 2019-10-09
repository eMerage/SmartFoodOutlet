package emerge.project.onmealoutlet.ui.adaptor;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import emerge.project.onmealoutlet.R;
import emerge.project.onmealoutlet.utils.entittes.TimeSlots;


/**
 * Created by Himanshu on 4/9/2015.
 */
public class DeliveryTimeSlotsAdapter extends BaseAdapter {


    @BindView(R.id.textView)
    TextView textViewTimeSlots;

    Context mContext;

    ArrayList<TimeSlots> slotsArrayList;

    public DeliveryTimeSlotsAdapter(Context context, ArrayList<TimeSlots> item) {
        mContext = context;
        slotsArrayList = item;

    }

    @Override
    public int getCount() {
        return slotsArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return slotsArrayList.get(i);
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
            view = inflater.inflate(R.layout.spinner_item, null);


        } else {
            view = convertView;

        }

        ButterKnife.bind(this, view);

        textViewTimeSlots.setText(slotsArrayList.get(position).getSlotsName());



        return view;
    }





}
