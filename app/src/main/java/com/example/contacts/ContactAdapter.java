package com.example.contacts;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactAdapter extends ArrayAdapter<Contact> {

    Context context;
    int layoutResourceId;
    ArrayList<Contact> data;

    public ContactAdapter(Context context, int layoutResourceId, ArrayList<Contact> data) {
        super(context, layoutResourceId, data);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View row = convertView;
        ContactHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, viewGroup, false);

            holder = new ContactHolder();
            holder.nameTextView = (TextView) row.findViewById(R.id.listViewItemContactName);
            holder.phoneTextView = (TextView) row.findViewById(R.id.listViewItemContactPhone);
            holder.groupTextView = (TextView) row.findViewById(R.id.listViewItemContactGroup);

            row.setTag(holder);
        } else {
            holder = (ContactHolder) row.getTag();
        }

        Contact contact = data.get(position);
        holder.nameTextView.setText(contact.getName());
        holder.phoneTextView.setText(contact.getPhone());
        holder.groupTextView.setText(contact.getGroup());

        return row;
    }

    static class ContactHolder {
        TextView nameTextView;
        TextView phoneTextView;
        TextView groupTextView;
    }

}
