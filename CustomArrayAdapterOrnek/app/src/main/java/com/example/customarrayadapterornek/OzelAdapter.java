package com.example.customarrayadapterornek;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.AnimatedStateListDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OzelAdapter extends BaseAdapter {

    public Context context;
    public ArrayList<Kisi> kisiler;
    LayoutInflater inflater;


    public OzelAdapter(Context context, ArrayList<Kisi> kisiler) {
        this.context = context;
        this.kisiler = kisiler;
    }

    @Override
    public int getCount() {
        return kisiler.size();
    }

    @Override
    public Object getItem(int position) {
        return kisiler.get(position);
    }

    @Override
    public long getItemId(int position) {
        return kisiler.indexOf(getItem(position));
    }


    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                context.getSystemService(MainActivity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.satir, null);

            holder = new ViewHolder();
            holder.txtTitle = (TextView) convertView.findViewById(R.id.textView);
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Kisi kisiItem = (Kisi) getItem(position);

        holder.txtTitle.setText(kisiItem.getName());
        holder.imageView.setImageResource(kisiItem.getPhoto());


        return convertView;
    }


}
