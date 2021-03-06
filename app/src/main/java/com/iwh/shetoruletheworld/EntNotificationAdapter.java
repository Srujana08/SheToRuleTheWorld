package com.iwh.shetoruletheworld;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by SrujanaParupudi on 2/28/2018.
 */

public class EntNotificationAdapter extends BaseAdapter implements ListAdapter {
    Context context;
    ArrayList<String> noti;

    public EntNotificationAdapter(
            Context context2,
            ArrayList<String> noti) {
        this.context = context2;
        this.noti = noti;
    }
    public int getCount() {
        // TODO Auto-generated method stub
        return noti.size();
    }
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
    public View getView(int position, View child, ViewGroup parent) {

        final Holder holder;
        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.ent_notification_data_layout, null);

            holder = new Holder();

            holder.textviewNoti = (TextView) child.findViewById(R.id.notification);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewNoti.setText(noti.get(position));

        return child;
    }

    public class Holder {
        TextView textviewNoti;
    }
}
