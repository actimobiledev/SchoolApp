package com.rahul.schoolapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.rahul.schoolapp.R;
import com.rahul.schoolapp.activity.MainActivity;


public class NavDrawerAdapter extends BaseAdapter {
    Context context;
    String[] data;
    private LayoutInflater inflater = null;

    public NavDrawerAdapter (Context context, String[] data) {
        this.context = context;
        this.data = data;
        inflater = (LayoutInflater) context.getSystemService (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount () {
        return data.length;
    }

    @Override
    public Object getItem (int position) {
        return data[position];
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate (R.layout.list_item_nav_drawer, null);
        ImageView icon = (ImageView) vi.findViewById(R.id.imageViewnavdraweritem);

        if (position % 2 == 1) {
            icon.setBackgroundColor (context.getResources ().getColor (R.color.colorNavDrawerLight));
        } else {
            icon.setBackgroundColor(context.getResources().getColor(R.color.colorNavDrawer));
        }
        TextView text = (TextView) vi.findViewById (R.id.textViewnavdraweritem);
        text.setText (data[position]);
      //  if (MainActivity.roll==0) {
            switch (position) {
                case 0:
                    icon.setImageResource(R.drawable.ic_dashboard_image);
                    icon.setVisibility(View.VISIBLE);
                    break;

            }
       // }

      /*  if (MainActivity.roll==1) {

            switch (position) {

                case 0:
                    icon.setImageResource(R.drawable.ic_dashboard_image);
                    icon.setVisibility(View.VISIBLE);
                    break;
                case 1:
                    icon.setImageResource(R.drawable.ic_manage_classes2);
                    icon.setVisibility(View.VISIBLE);
                    break;
                case 2:
                    icon.setImageResource(R.drawable.ic_coursework2);
                    icon.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    icon.setImageResource(R.drawable.ic_manage_students3);
                    icon.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    icon.setImageResource(R.drawable.ic_library2);
                    icon.setVisibility(View.VISIBLE);
                    break;
                case 5:
                    icon.setImageResource(R.drawable.ic_reports2);
                    icon.setVisibility(View.VISIBLE);
                    break;
                case 6:
                    icon.setImageResource(R.drawable.ic_transport2);
                    icon.setVisibility(View.VISIBLE);
                    break;
                case 7:
                    icon.setImageResource(R.drawable.ic_settings2);
                    icon.setVisibility(View.VISIBLE);
                    break;
            }
        }
        TextView text = (TextView) vi.findViewById (R.id.textViewnavdraweritem);
        if (position % 2 == 1) {
            text.setBackgroundColor (context.getResources ().getColor (R.color.colorNavDrawerLight));
        } else {
            text.setBackgroundColor (context.getResources ().getColor (R.color.colorNavDrawer));
        }
        text.setText (data[position]);

        //     if (position == Constants.nav_drawer_selection) {
        //         Toast.makeText (context, "position :" + Constants.nav_drawer_selection, Toast.LENGTH_SHORT).show ();
        //         text.setTextColor (Color.parseColor ("#5a7699"));
        //      }
*/
        return vi;
    }
}
